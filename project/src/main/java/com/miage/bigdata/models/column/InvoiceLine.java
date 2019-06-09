package com.miage.bigdata.models.column;

import com.datastax.driver.core.Row;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Orderline")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvoiceLine extends ColumnItem {
    @XmlElement(name = "asin")
    private String asin;
    @XmlElement(name = "title")
    private String title;
    @XmlElement(name = "price")
    private double price;
    @XmlElement(name = "brand")
    private String brand;
    @XmlElement(name = "productId")
    private int productId;

    public InvoiceLine() {

    }

    public InvoiceLine(String id, String asin, String title, double price, String brand) {
        this.asin = asin;
        this.id = id;
        this.title = title;
        this.price = price;
        this.brand = brand;
    }

    public InvoiceLine(Row row) {
        super();
        this.id = row.getString("productId");
        this.asin = row.getString("asin");
        this.title = row.getString("title");
        this.price = row.getDouble("price");
        this.brand = row.getString("brand");
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String getId() {
        return String.valueOf(getProductId());
    }

    @Override
    public String toString() {
        return "InvoiceLine{" +
                "asin='" + asin + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", id='" + id + '\'' +
                "} " + super.toString();
    }
}
