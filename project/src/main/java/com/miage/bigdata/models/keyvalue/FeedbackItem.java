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
    public FeedbackItem(String partitionKey, String rowKey, Date timestamp) {
        super(partitionKey, rowKey, timestamp);
    }

    public FeedbackItem(String partitionKey, String rowKey, Date timestamp, String eTag) {
        super(partitionKey, rowKey, timestamp, eTag);
    }

    public FeedbackItem(String partitionKey, String rowKey, Date timestamp, String rate, String review) {
        super(partitionKey, rowKey, timestamp);
        this.rate = rate;
        this.review = review;
    }

    public FeedbackItem(String partitionKey, String rowKey, Date timestamp, String eTag, String rate, String review) {
        super(partitionKey, rowKey, timestamp, eTag);
        this.rate = rate;
        this.review = review;
    }
    //endregion
}
