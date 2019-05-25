package com.miage.bigdata.daos.itemDao.document;

import com.miage.bigdata.daos.dbDao.document.DocumentModelDbDao;
import com.miage.bigdata.models.document.ThingItem;

public class ThingObjectDao extends DocumentObjectDao<ThingItem> {
    public ThingObjectDao(DocumentModelDbDao dbDao) {
        super(dbDao);
    }

    @Override
    public String getCollectionName() {
        return "things";
    }

    @Override
    protected Class<ThingItem> getItemClass() {
        return ThingItem.class;
    }
}
