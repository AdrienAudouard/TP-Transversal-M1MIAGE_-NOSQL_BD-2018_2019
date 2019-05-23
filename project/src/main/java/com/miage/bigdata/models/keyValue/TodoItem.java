package com.miage.bigdata.models.keyValue;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.azure.documentdb.Document;

public class TodoItem extends KeyValueItem {
    private String category;
    private boolean complete;
    private String name;

    public TodoItem(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject)jsonParser.parse(json);

        this.category = jo.get("category").toString();
        this.complete = jo.get("complete").getAsBoolean();
        this.id = jo.get("id").toString();
        this.name = jo.get("name").toString();
    }

    public TodoItem(String category, boolean complete, String id, String name) {
        this.category = category;
        this.complete = complete;
        this.id = id;
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Document updateDocument(Document document) {
        document.set("category", category);
        document.set("complete", complete);
        document.set("id", id);
        document.set("name", name);

        return document;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "category='" + category + '\'' +
                ", complete=" + complete +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}
