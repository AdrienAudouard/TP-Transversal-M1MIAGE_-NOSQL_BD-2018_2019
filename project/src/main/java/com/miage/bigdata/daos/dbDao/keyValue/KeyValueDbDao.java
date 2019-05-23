package com.miage.bigdata.daos.dbDao.keyValue;

import com.miage.bigdata.daos.dbDao.DbDao;
import com.microsoft.azure.documentdb.DocumentClient;

public class KeyValueDbDao extends DbDao<DocumentClient> {
    @Override
    public DocumentClient connect() {
        this.client = KeyValueClientFactory.getDocumentClient();
        return this.client;
    }

    @Override
    public boolean isConnected() {
        return client != null;
    }


}
