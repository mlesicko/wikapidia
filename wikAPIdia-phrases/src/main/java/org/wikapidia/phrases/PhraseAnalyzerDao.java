package org.wikapidia.phrases;

import org.wikapidia.core.dao.DaoException;
import org.wikapidia.core.lang.Language;
import org.wikapidia.core.lang.StringNormalizer;
import org.wikapidia.phrases.PrunedCounts;

/**
 * Stores and retrieves information related to phrase to page relationships.
 * @author Shilad Sen
 */
public interface PhraseAnalyzerDao {

    /**
     * Adds information mapping a page to phrases.
     * Multiple invocations of the method with the same page override counts.
     * @param lang
     * @param wpId
     * @param counts
     * @throws org.wikapidia.core.dao.DaoException
     */
    public void savePageCounts(Language lang, int wpId, PrunedCounts<String> counts) throws DaoException;

    /**
     * Adds information mapping a phrase to pages.
     * Phrases are normalized, and phrases that normalize to the same string are
     * treated as identical. Multiple invocations of the method with the same phrase
     * override counts.
     * @param lang
     * @param phrase
     * @param counts
     * @throws org.wikapidia.core.dao.DaoException
     */
    public void savePhraseCounts(Language lang, String phrase, PrunedCounts<Integer> counts) throws DaoException;

    /**
     * @return The string normalizer used to determine canonical string representations.
     */
    public StringNormalizer getStringNormalizer();

    /**
     * Gets pages related to a phrase. Phrases are normalized before looking them up.
     * @param lang
     * @param phrase
     * @return Map from page ids (in the local language) to the number of occurrences
     * ordered by decreasing count.
     * @throws DaoException
     */
    public PrunedCounts<Integer> getPhraseCounts(Language lang, String phrase, int maxPages) throws DaoException;

    /**
     * Gets phrases related to a page.
     * @param lang
     * @param wpId Local page id
     * @return Map from phrasese (in the local language) to the number of occurrences
     * ordered by decreasing count.
     * @throws DaoException
     */
    public PrunedCounts<String> getPageCounts(Language lang, int wpId, int maxPhrases) throws DaoException;

    /**
     * Closes the dao and flushes any unwritten data to disk.
     * @throws DaoException
     */
    public void close() throws DaoException;
}
