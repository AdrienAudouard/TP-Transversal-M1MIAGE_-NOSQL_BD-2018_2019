package com.miage.bigdata.daos.dbDao.keyvalue;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTableClient;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

public class KeyValueClientFactory {
    private static final String CONNECTION_STRING = "DefaultEndpointsProtocol=https;AccountName=db-keyvalue;AccountKey=Zs0ZQ0Z6UDPmaacRYePk94eo3aS6V3BUU81x5Yb46WXAUHEB22cTEd0vkmaq3Jo7sIb6cH3GZMg2GoNRScvSgw==;TableEndpoint=https://db-keyvalue.table.cosmos.azure.com:443/;";

    private static CloudTableClient cloudTableClient;

    public static CloudTableClient getKeyValueClient() {
        if (cloudTableClient == null) {

            CloudStorageAccount storageAccount = null;
            try {
                storageAccount = CloudStorageAccount.parse(CONNECTION_STRING);
            } catch (URISyntaxException | InvalidKeyException e) {
                e.printStackTrace();
            }

            cloudTableClient = storageAccount != null ? storageAccount.createCloudTableClient() : null;
        }

        return cloudTableClient;
    }

}
