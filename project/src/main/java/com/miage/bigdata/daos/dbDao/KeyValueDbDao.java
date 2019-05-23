package com.miage.bigdata.daos.dbDao;

import com.microsoft.azure.documentdb.DocumentClient;

public class KeyValueDbDao extends DbDao<DocumentClient> {
    private DocumentClient documentClient;


    @Override
    public DocumentClient connect() {
        this.documentClient = KeyValueClientFactory.getDocumentClient();
        return this.documentClient;
    }

    @Override
    boolean isConnected() {
        return documentClient != null;
    }


}
