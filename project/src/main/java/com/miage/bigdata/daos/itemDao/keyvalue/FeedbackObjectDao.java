package com.miage.bigdata.daos.itemDao.keyvalue;

import com.miage.bigdata.daos.dbDao.keyvalue.KeyValueModelDbDao;
import com.miage.bigdata.models.keyvalue.FeedbackItem;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

public class FeedbackObjectDao extends KeyValueObjectDao<FeedbackItem> {
    public FeedbackObjectDao(KeyValueModelDbDao dbDao) {
        super(dbDao);
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
            cloudTable.createIfNotExists();
        } catch (URISyntaxException | StorageException e) {
            e.printStackTrace();
        }

        return cloudTable;
    }

    @Override
    public String generateID() {
        return UUID.randomUUID().toString() + "/" + UUID.randomUUID();
    }

    @Override
    public boolean createTable() {
        try {
            CloudTable cloudTable = cloudTableClient.getTableReference(getTableName());
            cloudTable.create();
            return true;
        } catch (URISyntaxException | StorageException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean populateTable() {
        List<FeedbackItem> feedbacks = loadDataFile();
        for (FeedbackItem feedback : feedbacks) {
            if(feedback != null) {
                create(feedback);
            }
            return false;
        }
        return true;    }

    @Override
    public boolean deleteTable() {
        try {
            CloudTable cloudTable = cloudTableClient.getTableReference(getTableName());
            cloudTable.deleteIfExists();
            return true;
        } catch (URISyntaxException | StorageException e) {
            e.printStackTrace();
        }

        return false;    }

    @Override
    protected Class<FeedbackItem> getItemClass() {
        return FeedbackItem.class;
    }
}
