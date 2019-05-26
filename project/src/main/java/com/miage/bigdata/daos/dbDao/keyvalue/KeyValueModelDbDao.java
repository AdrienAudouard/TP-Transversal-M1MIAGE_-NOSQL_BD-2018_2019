package com.miage.bigdata.daos.dbDao.keyvalue;

import com.miage.bigdata.daos.dbDao.ModelDbDao;
import com.microsoft.azure.storage.table.CloudTableClient;

public class KeyValueModelDbDao extends ModelDbDao<CloudTableClient> {
    @Override
    public CloudTableClient connect() {
        if (client != null) {
            return client;
        }
        this.client = KeyValueClientFactory.getKeyValueClient();
        return this.client;
    }

    @Override
    public boolean isConnected() {
        return client != null;
    }

}
