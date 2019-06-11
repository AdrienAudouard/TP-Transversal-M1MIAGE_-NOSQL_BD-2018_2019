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

import java.util.ArrayList;
import java.util.List;

public abstract class KeyValueObjectDao<T extends KeyValueItem> extends ObjectDao<T, KeyValueModelDbDao> {
    protected static CloudTableClient cloudTableClient;

    protected abstract String getTableName();

    protected abstract CloudTable getTable();

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
    public final T getByID(String fullKey) {
        //FIXME since we're using a single string to contain partition key AND row key to preserve polymorphism
        CloudTable cloudTable = getTable();
        String[] keys = fullKey.split("/");

        if (cloudTable != null && keys.length == 2) {
            String partitionKey = keys[0]; // product id
            String rowKey = keys[1]; // user id

            if (!partitionKey.isEmpty() && !rowKey.isEmpty()) {
                try {
                    TableOperation retrieveKeyValueItem = TableOperation.retrieve(partitionKey, rowKey, getItemClass());
                    return cloudTable.execute(retrieveKeyValueItem).getResultAsType();
                } catch (StorageException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public final T create(T item) {
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
    public final T update(T item) {
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
    public final boolean delete(String fullKey) {
        //FIXME since we're using a single string to contain partition key AND row key to preserve polymorphism
        CloudTable cloudTable = getTable();

        if (cloudTable != null) {
            try {
                KeyValueItem keyValueItem = getByID(fullKey);

                if (keyValueItem != null) {
                    TableOperation deleteKeyValueItem = TableOperation.delete(keyValueItem);
                    return cloudTable.execute(deleteKeyValueItem).getResultAsType() != null;
                }
            } catch (StorageException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    protected final String getDatabaseID() {
        return "TablesDB";
    }
}
