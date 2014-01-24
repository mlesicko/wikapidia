package org.wikapidia.sr.wikilinks;

import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;
import org.apache.commons.cli.*;
import org.wikapidia.conf.ConfigurationException;
import org.wikapidia.conf.Configurator;
import org.wikapidia.core.cmd.Env;
import org.wikapidia.core.cmd.EnvBuilder;
import org.wikapidia.core.dao.DaoException;
import org.wikapidia.core.dao.LocalPageDao;
import org.wikapidia.core.lang.Language;
import org.wikapidia.core.model.Title;

import java.io.*;
import java.util.*;

/**
 * @author Matt Lesicko
 **/
public class LoadWikilinks {
    Map<Integer,TIntSet> pagesToUrls;
    Map<Integer,TIntSet> pagesToDomains;
    Map<String, Integer> urlToId;
    Map<String, Integer> domainNameToId;
    int numURLs;
    int numDomains;
    int nameFails;
    int formatFails;
    LocalPageDao localPageDao;

    static String dir = "./db/matrix/wikilinks/";

    public LoadWikilinks(LocalPageDao localPageDao){
        pagesToDomains = new HashMap<Integer, TIntSet>();
        pagesToUrls = new HashMap<Integer, TIntSet>();
        urlToId = new HashMap<String, Integer>();
        domainNameToId = new HashMap<String, Integer>();
        numURLs=0;
        numDomains=0;
        nameFails=0;
        formatFails=0;
        this.localPageDao = localPageDao;
    }

    private String extractDomain (String url){
        return url.split("//")[1].split("/")[0];
    }


    private Language extractLang(String url){
        String[] urlPieces = url.split("\\.")[0].split("/");
        String langCode = urlPieces[urlPieces.length-1];
        if (langCode.substring(0,2).equals(":@")){
            langCode=langCode.substring(2);
        }
        return Language.getByLangCode(langCode);
    }

    private static boolean isMention(String line){
        if (line==null){
            return false;
        }
        if (line.length()>7&&line.substring(0,7).equals("MENTION")){
            return true;
        }
        else {
            return false;
        }
    }


    void loadPages(String linkFile) throws IOException {
        FileInputStream fos = new FileInputStream(linkFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fos));
        for (String line = br.readLine(); line!=null; line=br.readLine()){
            if (line.length()>2&&line.substring(0,3).equals("URL")){
                String url = line.split("\t")[1];
                TIntSet mentions = new TIntHashSet();
                for (String mention = br.readLine(); isMention(mention); mention=br.readLine()){
                    String[] pieces = mention.split("\t");
                    String[] urlPieces = pieces[3].split("wikipedia\\.org/wiki/");
                    if (urlPieces.length == 2){
                        String articleName = urlPieces[1];
                        articleName = articleName.split("#")[0];
                        Title title = new Title(articleName,Language.getByLangCode("en"));
                        try{
                            int id = localPageDao.getIdByTitle(title);
                            if (id==-1) {
                                nameFails++;}
                            else{mentions.add(id);}
                        } catch (DaoException e){
                            nameFails++;
                        }
                    }
                    else {
                        formatFails++;
                    }
                }
                if (mentions.size()>1){
                    urlToId.put(url,numURLs);
                    for (int mention : mentions.toArray()){
                        if (!pagesToUrls.containsKey(mention)){
                            pagesToUrls.put(mention,new TIntHashSet());
                        }
                        pagesToUrls.get(mention).add(numURLs);
                    }
                    numURLs++;
                }
            }
        }
    }

    void loadDomains (String linkFile) throws IOException{
        FileInputStream fos = new FileInputStream(linkFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fos));
        for (String line = br.readLine(); line!=null; line=br.readLine()){
            if (line.length()>2&&line.substring(0,3).equals("URL")){
                String[] pieces = line.split("\t");
                String domain = extractDomain(pieces[1]);
                for (String mention = br.readLine(); isMention(mention); mention=br.readLine()){
                    String[] mentionPieces = mention.split("\t");
                    String[] urlPieces = mentionPieces[3].split("wikipedia\\.org/wiki/");
                    if (urlPieces.length == 2){
                        String articleName = urlPieces[1];
                        articleName = articleName.split("#")[0];
                        Title title = new Title(articleName,Language.getByLangCode("en"));
                        try{
                            int id = localPageDao.getIdByTitle(title);
                            if (id==-1) {
                                nameFails++;}
                            else{
                                int domainId;
                                if (domainNameToId.containsKey(domain)){
                                    domainId=domainNameToId.get(domain);
                                } else {
                                    domainNameToId.put(domain,numDomains);
                                    domainId=numDomains;
                                    numDomains++;
                                }
                                if (!pagesToDomains.containsKey(id)){
                                    pagesToDomains.put(id,new TIntHashSet());
                                }
                                pagesToDomains.get(id).add(domainId);
                            }
                        } catch (DaoException e){
                            nameFails++;
                        }
                    }
                    else {
                        formatFails++;
                    }
                }
            }
        }
    }

    static void write(String location, Object object) throws IOException {
        new File(dir).mkdirs();
        FileOutputStream fos = new FileOutputStream(dir+location);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
    }

    public static Map<Integer, TIntSet> readMap(String name) throws IOException{
        try {
            FileInputStream fis = new FileInputStream(dir+name);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map<Integer, TIntSet> object = (Map<Integer,TIntSet>) ois.readObject();
            return object;
        } catch (ClassNotFoundException e){
            throw new IOException(e);
        }
    }

    public static Map<Integer,String> readNames(String name) throws IOException{
        try {
            FileInputStream fis = new FileInputStream(dir+name);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map<Integer, String> object = (Map<Integer,String>) ois.readObject();
            return object;
        } catch (ClassNotFoundException e){
            throw new IOException(e);
        }
    }


    public static void main (String[] args) throws IOException, ConfigurationException {
        Options options = new Options();
        EnvBuilder.addStandardOptions(options);
        CommandLineParser parser = new PosixParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("Invalid option usage: " + e.getMessage());
            new HelpFormatter().printHelp("LoadWikilinks", options);
            return;
        }
        Env env = new EnvBuilder(cmd).build();
        Configurator c = env.getConfigurator();

        LoadWikilinks loader = new LoadWikilinks(c.get(LocalPageDao.class));
        String dir = "./wikAPIdia-loader/download/wikilinks/";
        String piece1 = "data-0000";
        String piece2 = "-of-00010";
//      /*
        long start1 = System.currentTimeMillis();
        for (int i=0; i<10; i++){
            loader.loadPages(dir + piece1 + i + piece2);
        }
        System.out.println("urls: "+loader.urlToId.size());
        System.out.println("pages: "+loader.pagesToUrls.size());
        System.out.println("nameFails: "+loader.nameFails);
        System.out.println("formatFails: "+loader.formatFails);
        System.out.println("time: "+(System.currentTimeMillis()-start1));

        write("urlIds", loader.urlToId);
        write("pagesToUrls",loader.pagesToUrls);
//      */
//      /*
        long start2 = System.currentTimeMillis();
        for (int i=0; i<10; i++){
            loader.loadDomains(dir + piece1 + i + piece2);
        }
        System.out.println("domains: "+loader.domainNameToId.size());
        System.out.println("pages: "+loader.pagesToDomains.size());
        System.out.println("time: "+(System.currentTimeMillis()-start2));
        new File("./db/matrix/wikilinks/").mkdirs();
        write("domainIds",loader.domainNameToId);
        write("pagesToDomains",loader.pagesToDomains);
//      */

    }
}
