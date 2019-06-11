package com.miage.bigdata.daos.dbDao.document;

import com.miage.bigdata.daos.dbDao.ModelDbDao;
import com.mongodb.MongoClient;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DocumentModelDbDao extends ModelDbDao<MongoClient> {
    // Called the first DocumentModelDbDao is used
    static {
        // Hide mongo driver log
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.
    }
    @Override
    public MongoClient connect() {
        if (client != null) {
            return client;
        }

        this.client = DocumentClientFactory.getMongoClient();
        return client;
    }

    @Override
    public boolean isConnected() {
        return client != null;
    }
}
