package com.miage.bigdata.daos.dbDao.graph;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;

import java.io.File;
import java.io.FileNotFoundException;

public class GraphClientFactory {
    private static Client client;

    public static Client getClient() {
        if (client == null) {
            try {
                Cluster cluster = Cluster.build(new File("src/main/resources/graph.yaml")).create();
                client = cluster.connect();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return client;
    }

}
