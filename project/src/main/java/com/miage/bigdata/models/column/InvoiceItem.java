package com.miage.bigdata.models.column;

import com.datastax.driver.core.Row;
import com.miage.bigdata.models.Item;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "Invoice.xml")
public class InvoiceItem extends ColumnItem{
    private String personId;
    private Date orderDate;
    private Double totalPrice;
    protected InvoiceLine orderLine;

    public InvoiceItem() {
    }

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


    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @XmlElement(name = "OrderDate")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @XmlElement(name = "TotalPrice")
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @XmlAnyElement
    public InvoiceLine getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(InvoiceLine orderLine) {
        this.orderLine = orderLine;
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "personId='" + personId + '\'' +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", orderLines=" + orderLine +
                ", id='" + id + '\'' +
                "} " + super.toString();
    }

    @Override
    public String getPathFileData() {
        return Item.getResourcesPath() + "invoice/Invoice.xml";
    }
}
