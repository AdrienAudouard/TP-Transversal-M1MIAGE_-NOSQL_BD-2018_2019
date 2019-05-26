package com.miage.bigdata.models.keyvalue;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.EntityProperty;

import java.util.Date;
import java.util.HashMap;

public class FeedbackItem extends KeyValueItem {
    //TODO
    private String rate;
    private String review;

    //region Constructors
    public FeedbackItem(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
    }

    public FeedbackItem(String partitionKey, String rowKey, String rate, String review) {
        super(partitionKey, rowKey);
        this.rate = rate;
        this.review = review;
    }
    //endregion
}
