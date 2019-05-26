package com.miage.bigdata.daos.dbDao.relational;

import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.DocumentClient;

public class RelationalClientFactory {
    private static final String HOST = "https://db-sql.documents.azure.com:443/";
    private static final String MASTER_KEY = "I56cDiKmjV0nuKlYm96svd5Rrn4mKPaL2NfmPWXN0bcLNdr6xEiMwiocDwpWU4D6fEDTGY8TtfXMlclepTapOQ==";

    private static DocumentClient documentClient;

    public static DocumentClient getDocumentClient() {
        if (documentClient == null) {
            documentClient = new DocumentClient(HOST, MASTER_KEY,
                    ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
        }

        return documentClient;
    }

}
