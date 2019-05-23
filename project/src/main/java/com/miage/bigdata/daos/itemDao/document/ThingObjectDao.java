package com.miage.bigdata.daos.itemDao.document;

import com.miage.bigdata.daos.dbDao.document.DocumentDbDao;
import com.miage.bigdata.models.document.ThingItem;

public class ThingObjectDao extends DocumentObjectDao<ThingItem> {
    public ThingObjectDao(DocumentDbDao dbDao) {
        super(dbDao);
    }

    @Override
    public String getCollectionName() {
        return "things";
    }

    @Override
    protected String generateID() {
        return null;
    }

    @Override
    public boolean createTable() {
        return false;
    }

    @Override
    public boolean populateTable() {
        return false;
    }

    @Override
    public boolean deleteTable() {
        return false;
    }

    @Override
    protected Class<ThingItem> getItemClass() {
        return ThingItem.class;
    }
}
