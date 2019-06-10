package com.miage.bigdata.models.relational;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.miage.bigdata.models.Item;
import com.microsoft.azure.documentdb.Document;

public class TodoItem extends RelationalItem {
    private String category;
    private boolean complete;
    private String name;

    public TodoItem(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject)jsonParser.parse(json);

        this.category = jo.get("category").toString();
        this.complete = jo.get("complete").getAsBoolean();
        this.itemId = jo.get("id").toString();
        this.name = jo.get("name").toString();
    }

    public TodoItem(String category, boolean complete, String id, String name) {
        this.category = category;
        this.complete = complete;
        this.itemId = id;
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

    public void setItemId(String id) {
        this.itemId = id;
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
        document.set("id", itemId);
        document.set("name", name);

        return document;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "category='" + category + '\'' +
                ", complete=" + complete +
                ", id='" + itemId + '\'' +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }

    @Override
    public String getPathFileData() {
        return Item.getDataPath() + "???";
    }
}
