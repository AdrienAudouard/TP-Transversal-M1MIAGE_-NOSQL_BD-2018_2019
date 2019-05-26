package com.miage.bigdata.models.column;

import com.datastax.driver.core.Row;

public class InvoiceLine extends ColumnItem {
    private String asin;
    private String title;
    private double price;
    private String brand;

    public InvoiceLine(String id, String asin, String title, double price, String brand) {
        this.asin = asin;
        this.id = id;
        this.title = title;
        this.price = price;
        this.brand = brand;
    }

    public InvoiceLine(Row row) {
        super(row);
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
