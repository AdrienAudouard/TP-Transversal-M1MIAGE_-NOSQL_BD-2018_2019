package com.miage.bigdata.models;

import java.io.File;

public abstract class Item {
    protected String id;

    public Item() {}

    public Item(String json) { }

    public String getId() {
        return id;
    }

    public static String getResourcesPath() {
        String resourcesPath = new File(".").getAbsolutePath();
        resourcesPath = resourcesPath.substring(0, resourcesPath.length() - 1);
        resourcesPath = resourcesPath.split("project/")[0];
        resourcesPath += "resources/";
        return resourcesPath;
    }

    public abstract String getPathFileData();
}
