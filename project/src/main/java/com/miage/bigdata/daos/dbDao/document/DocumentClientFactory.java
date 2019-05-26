package com.miage.bigdata.daos.dbDao.document;

import com.miage.bigdata.util.Configuration;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.io.IOException;

class DocumentClientFactory {
    private static MongoClient mongoClient;

    static MongoClient getMongoClient() {
        Configuration configuration = Configuration.DOCUMENT;

        String connectionString = null;
        try {
            connectionString = configuration.getProperty("connection");
            if (mongoClient == null) {
                MongoClientURI mongoClientURI = new MongoClientURI(connectionString);
                mongoClient = new MongoClient(mongoClientURI);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mongoClient;
    }
}
