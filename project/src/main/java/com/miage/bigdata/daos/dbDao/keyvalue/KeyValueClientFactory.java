package com.miage.bigdata.daos.dbDao.keyvalue;

import com.miage.bigdata.utils.Configuration;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTableClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

public class KeyValueClientFactory {
    private static CloudTableClient cloudTableClient;

    public static CloudTableClient getKeyValueClient() {
        if (cloudTableClient == null) {

            CloudStorageAccount storageAccount = null;
            try {
                String connectionString = Configuration.KEY_VALUE.getProperty("connection");
                System.out.println(connectionString);
                storageAccount = CloudStorageAccount.parse(connectionString);
            } catch (URISyntaxException | InvalidKeyException | IOException e) {
                e.printStackTrace();
            }

            cloudTableClient = storageAccount != null ? storageAccount.createCloudTableClient() : null;
        }

        return cloudTableClient;
    }

}
