package com.miage.bigdata.models.column;

import com.datastax.driver.core.Row;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Orderline")
public class InvoiceLine extends ColumnItem {
    private String asin;
    private String title;
    private double price;
    private String brand;
    private int productId;

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

    @XmlElement(name = "asin")
    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @XmlElement(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @XmlElement(name = "productId")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
