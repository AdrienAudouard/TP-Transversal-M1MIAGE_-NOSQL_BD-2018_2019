package com.miage.bigdata.models;

public class Thing {

    private String id;
    private String name;

    public Thing(String name) {
        this.name = name;
    }

    public Thing(String id, String name) {
        this.id = id;
        this.name = name;
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
