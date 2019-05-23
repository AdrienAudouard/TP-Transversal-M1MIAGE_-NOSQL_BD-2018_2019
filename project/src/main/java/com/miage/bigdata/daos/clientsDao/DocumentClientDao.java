package com.miage.bigdata.daos.clientsDao;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DocumentClientDao extends ClientDao<MongoClient> {

    private static String HOST = "mongodb://db-document:eJR9JKQ2ENDewW1cz2w7cRs8DNrEEYX18LRs4GyFvtCXEdDBhVkzI2EWsN3Ni4QIvxAO5EWYoccvLacPEDC7Qw==@db-document.documents.azure.com:10255/?ssl=true&replicaSet=globaldb";

    @Override
    public MongoClient getClient() {
        //CodecRegistry providers = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry providers = fromProviders(PojoCodecProvider.builder().register("com.miage.bigdata.models").build());
        CodecRegistry codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), providers);
        MongoClientOptions.Builder options = new MongoClientOptions.Builder().codecRegistry(codecRegistry);

        MongoClientURI uri = new MongoClientURI(HOST, options);
        MongoClient client = new MongoClient(uri);
        return client;
    }
}
