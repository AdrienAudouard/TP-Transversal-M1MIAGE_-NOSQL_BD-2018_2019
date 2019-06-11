package com.miage.bigdata.models.column;

import com.datastax.driver.core.Row;
import com.miage.bigdata.models.Item;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "Invoice.xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvoiceItem extends ColumnItem{
    @XmlElement(name = "PersonId")
    private String personId;
    @XmlElement(name = "OrderDate")
    private Date orderDate;
    @XmlElement(name = "TotalPrice")
    private Double totalPrice;
    @XmlElement(name = "Orderline")
    private List<InvoiceLine> orderLine = new ArrayList<>();

    public InvoiceItem() {
    }

    public InvoiceItem(Row row, List<Row> lines) {
        super();

        personId = row.getString("personId");
        orderDate = row.getTimestamp("orderDate");
        totalPrice = row.getDouble("totalPrice");
        itemId = row.getString("orderId");

        for (Row line : lines) {
            orderLine.add(new InvoiceLine(line));
        }
    }

    public InvoiceItem(String id, String personId, Date orderDate, Double totalPrice, List<InvoiceLine> orderLine) {
        this.personId = personId;
        this.itemId = id;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderLine = orderLine;
    }

    public InvoiceItem(String id, String personId, Date orderDate, Double totalPrice, InvoiceLine invoiceLine) {
        this.personId = personId;
        this.itemId = id;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderLine.add(invoiceLine);
    }

    public String getPersonId() {
        return personId;
    }


    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<InvoiceLine> getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(List<InvoiceLine> orderLine) {
        this.orderLine = orderLine;
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "personId='" + personId + '\'' +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", orderLines=" + orderLine +
                ", id='" + itemId + '\'' +
                "} " + super.toString();
    }

    @Override
    public String getPathFileData() {
        return Item.getDataPath() + "invoice/Invoice.xml";
    }
}
