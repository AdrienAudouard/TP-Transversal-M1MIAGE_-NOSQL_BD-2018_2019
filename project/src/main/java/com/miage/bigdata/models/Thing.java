package com.miage.bigdata.models;

public class Thing extends Item {
    private Object _id;

    public Thing(Object _id, String name) {
        this._id = _id;
        this.name = name;
    }

    public Thing(String name) {
        super(name);
    }

    public String toString() {
        return "[ID]: " + _id.toString() + "[Name]: " + name;
    }
}
