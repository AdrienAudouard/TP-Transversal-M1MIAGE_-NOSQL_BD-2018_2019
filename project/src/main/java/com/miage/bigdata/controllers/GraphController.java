package com.miage.bigdata.controllers;

import com.miage.bigdata.daos.dbDao.graph.GraphModelDbDao;
import com.miage.bigdata.daos.itemDao.graph.PersonObjectDao;
import com.miage.bigdata.daos.itemDao.graph.PostObjectDao;
import com.miage.bigdata.daos.itemDao.graph.TagObjectDao;
import com.miage.bigdata.models.graph.Person;
import com.miage.bigdata.models.graph.Post;
import com.miage.bigdata.models.graph.Tag;

public class GraphController extends ModelDbController<GraphModelDbDao> {
    public GraphController() {
        super();

        this.dbDao = new GraphModelDbDao();

        this.itemControllers.put(Person.class, new GraphItemController(new PersonObjectDao(dbDao)));
        this.itemControllers.put(Tag.class, new GraphItemController(new TagObjectDao(dbDao)));
        this.itemControllers.put(Post.class, new GraphItemController(new PostObjectDao(dbDao)));
    }
}