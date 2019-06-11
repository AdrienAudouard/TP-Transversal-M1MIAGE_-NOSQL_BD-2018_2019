package com.miage.bigdata.daos.dbDao.column;

import com.datastax.driver.core.Session;
import com.miage.bigdata.daos.dbDao.ModelDbDao;

public class ColumnModelDbDao extends ModelDbDao<Session> {
    @Override
    public Session connect() {
        if (this.client == null) {
            this.client = ColumnClientFactory.getCassandraSession();
        }

        return this.client;
    }

    @Override
    public boolean isConnected() {
        return client != null;
    }
}
