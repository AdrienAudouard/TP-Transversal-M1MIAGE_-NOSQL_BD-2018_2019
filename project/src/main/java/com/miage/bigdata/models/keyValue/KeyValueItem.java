package com.miage.bigdata.models.keyValue;

import com.miage.bigdata.models.Item;
import com.microsoft.azure.documentdb.Document;

public abstract class KeyValueItem extends Item {
    public abstract Document updateDocument(Document document);

    @Override
    public String toString() {
        return "KeyValueItem{" +
                "id='" + id + '\'' +
                "} " + super.toString();
    }
}
