package com.miage.bigdata.daos.clientsDao;

import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;

public class KeyValueClientDao extends ClientDao<DocumentClient> {

    private static String HOST = "https://db-keyvalue.documents.azure.com:443/";
    private static String MASTER_KEY = "cowNe6kZJssatZ7a41CJE8CTRAKyGpMsdUjcq5xXyxGSmD9Pn6yproGIZ02gd93udHLSvqTav94K0VNsEvcdGQ==";
    private static DocumentClient client = null;

    @Override
    public DocumentClient getClient() {
        if (client == null) {
            client = new DocumentClient(HOST, MASTER_KEY,
                    ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
        }

        return client;
    }

}
