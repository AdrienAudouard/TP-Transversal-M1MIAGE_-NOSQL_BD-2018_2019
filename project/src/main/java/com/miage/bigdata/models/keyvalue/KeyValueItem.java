package com.miage.bigdata.models.keyvalue;

import com.miage.bigdata.models.Item;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.StorageExtendedErrorInformation;
import com.microsoft.azure.storage.table.EntityProperty;
import com.microsoft.azure.storage.table.TableEntity;
import com.microsoft.azure.storage.table.TableServiceEntity;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

public abstract class KeyValueItem extends Item implements TableEntity {
    // region Properties
    protected String partitionKey;
    protected String rowKey;
    protected Date timestamp;
    protected String eTag;

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

    public String getFullKey() {
        return partitionKey + "/" + rowKey;
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

    //region Constructors
    public KeyValueItem() {}

    public KeyValueItem(String partitionKey, String rowKey) {
        this.partitionKey = partitionKey;
        this.rowKey = rowKey;
        this.timestamp = new Date();
        this.timestamp = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
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

    //region Methods
    @Override
    public void readEntity(HashMap<String, EntityProperty> properties, OperationContext operationContext) throws StorageException {
        try {
            TableServiceEntity.readEntityWithReflection(this, properties, operationContext);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new StorageException("InvalidDocument", "The response received is invalid or improperly formatted.", 306, (StorageExtendedErrorInformation)null, illegalArgumentException);
        } catch (IllegalAccessException illegalAccessException) {
            throw new StorageException("InvalidDocument", "The entity threw an exception during deserialization.", 306, (StorageExtendedErrorInformation)null, illegalAccessException);
        } catch (InvocationTargetException invocationTargetException) {
            throw new StorageException("InternalError", "The entity threw an exception during deserialization.", 306, (StorageExtendedErrorInformation)null, invocationTargetException);
        }
    }

    @Override
    public HashMap<String, EntityProperty> writeEntity(OperationContext operationContext) throws StorageException {
        try {
            return TableServiceEntity.writeEntityWithReflection(this);
        } catch (IllegalAccessException illegalAccessException) {
            throw new StorageException("InternalError", "An attempt was made to access an inaccessible member of the entity during serialization.", 306, (StorageExtendedErrorInformation)null, illegalAccessException);
        } catch (InvocationTargetException invocationTargetException) {
            throw new StorageException("InternalError", "The entity threw an exception during serialization.", 306, (StorageExtendedErrorInformation)null, invocationTargetException);
        }
    }

    @Override
    public String toString() {
        return "KeyValueItem{" +
                "partitionKey='" + partitionKey + ",\'" +
                "rowKey='" + rowKey + ",\'" +
                "timestamp='" + timestamp + ",\'" +
                "eTag='" + eTag + "\'" +
                "} ";
    }
    //endregion
}
