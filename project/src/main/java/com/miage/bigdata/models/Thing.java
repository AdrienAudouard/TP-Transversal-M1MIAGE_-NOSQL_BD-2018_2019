package com.miage.bigdata.models;

public class Thing extends Item {
    private String _id;

    public Thing(String _id, String name) {
        this._id = _id;
        this.id = this._id;
        this.name = name;
    }

    public Thing(String name) {
        super(name);
    }

    public String toString() {
        return "[ID]: " + id + "[Name]: " + name;
    }
}
