package com.miage.bigdata.daos.itemDao.graph;

import com.google.gson.Gson;
import com.miage.bigdata.daos.dbDao.graph.GraphModelDbDao;
import com.miage.bigdata.daos.itemDao.ObjectDao;
import com.miage.bigdata.models.graph.GraphItem;
import com.miage.bigdata.models.graph.Person;
import com.miage.bigdata.models.graph.Post;
import com.miage.bigdata.models.graph.Tag;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ResultSet;

import java.util.ArrayList;
import java.util.List;

public abstract class GraphObjectDao<T extends GraphItem> extends ObjectDao<T, GraphModelDbDao> {
    private final Client client;
    protected static final Gson gson = new Gson();

    public GraphObjectDao(GraphModelDbDao dbDao) {
        super(dbDao);

        this.client = dbDao.connect();
    }

    @Override
    public T create(T item) {
        String query = item.getCreateQuery(getDatabaseID());
        ResultSet result = this.client.submit(query);

        return item;
    }

    @Override
    public List<T> readAll() {
        String query = "g.V().hasLabel('"+ getDatabaseID() +"')";
        ResultSet results = this.client.submit(query);

        List<T> objects = new ArrayList<>();

        for (Result result : results) {
            objects.add(instanciateItemFromJSON(gson.toJson(result.getObject())));
        }

        return objects;
    }

    @Override
    public T getByID(String id) {
        String query = "g.V('"+ id +"')";
        Result result = this.client.submit(query).one();

        if (result == null) {
            return null;
        }

        return instanciateItemFromJSON(gson.toJson(result.getObject()));
    }

    @Override
    public boolean delete(String id) {
        String query = "g.V('"+ id +"').drop()";
        this.client.submit(query).one();

        return true;
    }

    @Override
    public T update(T item) {
        String query = item.getUpdateQuery();

        client.submit(query);

        return item;

    }

    public void addKnow(Person p1, Person p2) {
        String query = "g.V('" + p1.getItemId() + "').addE('knows').to(g.V('" + p2.getItemId() + "'))";
        this.client.submit(query);
    }

    public void addHas(Person p, Tag t) {
        String query = "g.V('" + p.getItemId() + "').addE('has').to(g.V('" + t.getItemId() + "'))";
        this.client.submit(query);
    }

    public void addCreated(Post post, Person person) {
        String query = "g.V('" + post.getItemId() + "').addE('created').to(g.V('" + person.getItemId() + "'))";
        this.client.submit(query);
    }

    public List<Person> createdBy(Post post) {
        String query = "g.V('" + post.getItemId() + "').out('created').hasLabel('person')";

        return getPeopleFromQuery(query);
    }

    public List<Post> hasCreated(Person person) {
        String query = "g.V('"+ person.getItemId() +"').in('created').hasLabel('post')";

        return getPostsFromQuery(query);
    }

    public void addHas(Post p, Tag t) {
        String query = "g.V('" + p.getItemId() + "').addE('has').to(g.V('" + t.getItemId() + "'))";
        this.client.submit(query);
    }

    public List<Person> peoplesHas(Tag tag) {
        String query = "g.V('" + tag.getItemId() + "').in('has').hasLabel('person')";

        return getPeopleFromQuery(query);
    }

    public List<Post> postHas(Tag tag) {
        String query = "g.V('" + tag.getItemId() + "').in('has').hasLabel('post')";

        return getPostsFromQuery(query);
    }

    private List<Post> getPostsFromQuery(String query) {
        ResultSet results = this.client.submit(query);

        List<Post> objects = new ArrayList<>();

        for (Result result : results) {
            objects.add(instanciateItemFromJSON(gson.toJson(result.getObject()), Post.class));
        }

        return objects;
    }

    public List<Tag> tagsOn(Post p) {
        return getTagsFromItemId(p.getItemId());
    }

    private List<Tag> getTagsFromItemId(String id) {
        String query = "g.V('"+ id +"').out('has').hasLabel('tag')";

        ResultSet results = this.client.submit(query);

        List<Tag> objects = new ArrayList<>();

        for (Result result : results) {
            objects.add(instanciateItemFromJSON(gson.toJson(result.getObject()), Tag.class));
        }

        return objects;
    }

    public List<Tag> tagsOn(Person p) {
        return getTagsFromItemId(p.getItemId());
    }

    private List<Person> getPeopleFromQuery(String query) {
        ResultSet results = this.client.submit(query);

        List<Person> objects = new ArrayList<>();

        for (Result result : results) {
            objects.add(instanciateItemFromJSON(gson.toJson(result.getObject()), Person.class));
        }

        return objects;
    }

    // Return all the persons that knows p1
    public List<Person> knows(Person p1) {
        String query = "g.V('" + p1.getItemId() + "').out('knows').hasLabel('person')";

        return getPeopleFromQuery(query);
    }

    @Override
    public boolean deleteTable() {
        String query = "g.V().drop()";

        client.submit(query);

        return true;
    }

    @Override
    public boolean createTable() {
        return true;
    }
}
