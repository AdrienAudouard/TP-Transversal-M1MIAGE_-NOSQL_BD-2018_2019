package com.miage.bigdata.models.document;

import com.google.gson.annotations.SerializedName;
import com.miage.bigdata.models.Item;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderItem extends DocumentItem {

    @SerializedName("PersonId")
    private String personId;

    @SerializedName("OrderDate")
    private String orderDate;

    @SerializedName("TotalPrice")
    private Double totalPrice;

    @SerializedName("Orderline")
    private List<ProductItem> orderLines;

    public OrderItem() {}

    public OrderItem(String id, String personId, String orderDate, Double totalPrice, List<ProductItem> orderLines) {
        this.personId = personId;
        this.id = id;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderLines = orderLines;
    }

    public OrderItem(Document document) {
        super(document);

        personId = document.getString("personId");
        orderDate = document.getString("orderDate");
        totalPrice = document.getDouble("totalPrice");
        orderLines = (List<ProductItem>) document.get("orderLines");
    }

    @Override
    public Document toDocument() {

        Document document = new Document("id", id)
                .append("personId", personId)
                .append("orderDate", orderDate)
                .append("totalPrice", totalPrice);


        ArrayList<Document> documents = new ArrayList<>();

        for (ProductItem orderLine : orderLines) {
            Document dbRef = new Document("_id", orderLine.getId());
            documents.add(dbRef);
        }

        document = document.append("orderLines", documents);


        return document;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "personId='" + personId + '\'' +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", orderLines=" + orderLines +
                ", _id='" + _id + '\'' +
                ", id='" + id + '\'' +
                "} " + super.toString();
    }

    @Override
    public String getPathFileData() {
        return Item.getResourcesPath() + "order/Order.json";
    }
}
