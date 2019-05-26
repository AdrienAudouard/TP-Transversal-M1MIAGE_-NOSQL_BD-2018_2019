package com.miage.bigdata.models.column;

import com.datastax.driver.core.Row;

import java.util.Date;

public class InvoiceItem extends ColumnItem{
    private String personId;
    private Date orderDate;
    private Double totalPrice;
    protected final InvoiceLine orderLine;

    public InvoiceItem(Row row, Row lineRow) {
        super();

        personId = row.getString("personId");
        orderDate = row.getTimestamp("orderDate");
        totalPrice = row.getDouble("totalPrice");
        id = row.getString("orderId");
        this.orderLine = new InvoiceLine(lineRow);
    }

    public InvoiceItem(String id, String personId, Date orderDate, Double totalPrice, InvoiceLine invoiceLine) {
        this.personId = personId;
        this.id = id;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderLine = invoiceLine;
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

    public InvoiceLine getOrderLine() {
        return orderLine;
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "personId='" + personId + '\'' +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", orderLine=" + orderLine +
                ", id='" + id + '\'' +
                "} ";
    }
}
