package com.miage.bigdata.models.document;

import com.miage.bigdata.models.Item;
import com.miage.bigdata.utils.CsvConfig;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.bson.Document;

public class ProductItem extends DocumentItem {
    private String asin;
    private String title;
    private double price;
    private String imgURL;
    private String brand;
    private CsvConfig csvConfig;

    public ProductItem() {
        initCsvConfig();
    }

    public ProductItem(String id, String title, String asin, String imgURL, String brand, double price) {
        this.asin = asin;
        this.title = title;
        this.price = price;
        this.imgURL = imgURL;
        this.brand = brand;
        this.id = id;
        initCsvConfig();
    }

    public ProductItem(String id, String title, double price, String imgURL, String brand) {
        this.title = title;
        this.price = price;
        this.imgURL = imgURL;
        this.brand = brand;
        this.id = id;
        initCsvConfig();
    }

    public ProductItem(Document document) {
        super(document);

        this.title = document.getString("title");
        this.price = document.getDouble("price");
        this.imgURL = document.getString("imgURL");
        this.brand = document.getString("brand");
        this.id = document.getString("asin");
        initCsvConfig();
    }

    @Override
    public Document toDocument() {
        return new Document("title", title)
                .append("price", price)
                .append("imgURL", imgURL)
                .append("id", id)
                .append("brand", brand)
                .append("asin", id);
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

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public CsvConfig getCsvConfig() {
        return csvConfig;
    }

    private void initCsvConfig() {
        HeaderColumnNameMappingStrategy strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(this.getClass());
        csvConfig = new CsvConfig(',','\"',strategy);
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", imgURL='" + imgURL + '\'' +
                ", brand='" + brand + '\'' +
                ", id='" + id + '\'' +
                ", _id='" + _id + '\'' +
                "} " + super.toString();
    }

    @Override
    public String getPathFileData() {
        return Item.getResourcesPath() + "product/Product.csv";
    }
}
