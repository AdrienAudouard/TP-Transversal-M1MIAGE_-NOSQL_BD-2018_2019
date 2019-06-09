package com.miage.bigdata.models;

import javax.xml.bind.annotation.XmlElement;
import java.io.File;

public abstract class Item {
    protected String id;

    public Item() {}

    public Item(String json) { }

    @XmlElement(name = "OrderId")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static String getResourcesPath() {
        String resourcesPath = new File(".").getAbsolutePath();
        resourcesPath = resourcesPath.substring(0, resourcesPath.length() - 1);
        resourcesPath = resourcesPath.split("project/")[0];
        resourcesPath += "project/src/main/resources/datas/";
        return resourcesPath;
    }

    public String getPathFileData() {
        return null;
    }
}
