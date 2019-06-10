package com.miage.bigdata.models.document;

import com.miage.bigdata.models.Item;
import com.miage.bigdata.utils.CsvConfig;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.bson.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Orderline")
public class ProductItem extends DocumentItem {
    private int productId;
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
        this.itemId = id;
        initCsvConfig();
    }

    public ProductItem(String id, String title, double price, String imgURL, String brand) {
        this.title = title;
        this.price = price;
        this.imgURL = imgURL;
        this.brand = brand;
        this.itemId = id;
        initCsvConfig();
    }

    public ProductItem(Document document) {
        super(document);

        this.title = document.getString("title");
        this.price = document.getDouble("price");
        this.imgURL = document.getString("imgURL");
        this.brand = document.getString("brand");
        this.itemId = document.getString("asin");
        initCsvConfig();
    }

    public ProductItem(int productId, String asin, String title, double price, String brand) {
        this.productId = productId;
        this.asin = asin;
        this.title = title;
        this.price = price;
        this.brand = brand;
    }

    @Override
    public Document toDocument() {
        return new Document("title", title)
                .append("price", price)
                .append("imgURL", imgURL)
                .append("id", itemId)
                .append("brand", brand)
                .append("asin", itemId);
    }

    @XmlElement(name = "productId")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @XmlElement(name = "brand")
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
                ", id='" + itemId + '\'' +
                ", _id='" + _id + '\'' +
                "} " + super.toString();
    }

    @Override
    public String getPathFileData() {
        return Item.getDataPath() + "product/Product.csv";
    }
}
