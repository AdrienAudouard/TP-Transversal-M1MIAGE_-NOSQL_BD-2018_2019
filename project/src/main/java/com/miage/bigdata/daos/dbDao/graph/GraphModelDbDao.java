package com.miage.bigdata.daos.dbDao.graph;

import com.miage.bigdata.daos.dbDao.ModelDbDao;
import org.apache.tinkerpop.gremlin.driver.Client;

public class GraphModelDbDao extends ModelDbDao<Client> {
    @Override
    public Client connect() {
        if (client == null) {
            client = GraphClientFactory.getClient();
        }

        return client;
    }

    @Override
    public boolean isConnected() {
        return client != null;
    }
}
