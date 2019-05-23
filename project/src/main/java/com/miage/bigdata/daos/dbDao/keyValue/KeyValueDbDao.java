package com.miage.bigdata.daos.dbDao.keyValue;

import com.miage.bigdata.daos.dbDao.DbDao;
import com.microsoft.azure.documentdb.DocumentClient;

public class KeyValueDbDao extends DbDao<DocumentClient> {
    private DocumentClient documentClient;


    @Override
    public DocumentClient connect() {
        this.documentClient = KeyValueClientFactory.getDocumentClient();
        return this.documentClient;
    }

    @Override
    public boolean isConnected() {
        return documentClient != null;
    }


}
