package com.miage.bigdata.daos.itemDao;

import com.miage.bigdata.models.Thing;

import java.util.List;

public interface ThingDao {
    public List<Thing> readItems();

    public Thing createItem(Thing item);

    public Thing readItem(String id);

    public Thing updateItem(Thing item);

    public boolean deleteItem(String id);
}