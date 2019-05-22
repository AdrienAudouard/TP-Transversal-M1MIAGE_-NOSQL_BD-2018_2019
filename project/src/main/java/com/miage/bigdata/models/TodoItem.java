package com.miage.bigdata.models;

public class TodoItem extends Item{
    private String category;
    private boolean complete;
    private String id;
    private String name;

    public TodoItem(String name, String category, boolean complete) {
        this.category = category;
        this.complete = complete;
        this.name = name;
    }


    public TodoItem(String id, String name, String category, boolean complete) {
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
