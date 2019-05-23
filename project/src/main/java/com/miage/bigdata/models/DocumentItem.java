package com.miage.bigdata.models;

import com.microsoft.azure.documentdb.Document;

public abstract class DocumentItem extends Item {
    protected String id;

    public String getId() {
        return id;
    }

    public abstract Document updateDocument(Document document);

    @Override
    public String toString() {
        return "DocumentItem{" +
                "id='" + id + '\'' +
                "} " + super.toString();
    }
}
