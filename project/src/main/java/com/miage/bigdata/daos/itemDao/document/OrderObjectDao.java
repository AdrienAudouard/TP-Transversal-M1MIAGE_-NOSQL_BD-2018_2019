package com.miage.bigdata.daos.itemDao.document;

import com.miage.bigdata.controllers.item.ItemController;
import com.miage.bigdata.controllers.models.ModelController;
import com.miage.bigdata.daos.dbDao.document.DocumentModelDbDao;
import com.miage.bigdata.models.document.OrderItem;
import com.miage.bigdata.models.document.ProductItem;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class OrderObjectDao extends DocumentObjectDao<OrderItem> {

    public OrderObjectDao() {
    }

    public OrderObjectDao(DocumentModelDbDao dbDao) {
        super(dbDao);
    }

    @Override
    public List<OrderItem> readAll() {
        MongoCollection collection = getCollection();

        ArrayList<OrderItem> list = new ArrayList<>();

        for (Object o : collection.find()) {
            org.bson.Document document = (org.bson.Document) o;
            loadOrderLine(document);

            list.add(instanciateItemFromDocument(document));
        }

        return list;
    }

    @Override
    public boolean populateTable() {
        List<OrderItem> orders = loadDataFile();
        for (OrderItem order : orders) {
            if(order != null) {
                order.setItemId(generateID());
                List<ProductItem> orderLines = order.getOrderLines();

                if(orderLines.size() > 0) {
                    ModelController modelController = new ModelController();
                    ItemController<ProductItem> piController = modelController.getItemController(ProductItem.class);
                    piController.deleteTable();
                    piController.createTable();
                    for (ProductItem orderLine : orderLines) {
                        orderLine.setItemId(generateID());
                        piController.create(orderLine);
                    }
                }
                create(order);
            }
        }
        return true;
    }

    @Override
    public OrderItem getByID(String id) {
        return super.getByID(id);
    }

    private void loadOrderLine(Document document) {
        List<Document> docs = (List<Document>) document.get("orderLines");

        ProductObjectDao productObjectDao = new ProductObjectDao(dbDao);

        ArrayList<ProductItem> productItems = new ArrayList<>();

        for (Document doc : docs) {
            productItems.add(productObjectDao.getByID(doc.getString("_id")));
        }

        document.append("orderLines", productItems);
    }

    @Override
    public String getCollectionName() {
        return "order";
    }

    @Override
    protected Class<OrderItem> getItemClass() {
        return OrderItem.class;
    }
}
