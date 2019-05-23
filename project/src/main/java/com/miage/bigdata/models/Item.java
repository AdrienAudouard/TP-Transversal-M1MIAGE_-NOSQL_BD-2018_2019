package com.miage.bigdata.models;

public class Item {

    protected String id;
    protected String name;

    protected Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    protected Item(String name) {
        this.name = name;
    }

    public Item() {
    }

    public String getId() {
        return id;
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
}
