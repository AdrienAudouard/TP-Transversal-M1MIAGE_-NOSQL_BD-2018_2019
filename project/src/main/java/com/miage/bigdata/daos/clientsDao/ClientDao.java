package com.miage.bigdata.daos.clientsDao;

import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.DocumentClient;

public abstract class ClientDao {
    protected static String host;
    protected static String masterKey;

    private static DocumentClient client;

    public static DocumentClient getDocumentClient() {
        if (client == null) {
            client = new DocumentClient(host, masterKey,
                    ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
        }

        return client;
    }
}
