package com.miage.bigdata.models;

public class Thing extends Item {
    public Thing(String id, String name) {
        super(id, name);
    }

    public Thing(String name) {
        super(name);
    }

    public String toString() {
        return "[ID]: " + id + "[Name]: " + name;
    }
}
