package com.miage.bigdata.models.document;

import com.miage.bigdata.models.Item;
import org.bson.Document;

public abstract class DocumentItem extends Item {
    public abstract Document toDocument();

    public DocumentItem() {

    }

    public DocumentItem(Document document) {

    }
}
