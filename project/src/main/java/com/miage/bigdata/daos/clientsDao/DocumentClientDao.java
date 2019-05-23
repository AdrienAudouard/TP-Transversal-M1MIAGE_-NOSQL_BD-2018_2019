package com.miage.bigdata.daos.clientsDao;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DocumentClientDao extends ClientDao<MongoClient> {

    private static String HOST = "mongodb://db-document:eJR9JKQ2ENDewW1cz2w7cRs8DNrEEYX18LRs4GyFvtCXEdDBhVkzI2EWsN3Ni4QIvxAO5EWYoccvLacPEDC7Qw==@db-document.documents.azure.com:10255/?ssl=true&replicaSet=globaldb";
    private static MongoClient client = null;

    @Override
    public MongoClient getClient() {
        MongoClientURI uri = new MongoClientURI(HOST);
        client = new MongoClient(uri);
        return client;
    }
}
