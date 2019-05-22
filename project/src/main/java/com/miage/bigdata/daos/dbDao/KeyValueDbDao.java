package com.miage.bigdata.daos.dbDao;

import com.miage.bigdata.daos.clientsDao.KeyValueClientDao;
import com.miage.bigdata.daos.itemDao.TodoDao;

public class KeyValueDbDao extends ModelDao implements TodoDao {

    public KeyValueDbDao() {
        this.collectionId = "TestDB";
        this.databaseId = "items";
        this.client = KeyValueClientDao.getDocumentClient();

    }
}
