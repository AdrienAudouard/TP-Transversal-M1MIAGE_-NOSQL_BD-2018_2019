package com.miage.bigdata.models.graph;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Tag extends GraphItem {
    private final String title;

    public Tag(String id, String title) {
        this.title = title;
        this.itemId = id;
    }

    public Tag(String json) {
        super(json);

        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject)jsonParser.parse(json);
        JsonObject properties = jo.getAsJsonObject("properties");

        this.title = getPropertyInJSON(properties, "title").toString();
    }

    @Override
    public String getCreateQuery(String verticeName) {
        return "g.addV('" + verticeName + "')" +
                ".property('id', '"+ itemId +"')" +
                ".property('title', '"+ title +"')";
    }

    @Override
    public String getUpdateQuery() {
        return "g.V('"+ itemId +"')" +
                ".property('title', '"+ title +"')";
    }

    @Override
    public String toString() {
        return "Tag{" +
                "title='" + title + '\'' +
                ", id='" + itemId + '\'' +
                "} ";
    }
}
