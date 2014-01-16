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
    Map<Integer,TIntSet> urls;
    Map<String,TIntSet> domains;
    Map<Integer,TIntSet> pagesToUrls;
    Map<Integer,Set<String>> pagesToDomains;
    int numURLs;
    int nameFails;
    int formatFails;
    LocalPageDao localPageDao;

    public LoadWikilinks(LocalPageDao localPageDao){
        urls=new HashMap<Integer,TIntSet>();
        domains=new HashMap<String,TIntSet>();
        pagesToDomains = new HashMap<Integer, Set<String>>();
        pagesToUrls = new HashMap<Integer, TIntSet>();
        numURLs =0;
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
                    urls.put(numURLs,mentions);
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
                                if (!domains.containsKey(domain)){
                                    domains.put(domain,new TIntHashSet());
                                }
                                domains.get(domain).add(id);
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

    void cleanDomains (){
        String[] A= new String[0];
        String[] keys = domains.keySet().toArray(A);
        for (String key : keys){
            if (domains.get(key).size()<2){
                domains.remove(key);
            } else {
                for (int id : domains.get(key).toArray()){
                    if (!pagesToDomains.containsKey(id)){
                        pagesToDomains.put(id,new HashSet<String>());
                    }
                    pagesToDomains.get(id).add(key);
                }

            }
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
        System.out.println("urls: "+loader.urls.size());
        System.out.println("pages: "+loader.pagesToUrls.size());
        System.out.println("nameFails: "+loader.nameFails);
        System.out.println("formatFails: "+loader.formatFails);
        System.out.println("time: "+(System.currentTimeMillis()-start1));
//      */
//      /*
        long start2 = System.currentTimeMillis();
        for (int i=0; i<10; i++){
            loader.loadDomains(dir + piece1 + i + piece2);
        }
        loader.cleanDomains();
        System.out.println("domains: "+loader.domains.size());
        System.out.println("pages: "+loader.pagesToDomains.size());
        System.out.println("time: "+(System.currentTimeMillis()-start2));
//      */
    }
}
