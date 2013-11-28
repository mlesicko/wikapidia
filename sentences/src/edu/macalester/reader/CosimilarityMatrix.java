package edu.macalester.reader;
import gnu.trove.map.hash.TIntDoubleHashMap;
import org.wikapidia.conf.ConfigurationException;
import org.wikapidia.conf.Configurator;
import org.wikapidia.core.cmd.Env;
import org.wikapidia.core.cmd.EnvBuilder;
import org.wikapidia.core.dao.DaoException;
import org.wikapidia.core.lang.Language;
import org.wikapidia.sr.LocalSRMetric;
import org.wikapidia.sr.esa.ESAMetric;
import org.wikapidia.sr.utils.SimUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CosimilarityMatrix{
    private boolean matrixBuilt;
    private double[][] matrix;
    private Set<String> words;
    private Map<String,Integer> matrixLookUp;
    private TIntDoubleHashMap[] wordVectors;
    ESAMetric metric;
    Language language;


    public CosimilarityMatrix(){
        words = new HashSet<String>();
        matrixLookUp = new HashMap<String, Integer>();
        matrixBuilt=false;
        language = Language.getByLangCode("simple");

        try{
            Env env = new EnvBuilder().build();
            Configurator c = env.getConfigurator();
            metric = (ESAMetric)c.get(LocalSRMetric.class,"ESA");
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

    public void buildMatrix(){
        buildLazyMatrix();
    }

    private void buildNoMatrix(){
        try{
            wordVectors=new TIntDoubleHashMap[words.size()];
            int i=0;
            for (String word : words){
                matrixLookUp.put(word,i);
                wordVectors[i]=metric.getVector(word,language);
                i++;
            }
        } catch (DaoException e){
            throw new RuntimeException("WikAPIdia experienced a DAO error.");
        }
    }

    /**
     * Build up the cosimilarity matrix
     */
    private void buildMatrixNow(){
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

    private void buildLazyMatrix(){
        try{
            matrix = new double[words.size()][words.size()];
            for (int i=0; i< words.size(); i++){
                for (int j=0; j<words.size(); j++){
                    matrix[i][j]=-1;
                }
            }

            wordVectors=new TIntDoubleHashMap[words.size()];
            int i=0;
            for (String word : words){
                matrixLookUp.put(word,i);
                wordVectors[i]=metric.getVector(word,language);
                i++;
            }
        } catch (DaoException e){
            throw new RuntimeException("WikAPIdia experienced a DAO error.");
        }

    }

    public double sr(String word1, String word2){
        return srLazy(word1,word2);
    }

    /**
     * get an sr score between two words
     * @param word1
     * @param word2
     * @return
     */
    private double srNow(String word1, String word2){
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

    private double srLazy(String word1, String word2){
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();
        if (matrixBuilt){
            if (matrixLookUp.containsKey(word1)&&matrixLookUp.containsKey(word2)){
                int id1 = matrixLookUp.get(word1);
                int id2 = matrixLookUp.get(word2);
                if (matrix[id1][id2]==-1){
                    matrix[id1][id2]= SimUtils.cosineSimilarity(wordVectors[id1],wordVectors[id2]);
                }
                return matrix[id1][id2];
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

    private double srNoMatrix(String word1, String word2){
        //TODO: implement
        int id1;
        int id2;
        return SimUtils.cosineSimilarity(null, null);
    }

}
