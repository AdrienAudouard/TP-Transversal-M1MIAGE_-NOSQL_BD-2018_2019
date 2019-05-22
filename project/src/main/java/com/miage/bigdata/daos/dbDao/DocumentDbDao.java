package com.miage.bigdata.daos.dbDao;

import com.miage.bigdata.daos.clientsDao.DocumentClientDao;
import com.miage.bigdata.daos.itemDao.ThingDao;
import com.miage.bigdata.models.Thing;
import com.mongodb.MongoClient;

import java.util.List;

public class DocumentDbDao extends ModelDao<Thing> implements ThingDao {

    private static MongoClient client;

    public DocumentDbDao() {
        collectionId = "items";
        databaseId = "TestDB";
        client = new DocumentClientDao().getClient();
    }

    @Override
    public Thing createItem(Thing item) {
        return null;
    }

    @Override
    public Thing readItem(String id) {
        return null;
    }

    @Override
    public List<Thing> readItems() {
        return null;
    }

    @Override
    public Thing updateItem(Thing item) {
        return null;
    }

    @Override
    public boolean deleteItem(String id) {
        return false;
    }
}
