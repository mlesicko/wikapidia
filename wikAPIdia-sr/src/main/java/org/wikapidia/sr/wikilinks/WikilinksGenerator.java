package org.wikapidia.sr.wikilinks;

import com.typesafe.config.Config;
import edu.emory.mathcs.backport.java.util.Arrays;
import gnu.trove.map.TIntFloatMap;
import gnu.trove.map.hash.TIntFloatHashMap;
import gnu.trove.set.TIntSet;
import org.wikapidia.conf.Configuration;
import org.wikapidia.conf.ConfigurationException;
import org.wikapidia.conf.Configurator;
import org.wikapidia.core.dao.DaoException;
import org.wikapidia.core.model.LocalPage;
import org.wikapidia.sr.Explanation;
import org.wikapidia.sr.SRResult;
import org.wikapidia.sr.SRResultList;
import org.wikapidia.sr.utils.Leaderboard;
import org.wikapidia.sr.vector.VectorGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Matt Lesicko
 */
public class WikilinksGenerator implements VectorGenerator {
    boolean fullUrls; //If false, uses domains
    Map<Integer,TIntSet> pages;
    Map<Integer,String> urlNames; //For explanations

    public WikilinksGenerator(Map<Integer,TIntSet> pages, Map<Integer,String> urlNames, boolean fullUrls){
        this.fullUrls=fullUrls;
        this.pages=pages;
        this.urlNames=urlNames;
    }

    @Override
    public TIntFloatMap getVector(int pageId) throws DaoException {
        TIntFloatMap vector = new TIntFloatHashMap();
        if (!pages.containsKey(pageId)){
            return null;
        }
        for (Integer i: pages.get(pageId).toArray()){
            vector.put(i,1);
        }
        return vector;
    }

    @Override
    public TIntFloatMap getVector(String phrase) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Explanation> getExplanations(LocalPage page1, LocalPage page2, TIntFloatMap vector1, TIntFloatMap vector2, SRResult result) throws DaoException {
        Leaderboard lb = new Leaderboard(5);    // TODO: make 5 configurable
        for (int id : vector1.keys()) {
            if (vector2.containsKey(id)) {
                lb.insert(id, vector1.get(id) * vector2.get(id));
            }
        }
        SRResultList top = lb.getTop();
        if (top.numDocs() == 0) {
            return java.util.Arrays.asList(new Explanation("? and ? share no links", page1, page2));
        }
        top.sortDescending();

        List<Explanation> explanations = new ArrayList<Explanation>();
        for (int i = 0; i < top.numDocs(); i++) {
            String url = urlNames.get(top.getId(i));
            explanations.add(new Explanation("? links to both ? and ?", url, page1, page2));
        }
        return explanations;
    }

    public static class Provider extends org.wikapidia.conf.Provider<VectorGenerator>{
        public Provider(Configurator configurator, Configuration config) throws ConfigurationException {
            super(configurator, config);
        }

        @Override
        public Class getType(){
            return VectorGenerator.class;
        }

        @Override
        public String getPath() {
            return "sr.metric.generator";
        }

        @Override
        public VectorGenerator get(String name, Config config, Map<String,String> runtimeParams) throws ConfigurationException {
            if (!config.getString("type").equals("wikilinks")){
                return null;
            }
            if (!runtimeParams.containsKey("language")){
                throw new IllegalArgumentException("Monolingual SR Metric requires 'language' runtime parameter");
            }
            if (!runtimeParams.get("language").equals("en")){
                throw new IllegalArgumentException("Wikilinks metric only supports English.");
            }
            boolean fullUrls = config.getBoolean("fullurls");
            Map<Integer,TIntSet> pages;
            Map<Integer,String> names;
            try {
                if (fullUrls){
                    pages = LoadWikilinks.readMap("pagesToUrls");
                    names = LoadWikilinks.readNames("urlIds");
                } else {
                    pages = LoadWikilinks.readMap("pagesToDomains");
                    names = LoadWikilinks.readNames("domainIds");
                }
            }catch (IOException e){
                throw new ConfigurationException(e);
            }
            return new WikilinksGenerator(pages,names,fullUrls);
        }
    }
}
