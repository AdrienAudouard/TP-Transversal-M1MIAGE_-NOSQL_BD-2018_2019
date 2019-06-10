package com.miage.bigdata.models.document;

import com.google.gson.annotations.SerializedName;
import com.miage.bigdata.models.Item;
import org.bson.Document;

import java.util.ArrayList;
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
        this.itemId = id;
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

        Document document = new Document("id", itemId)
                .append("personId", personId)
                .append("orderDate", orderDate)
                .append("totalPrice", totalPrice);


        ArrayList<Document> documents = new ArrayList<>();

        for (ProductItem orderLine : orderLines) {
            Document dbRef = new Document("_id", orderLine.getItemId());
            documents.add(dbRef);
        }

        document = document.append("orderLines", documents);


        return document;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ProductItem> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<ProductItem> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "personId='" + personId + '\'' +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", orderLines=" + orderLines +
                ", _id='" + _id + '\'' +
                ", id='" + itemId + '\'' +
                "} " + super.toString();
    }

    @Override
    public String getPathFileData() {
        return Item.getDataPath() + "order/Order.json";
    }
}
