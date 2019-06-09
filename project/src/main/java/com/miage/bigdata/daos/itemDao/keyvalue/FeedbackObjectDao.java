package com.miage.bigdata.daos.itemDao.keyvalue;

import com.miage.bigdata.daos.dbDao.keyvalue.KeyValueModelDbDao;
import com.miage.bigdata.models.keyvalue.FeedbackItem;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;

import java.net.URISyntaxException;

public class FeedbackObjectDao extends KeyValueObjectDao<FeedbackItem> {
    //TODO
    public FeedbackObjectDao(KeyValueModelDbDao dbDao) {
        super(dbDao);
    }

    public FeedbackObjectDao() {
    }

    @Override
    protected String getTableName() {
        return "feedback";
    }

    @Override
    protected CloudTable getTable() {
        CloudTable cloudTable = null;

        try {
            cloudTable = cloudTableClient.getTableReference(getTableName());
        } catch (URISyntaxException | StorageException e) {
            e.printStackTrace();
        }

        return cloudTable;
    }

    @Override
    public FeedbackItem getByID(String id) {
        return null;
    }

    @Override
    public String generateID() {
        return null;
    }

    @Override
    public boolean createTable() {
        return false;
    }

    @Override
    public boolean populateTable() {
        return false;
    }

    @Override
    public boolean deleteTable() {
        return false;
    }

    @Override
    protected Class<FeedbackItem> getItemClass() {
        return FeedbackItem.class;
    }
}
