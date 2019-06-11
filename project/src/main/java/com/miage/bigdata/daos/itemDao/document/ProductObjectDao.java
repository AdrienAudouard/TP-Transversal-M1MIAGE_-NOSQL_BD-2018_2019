package com.miage.bigdata.daos.itemDao.document;

import com.miage.bigdata.daos.dbDao.document.DocumentModelDbDao;
import com.miage.bigdata.models.document.ProductItem;

public class ProductObjectDao extends DocumentObjectDao<ProductItem> {
    public ProductObjectDao(DocumentModelDbDao dbDao) {
        super(dbDao);
    }

    public ProductObjectDao() {}

    @Override
    public String getCollectionName() {
        return "product";
    }

    @Override
    protected Class<ProductItem> getItemClass() {
        return ProductItem.class;
    }
}
