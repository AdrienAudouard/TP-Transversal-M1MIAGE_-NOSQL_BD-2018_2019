package com.miage.bigdata.models.keyvalue;

import com.miage.bigdata.models.Item;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.EntityProperty;
import com.microsoft.azure.storage.table.TableEntity;

import java.util.Date;
import java.util.HashMap;

public abstract class KeyValueItem extends Item implements TableEntity {
    private String partitionKey;
    private String rowKey;
    private Date timestamp;
    private String eTag;

    //region Constructors
    public KeyValueItem() {
    }

    public KeyValueItem(String partitionKey, String rowKey, Date timestamp) {
        this.partitionKey = partitionKey;
        this.rowKey = rowKey;
        this.timestamp = timestamp;
    }

    public KeyValueItem(String partitionKey, String rowKey, Date timestamp, String eTag) {
        this.partitionKey = partitionKey;
        this.rowKey = rowKey;
        this.timestamp = timestamp;
        this.eTag = eTag;
    }
    //endregion

    // region Getters & Setters
    @Override
    public String getPartitionKey() {
        return partitionKey;
    }

    @Override
    public void setPartitionKey(String s) {
        partitionKey = s;
    }

    @Override
    public String getRowKey() {
        return rowKey;
    }

    @Override
    public void setRowKey(String s) {
        rowKey = s;
    }
    @Override
    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(Date date) {
        timestamp = date;
    }

    @Override
    public String getEtag() {
        return eTag;
    }

    @Override
    public void setEtag(String s) {
        eTag = s;

    }
    //endregion



    @Override
    public void readEntity(HashMap<String, EntityProperty> hashMap, OperationContext operationContext) throws StorageException {
        //TODO
    }

    @Override
    public HashMap<String, EntityProperty> writeEntity(OperationContext operationContext) throws StorageException {
        // TODO
        return null;
    }
    @Override
    public String toString() {
        return "KeyValueItem{" +
                "partitionKey='" + partitionKey + '\'' +
                "rowKey='" + rowKey + '\'' +
                "} " + super.toString();
    }
}
