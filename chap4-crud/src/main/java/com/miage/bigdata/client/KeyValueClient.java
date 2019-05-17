package com.miage.bigdata.client;

import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.DocumentClient;

public class KeyValueClient {
    private static final String HOST = "https://db-keyvalue.documents.azure.com:443/";
    private static final String MASTER_KEY = "cowNe6kZJssatZ7a41CJE8CTRAKyGpMsdUjcq5xXyxGSmD9Pn6yproGIZ02gd93udHLSvqTav94K0VNsEvcdGQ==";

    private static DocumentClient keyValueClient;

    public static DocumentClient getDocumentClient() {
        if (keyValueClient == null) {
            keyValueClient = new DocumentClient(HOST, MASTER_KEY, ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
        }

        return keyValueClient;
    }

}
