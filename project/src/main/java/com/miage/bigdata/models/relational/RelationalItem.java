package com.miage.bigdata.models.relational;

import com.miage.bigdata.models.Item;
import com.microsoft.azure.documentdb.Document;

public abstract class RelationalItem extends Item {
    public abstract Document updateDocument(Document document);

    @Override
    public String toString() {
        return "RelationalItem{" +
                "id='" + itemId + '\'' +
                "} " + super.toString();
    }
}
