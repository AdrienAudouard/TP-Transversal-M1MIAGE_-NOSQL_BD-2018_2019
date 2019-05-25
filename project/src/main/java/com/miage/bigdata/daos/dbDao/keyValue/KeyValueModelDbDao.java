package com.miage.bigdata.daos.dbDao.keyValue;

import com.miage.bigdata.daos.dbDao.ModelDbDao;
import com.microsoft.azure.documentdb.DocumentClient;

public class KeyValueModelDbDao extends ModelDbDao<DocumentClient> {
    @Override
    public DocumentClient connect() {
        if (client != null) {
            return client;
        }
        this.client = KeyValueClientFactory.getDocumentClient();
        return this.client;
    }

    @Override
    public boolean isConnected() {
        return client != null;
    }


}
