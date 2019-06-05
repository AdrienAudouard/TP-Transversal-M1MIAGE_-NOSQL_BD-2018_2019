package com.miage.bigdata.models.column;

import com.miage.bigdata.models.Item;
import com.miage.bigdata.models.document.ProductItem;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "Invoice.xml")
public class InvoiceItem extends ColumnItem {

    private List<InvoiceItem> invoices;

    private String personId;
    private String orderDate;
    private Double totalPrice;
    private List<ProductItem> orderLines;

    public InvoiceItem() {
    }

    public InvoiceItem(String personId, String orderDate, Double totalPrice, List<ProductItem> orderLines) {
        this.personId = personId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderLines = orderLines;
    }

    public InvoiceItem(String personId, String orderDate, Double totalPrice) {
        this.personId = personId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    @XmlElement(name = "Invoices")
    public List<InvoiceItem> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<InvoiceItem> invoices) {
        this.invoices = invoices;
    }

    @XmlElement(name = "PersonId")
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @XmlElement(name = "OrderDate")
    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
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
    public List<ProductItem> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<ProductItem> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "personId='" + personId + '\'' +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", orderLines=" + orderLines +
                ", id='" + id + '\'' +
                "} " + super.toString();
    }

    @Override
    public String getPathFileData() {
        return Item.getResourcesPath() + "invoice/Invoice.xml";
    }
}
