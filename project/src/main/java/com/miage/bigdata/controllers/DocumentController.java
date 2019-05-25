package com.miage.bigdata.controllers;

import com.miage.bigdata.daos.dbDao.document.DocumentModelDbDao;
import com.miage.bigdata.daos.itemDao.document.ProductObjectDao;
import com.miage.bigdata.daos.itemDao.document.ThingObjectDao;
import com.miage.bigdata.models.document.ProductItem;
import com.miage.bigdata.models.document.ThingItem;

public class DocumentController extends ModelDbController<DocumentModelDbDao> {
    public DocumentController() {
        super();

        this.dbDao = new DocumentModelDbDao();
        this.itemControllers.put(ThingItem.class, new ItemController<ThingItem>(new ThingObjectDao(dbDao)));
        this.itemControllers.put(ProductItem.class, new ItemController<ProductItem>(new ProductObjectDao(dbDao)));
    }
}
