package org.wikapidia.phrases;

import org.wikapidia.core.dao.DaoException;
import org.wikapidia.core.lang.Language;
import org.wikapidia.core.lang.LanguageSet;
import org.wikapidia.core.model.LocalPage;
import org.wikapidia.core.model.UniversalPage;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Given a page, returns the most common phrases
 */
public interface PhraseAnalyzer {

    /**
     * Loads a specific corpus into the dao.
     *
     * @throws DaoException
     * @throws IOException
     */
    void loadCorpus(LanguageSet langs) throws DaoException, IOException;

    /**
     * Returns the most descriptive phrases for a wikipedia page.
     * @param language The language for the phrase and the returned LocalPages.
     * @param page The page to be described.
     * @param maxPhrases The maximum number of phrases to be returned.
     * @return An map from phrase to score, ordered by decreasing probability.
     * The scores can be considered probabilities that sum to 1.0 across all possibilities.
     */
    public LinkedHashMap<String, Float> describe(Language language, LocalPage page, int maxPhrases) throws DaoException;

    /**
     * Returns the most likely wikipedia pages for a phrase.
     * @param language The language for the phrase and the returned LocalPages.
     * @param phrase The phrase to be resolved.
     * @param maxPages The maximum number of pages to be returned.
     * @return An map from page to score, ordered by decreasing probability.
     * The scores can be considered probabilities that sum to 1.0 across all possibilities.
     */
    public LinkedHashMap<LocalPage, Float> resolve(Language language, String phrase, int maxPages) throws DaoException;

}
