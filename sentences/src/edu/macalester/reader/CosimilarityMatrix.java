package edu.macalester.reader;
import org.wikapidia.conf.ConfigurationException;
import org.wikapidia.conf.Configurator;
import org.wikapidia.core.cmd.Env;
import org.wikapidia.core.cmd.EnvBuilder;
import org.wikapidia.core.dao.DaoException;
import org.wikapidia.core.lang.Language;
import org.wikapidia.sr.LocalSRMetric;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CosimilarityMatrix{
    private boolean matrixBuilt;
    private double[][] matrix;
    private Set<String> words;
    private Map<String,Integer> matrixLookUp;
    LocalSRMetric metric;
    Language language;


    public CosimilarityMatrix(){
        words = new HashSet<String>();
        matrixLookUp = new HashMap<String, Integer>();
        matrixBuilt=false;
        language = Language.getByLangCode("simple");

        try{
            Env env = new EnvBuilder().build();
            Configurator c = env.getConfigurator();
            metric = c.get(LocalSRMetric.class,"ESA");
        } catch (ConfigurationException e){
            throw new RuntimeException("There's a problem with the WikAPIdia configuration");
        }
    }

    /**
     * Add new words to the set from a file
     * @param file
     */
    public void addWords(String file){
        BufferedReader reader = null;
        try{
            String line = null;
            reader = new BufferedReader(new FileReader(file));

            while ((line = reader.readLine()) != null) {
                String[] pieces = line.split(" ");
                for (String piece : pieces){
                    words.add(piece.toLowerCase());
                }
            }
        } catch (IOException e){

        }
    }

    /**
     * Build up the cosimilarity matrix
     */
    public void buildMatrix(){
        List<String> wordList = new ArrayList<String>();
        int i=0;
        for (String word : words){
            wordList.add(word);
            matrixLookUp.put(word,i);
            i++;
        }
        try{
            matrix = metric.cosimilarity(wordList.toArray(new String[wordList.size()]),language);
            matrixBuilt=true;
        }catch (DaoException e){
            throw new RuntimeException("WikAPIdia experienced a DAO error.");
        }
    }

    /**
     * get an sr score between two words
     * @param word1
     * @param word2
     * @return
     */
    public double sr(String word1, String word2){
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();
        if (matrixBuilt){
            if (matrixLookUp.containsKey(word1)&&matrixLookUp.containsKey(word2)){
                return matrix[matrixLookUp.get(word1)][matrixLookUp.get(word2)];
            } else{
                if (!matrixLookUp.containsKey(word1)){
                    throw new IllegalStateException("Matrix built but does not contain "+word1);
                }else{
                    throw new IllegalStateException("Matrix built but does not contain "+word2);
                }
            }
        } else{
            try{
                return metric.similarity(word1,word2,language,false).getScore();
            }catch (DaoException e){
                throw new RuntimeException("WikAPIdia experienced a DAO error.");
            }
        }
    }

}
