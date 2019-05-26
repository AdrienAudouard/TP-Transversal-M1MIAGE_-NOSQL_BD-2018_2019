package com.miage.bigdata.daos.itemDao.graph;

import com.miage.bigdata.daos.dbDao.graph.GraphModelDbDao;
import com.miage.bigdata.models.graph.Person;

public class PersonObjectDao extends GraphObjectDao<Person> {
    public PersonObjectDao(GraphModelDbDao dbDao) {
        super(dbDao);
    }

    @Override
    protected String getDatabaseID() {
        return "person";
    }

    @Override
    public String generateID() {
        return null;
    }

    @Override
    public boolean populateTable() {
        return false;
    }

    @Override
    protected Class<Person> getItemClass() {
        return Person.class;
    }
}
