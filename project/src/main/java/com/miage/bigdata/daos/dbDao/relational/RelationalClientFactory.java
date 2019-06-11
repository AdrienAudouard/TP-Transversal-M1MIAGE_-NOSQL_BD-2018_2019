package com.miage.bigdata.daos.dbDao.relational;

import com.miage.bigdata.utils.Configuration;
import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.DocumentClient;

import java.io.IOException;

public class RelationalClientFactory {
    private static DocumentClient documentClient;

    public static DocumentClient getDocumentClient() {
        try {
            String host = Configuration.RELATIONAL.getProperty("host");
            String masterKey = Configuration.RELATIONAL.getProperty("masterkey");

            if (documentClient == null) {
                documentClient = new DocumentClient(host, masterKey,
                        ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return documentClient;
    }

}
