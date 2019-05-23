package com.miage.bigdata.daos.dbDao;

import com.miage.bigdata.daos.clientsDao.DocumentClientDao;
import com.miage.bigdata.models.Item;
import com.mongodb.MongoClient;
import lombok.NonNull;

import java.util.List;

public class DocumentDbDao<T> extends ModelDbDao {

    private MongoClient client;

    public DocumentDbDao(String collectionId, String databaseId) {
        this.collectionId = collectionId; //"items";
        this.databaseId = databaseId; //"TestDB";
        client = new DocumentClientDao().getClient();
    }


    @Override
    public String getDatabaseID() {
        return null;
    }

    @Override
    public List<T> readAll() {
        return null;
    }

    @Override
    public T create(Object item) {
        return null;
    }

    @Override
    public Item getByID(@NonNull String id) {
        return null;
    }

    @Override
    public boolean delete(@NonNull String id) {
        return false;
    }

    @Override
    public Item update(@NonNull Item item) {
        return null;
    }

    @Override
    public String generateID() {
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
}
