package com.miage.bigdata.controllers.models;

import com.miage.bigdata.controllers.item.ItemController;
import com.miage.bigdata.daos.dbDao.document.DocumentModelDbDao;
import com.miage.bigdata.daos.itemDao.document.OrderObjectDao;
import com.miage.bigdata.daos.itemDao.document.ProductObjectDao;
import com.miage.bigdata.daos.itemDao.document.ThingObjectDao;
import com.miage.bigdata.models.document.OrderItem;
import com.miage.bigdata.models.document.ProductItem;
import com.miage.bigdata.models.document.ThingItem;

class DocumentController extends ModelDbController<DocumentModelDbDao> {
    DocumentController() {
        super();

        this.dbDao = new DocumentModelDbDao();
        this.itemControllers.put(ThingItem.class, new ItemController<ThingItem>(new ThingObjectDao(dbDao)));
        this.itemControllers.put(ProductItem.class, new ItemController<ProductItem>(new ProductObjectDao(dbDao)));
        this.itemControllers.put(OrderItem.class, new ItemController<OrderItem>(new OrderObjectDao(dbDao)));
    }
}
