package org.wikapidia.core.dao;

import com.jolbox.bonecp.BoneCPDataSource;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.wikapidia.core.jooq.Tables;
import org.wikapidia.core.model.Link;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class LinkDao {

    private BoneCPDataSource bds;

    public LinkDao(BoneCPDataSource bds) {
        this.bds = bds;
    }

    public Link get(int lId) {
        try{
            Connection conn = bds.getConnection();
            DSLContext context = DSL.using(conn, SQLDialect.H2);
            Record record = context.select().from(Tables.LINK).where(Tables.LINK.ARTICLE_ID.equal(lId)).fetchOne();
            Link l = new Link(
                    record.getValue(Tables.LINK.TEXT),
                    record.getValue(Tables.LINK.ARTICLE_ID),
                    false
            );
            conn.close();
            return l;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Link> query(String lText) {
        try{
            Connection conn = bds.getConnection();
            DSLContext context = DSL.using(conn, SQLDialect.H2);
            Result<Record> result = context.select().from(Tables.LINK).where(Tables.LINK.TEXT.likeIgnoreCase(lText)).fetch();
            conn.close();
            return buildLinks(result);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void save(Link link) {
        try{
            Connection conn = bds.getConnection();
            DSLContext context = DSL.using(conn, SQLDialect.H2);
            context.insertInto(Tables.LINK, Tables.LINK.ARTICLE_ID, Tables.LINK.TEXT).values(
                    link.getId(),
                    link.getText()
            );
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private ArrayList<Link> buildLinks(Result<Record> result){
        ArrayList<Link> links = null;
        for (Record record: result){
            Link a = new Link(
                    record.getValue(Tables.LINK.TEXT),
                    record.getValue(Tables.LINK.ARTICLE_ID),
                    false
            );
            links.add(a);
        }
        return links;
    }

}