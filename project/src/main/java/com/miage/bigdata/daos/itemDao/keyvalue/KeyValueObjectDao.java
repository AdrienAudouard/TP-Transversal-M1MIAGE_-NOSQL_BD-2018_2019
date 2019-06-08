package com.miage.bigdata.daos.itemDao.keyvalue;

import com.google.common.collect.Lists;
import com.miage.bigdata.daos.dbDao.keyvalue.KeyValueModelDbDao;
import com.miage.bigdata.daos.itemDao.ObjectDao;
import com.miage.bigdata.models.keyvalue.KeyValueItem;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public abstract class KeyValueObjectDao<T extends KeyValueItem> extends ObjectDao<T, KeyValueModelDbDao> {
    protected static CloudTableClient cloudTableClient;

    protected abstract String getTableName();

    protected abstract CloudTable getTable();

    public KeyValueObjectDao() {
    }

    public KeyValueObjectDao(KeyValueModelDbDao dbDao) {
        super(dbDao);

        cloudTableClient = dbDao.connect();
    }

    @Override
    public List<T> readAll() {
        List<T> items = new ArrayList<T>();
        CloudTable cloudTable = getTable();

        if (cloudTable != null) {
            TableQuery<T> readAllQuery = TableQuery.from(getItemClass()); // TODO check if this works
            List<T> allKeyValueItems = Lists.newArrayList(cloudTable.execute(readAllQuery));
            items.addAll(allKeyValueItems);
        }

        return items;
    }

    @Override
    public T getByID(@NonNull String id) {
        //TODO FIXME as we need partition and row keys instead of a single id, something like @NonNull String partitionKey, @NonNull String rowKey
        CloudTable cloudTable = getTable();

        if (cloudTable != null) {
            try {
                TableOperation retrieveKeyValueItem = TableOperation.retrieve(null, null, getItemClass());
                return cloudTable.execute(retrieveKeyValueItem).getResultAsType();
            } catch (StorageException e) {
                e.printStackTrace();
            }
            //TODO FIXME
        }

        return null;
    }

    @Override
    public T create(@NonNull T item) {
        CloudTable cloudTable = getTable();

        if (cloudTable != null) {
            try {
                TableOperation insertKeyValueItem = TableOperation.insertOrReplace(item);
                return cloudTable.execute(insertKeyValueItem).getResultAsType();
            } catch (StorageException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public T update(@NonNull T item) {
        CloudTable cloudTable = getTable();

        if (cloudTable != null) {
            try {
                TableOperation replaceKeyValueItem = TableOperation.replace(item);
                return cloudTable.execute(replaceKeyValueItem).getResultAsType();
            } catch (StorageException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public boolean delete(@NonNull String id) {
        CloudTable cloudTable = getTable();

        if (cloudTable != null) {
            try {
                KeyValueItem keyValueItem = getByID(id); // TODO FIXME as we need the partition and row keys instead of a single id

                TableOperation deleteKeyValueItem = TableOperation.delete(keyValueItem);
                return cloudTable.execute(deleteKeyValueItem).getResultAsType(); // TODO FIXME this probably won't return a boolean
            } catch (StorageException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    protected String getDatabaseID() {
        return "TablesDB";
    }
}
