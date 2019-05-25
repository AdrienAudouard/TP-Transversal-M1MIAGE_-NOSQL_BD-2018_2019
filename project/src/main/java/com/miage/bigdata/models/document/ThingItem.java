package com.miage.bigdata.models.document;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bson.Document;

public class ThingItem extends DocumentItem {
    private String name;

    public ThingItem(String id, String name) {
        this.name = name;
        this.id = id;
    }

    public ThingItem(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject)jsonParser.parse(json);

        this._id = jo.get("_id").toString();
        this.name = jo.get("name").toString();
    }

    public ThingItem(Document document) {
        this(document.toJson());
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ThingItem{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                "} " + super.toString();
    }

    @Override
    public Document toDocument() {
        return new Document("id", id).append("name", name);
    }
}
