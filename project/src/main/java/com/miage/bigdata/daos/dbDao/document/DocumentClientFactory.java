package com.miage.bigdata.daos.dbDao.document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

class DocumentClientFactory {
    private static final String connectionString = "mongodb://db-document:eJR9JKQ2ENDewW1cz2w7cRs8DNrEEYX18LRs4GyFvtCXEdDBhVkzI2EWsN3Ni4QIvxAO5EWYoccvLacPEDC7Qw==@db-document.documents.azure.com:10255/?ssl=true&replicaSet=globaldb";
    private static MongoClient mongoClient;

    static MongoClient getMongoClient() {
        if (mongoClient == null) {
            MongoClientURI mongoClientURI = new MongoClientURI(connectionString);
            mongoClient = new MongoClient(mongoClientURI);
        }

        return mongoClient;
    }
}
