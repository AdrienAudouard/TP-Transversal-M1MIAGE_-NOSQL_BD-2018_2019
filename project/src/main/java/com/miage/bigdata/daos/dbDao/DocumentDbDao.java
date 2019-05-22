package com.miage.bigdata.daos.dbDao;

import com.miage.bigdata.daos.itemDao.TodoDao;

public class DocumentDbDao extends ModelDao implements TodoDao {

    public DocumentDbDao() {
        this.collectionId = "TestDB";
        this.databaseId = "items";
    }
}
