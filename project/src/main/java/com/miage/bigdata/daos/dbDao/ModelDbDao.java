package com.miage.bigdata.daos.dbDao;

import com.miage.bigdata.daos.itemDao.ObjectDao;

import java.util.List;

public abstract class ModelDbDao<T> {
    protected List<ObjectDao> objectDaos;

    protected T client;

    public abstract T connect();

    public abstract boolean isConnected();
}
