package com.miage.bigdata.models.document;

import com.miage.bigdata.models.Item;
import org.bson.Document;

public abstract class DocumentItem extends Item {
    public abstract Document toDocument();

    protected String _id;

    public DocumentItem() {
        super();
    }

    public DocumentItem(Document document) {
        super();

        this._id = document.getObjectId("_id").toString();
        this.id = document.getString("id");
    }

    public void setMongoID(String id) { this._id = id; }

    public String getMongoID() {
        return _id;
    }
}
