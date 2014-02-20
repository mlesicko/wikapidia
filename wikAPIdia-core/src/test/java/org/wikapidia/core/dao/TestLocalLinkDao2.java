package org.wikapidia.core.dao;

import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;
import org.junit.Test;

import static org.junit.Assert.*;

import org.wikapidia.core.dao.matrix.MatrixLocalLinkDao;
import org.wikapidia.core.dao.sql.LocalLinkSqlDao;
import org.wikapidia.core.dao.sql.TestDaoUtil;
import org.wikapidia.core.dao.sql.WpDataSource;
import org.wikapidia.core.lang.Language;
import org.wikapidia.core.model.LocalLink;

import java.io.File;
import java.io.IOException;

public class TestLocalLinkDao2 {

    public static final int STARTING_ID = 1986751;

    @Test
    public void testLink() throws ClassNotFoundException, IOException, DaoException {
        WpDataSource ds = TestDaoUtil.getWpDataSource();

        Language lang = Language.getByLangCode("simple");
        LocalLinkSqlDao sqlDao = new LocalLinkSqlDao(ds);
        MatrixLocalLinkDao matrixDao = new MatrixLocalLinkDao(sqlDao, new File(".tmp/links"));
        matrixDao.beginLoad();

        for (int i = 1; i <= 200; i++) {
            for (int j = i+1; j <= 200; j++) {
                LocalLink link = new LocalLink(
                        lang,
                        "I am an anchor text",
                        i+ STARTING_ID,
                        j+ STARTING_ID,
                        true,
                        0,
                        true,
                        LocalLink.LocationType.FIRST_PARA
                );
                matrixDao.save(link);
            }
        }
        matrixDao.endLoad();

        for (int i = 1; i <= 200; i++) {
            TIntSet ids = new TIntHashSet();
            for (LocalLink link : sqlDao.getLinks(lang, i+ STARTING_ID, true)) {
                ids.add(link.getDestId());
                assert(link.isOutlink());
            }
            assertEquals(ids, rangeSet(i + 1 + STARTING_ID, 200 + STARTING_ID));

            ids.clear();
            for (LocalLink link : sqlDao.getLinks(lang, i+ STARTING_ID, false)) {
                ids.add(link.getSourceId());
                assert(!link.isOutlink());
            }
            assertEquals(ids, rangeSet(1 + STARTING_ID, i + STARTING_ID - 1));
        }
    }

    /**
     * returns a set containing the numbers in a range (inclusive)
     * @param from
     * @param to
     * @return
     */
    private TIntSet rangeSet(int from, int to) {
        TIntSet set = new TIntHashSet();
        for (int i = from; i <= to; i++) {
            set.add(i);
        }
        return set;
    }
}
