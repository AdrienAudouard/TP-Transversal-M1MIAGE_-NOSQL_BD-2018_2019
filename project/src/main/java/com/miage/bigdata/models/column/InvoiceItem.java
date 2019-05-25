package com.miage.bigdata.models.column;

import com.datastax.driver.core.Row;

import java.util.Date;

public class InvoiceItem extends ColumnItem{
    private String personId;
    private Date orderDate;
    private Double totalPrice;

    public InvoiceItem(Row row) {
        super(row);

        personId = row.getString("personId");
        orderDate = row.getTimestamp("orderDate");
        totalPrice = row.getDouble("totalPrice");
        id = row.getString("orderId");
    }

    public InvoiceItem(String id, String personId, Date orderDate, Double totalPrice) {
        this.personId = personId;
        this.id = id;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public String getPersonId() {
        return personId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "personId='" + personId + '\'' +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", id='" + id + '\'' +
                "} " + super.toString();
    }
}
