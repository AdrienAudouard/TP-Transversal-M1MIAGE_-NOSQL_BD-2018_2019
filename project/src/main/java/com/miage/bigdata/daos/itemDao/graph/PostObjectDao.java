package com.miage.bigdata.daos.itemDao.graph;

import com.miage.bigdata.daos.dbDao.graph.GraphModelDbDao;
import com.miage.bigdata.models.graph.Post;

public class PostObjectDao extends GraphObjectDao<Post> {
    public PostObjectDao(GraphModelDbDao dbDao) {
        super(dbDao);
    }

    @Override
    protected String getDatabaseID() {
        return "post";
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
    protected Class<Post> getItemClass() {
        return Post.class;
    }
}
