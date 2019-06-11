package com.miage.bigdata.models;

import com.opencsv.bean.CsvBindByName;

import javax.xml.bind.annotation.XmlElement;
import java.io.File;

public abstract class Item {
    @CsvBindByName(column = "id")
    protected String itemId;

    public Item() {}

    public Item(String json) { }

    @XmlElement(name = "OrderId")
    public String getItemId() {
        return itemId.replace("\"", "");
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    private static String getProjectPath() {
        String resourcesPath = new File(".").getAbsolutePath();
        resourcesPath = resourcesPath.substring(0, resourcesPath.length() - 1);
        return resourcesPath.split("project/")[0];
    }

    public static String getDataPath() {
        return getProjectPath() + "project/src/main/resources/data/";
    }

    public static String getConfigPath() {
        return getProjectPath() + "project/src/main/resources/config/";
    }

    public String getPathFileData() {
        return null;
    }
}
