package com.miage.bigdata.models;

public class TodoItem extends Item {
    private String category;
    private boolean complete;

    public TodoItem(String id, String name, String category, boolean complete) {
        super(id, name);
        this.category = category;
        this.complete = complete;
    }

    public TodoItem(String name, String category, boolean complete) {
        super(name);
        this.category = category;
        this.complete = complete;
    }

    protected TodoItem(String id, String name) {
        super(id, name);
    }

    protected TodoItem(String name) {
        super(name);
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

    public String toString() {
        return "[ID]: " + id + "[Name]: " + name + "[Category]: " + category + "[Complete]: " + complete;
    }
}
