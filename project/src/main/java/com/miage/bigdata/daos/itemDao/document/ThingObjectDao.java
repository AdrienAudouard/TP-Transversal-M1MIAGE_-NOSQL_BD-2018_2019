package com.miage.bigdata.daos.itemDao.document;

import com.miage.bigdata.daos.dbDao.document.DocumentDbDao;
import com.miage.bigdata.models.document.ThingItem;
import com.mongodb.client.MongoCollection;

public class ThingObjectDao extends DocumentObjectDao<ThingItem> {
    public ThingObjectDao(DocumentDbDao dbDao) {
        super(dbDao);
    }

    @Override
    public String getCollectionName() {
        return "things";
    }

    @Override
    public boolean createTable() {
        this.getDatabase().createCollection(getCollectionName());
        return true;
    }

    @Override
    public boolean populateTable() {
        return false;
    }

    @Override
    public boolean deleteTable() {
        MongoCollection a = getCollection();

        if (a != null) {
            a.drop();
            return true;
        }

        return false;
    }

    @Override
    protected Class<ThingItem> getItemClass() {
        return ThingItem.class;
    }
}
