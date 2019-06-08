package com.miage.bigdata.controllers;

import com.miage.bigdata.daos.itemDao.graph.GraphObjectDao;
import com.miage.bigdata.models.graph.GraphItem;
import com.miage.bigdata.models.graph.Person;
import com.miage.bigdata.models.graph.Post;
import com.miage.bigdata.models.graph.Tag;

import java.util.List;

public class GraphItemController<T extends GraphItem> extends ItemController<T> {

    public GraphItemController(GraphObjectDao objectDao) {
        super(objectDao);
    }

    public void addKnow(Person p1, Person p2) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        graphObjectDao.addKnow(p1, p2);
    }

    public List<Person> knows(Person p1) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        return graphObjectDao.knows(p1);
    }

    public void addHas(Person p, Tag t) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        graphObjectDao.addHas(p, t);
    }

    public void addHas(Post p, Tag t) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        graphObjectDao.addHas(p, t);
    }

    public List<Person> peoplesHas(Tag tag) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        return graphObjectDao.peoplesHas(tag);
    }

    public List<Tag> tagsOn(Person p) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        return graphObjectDao.tagsOn(p);
    }

    public List<Tag> tagsOn(Post p) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        return graphObjectDao.tagsOn(p);
    }

    public List<Tag> postHas(Tag t) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        return graphObjectDao.postHas(t);
    }

    public void addcreated(Post post, Person person) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        graphObjectDao.addCreated(post, person);
    }

    public List<Person> createdBy(Post post) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        return graphObjectDao.createdBy(post);
    }

    public List<Post> hasCreated(Person person) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        return graphObjectDao.hasCreated(person);
    }
}
