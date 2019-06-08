package com.miage.bigdata.daos.itemDao.graph;

import com.miage.bigdata.daos.dbDao.graph.GraphModelDbDao;
import com.miage.bigdata.models.graph.Tag;

public class TagObjectDao extends GraphObjectDao<Tag> {
    public TagObjectDao(GraphModelDbDao dbDao) {
        super(dbDao);
    }

    @Override
    protected String getDatabaseID() {
        return "tag";
    }

    @Override
    public String generateID() {
        return null;
    }

    @Override
    public boolean populateTable() {
        return false;
    }

    @Override
    protected Class<Tag> getItemClass() {
        return Tag.class;
    }
}
