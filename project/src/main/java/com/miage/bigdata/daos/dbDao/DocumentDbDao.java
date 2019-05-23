package com.miage.bigdata.daos.dbDao;

import com.microsoft.azure.documentdb.DocumentClient;

public class DocumentDbDao extends DbDao<DocumentClient> {
    private DocumentClient documentClient;


    @Override
    public DocumentClient connect() {
        this.documentClient = DocumentClientFactory.getDocumentClient();
        return this.documentClient;
    }

    @Override
    boolean isConnected() {
        return documentClient != null;
    }


}
