package com.miage.bigdata.daos.dbDao.relational;

import com.miage.bigdata.daos.dbDao.ModelDbDao;
import com.microsoft.azure.documentdb.DocumentClient;

public class RelationalModelDbDao extends ModelDbDao<DocumentClient> {
    @Override
    public DocumentClient connect() {
        if (client != null) {
            return client;
        }
        this.client = RelationalClientFactory.getDocumentClient();
        return this.client;
    }

    @Override
    public boolean isConnected() {
        return client != null;
    }


}
