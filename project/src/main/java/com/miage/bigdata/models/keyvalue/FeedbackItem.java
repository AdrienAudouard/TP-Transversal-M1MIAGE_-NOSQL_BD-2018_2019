package com.miage.bigdata.models.keyvalue;

import com.miage.bigdata.models.Item;
import com.miage.bigdata.utils.CsvConfig;
import com.opencsv.bean.ColumnPositionMappingStrategy;

import java.util.Date;

public class FeedbackItem extends KeyValueItem {
    //region Properties
    private double rating;
    private String review;
    private CsvConfig csvConfig;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public CsvConfig getCsvConfig() {
        return csvConfig;
    }
    //endregion

    //region Constructors
    public FeedbackItem() {
        initCsvConfig();
    }

    public FeedbackItem(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
        initCsvConfig();
    }

    public FeedbackItem(String partitionKey, String rowKey, double rating, String review) {
        super(partitionKey, rowKey);
        this.rating = rating;
        this.review = review;
        initCsvConfig();
    }

    public FeedbackItem(String partitionKey, String rowKey, Date timestamp, double rating, String review) {
        super(partitionKey, rowKey, timestamp);
        this.rating = rating;
        this.review = review;
        initCsvConfig();
    }

    public FeedbackItem(String partitionKey, String rowKey, Date timestamp, String eTag, double rating, String review) {
        super(partitionKey, rowKey, timestamp, eTag);
        this.rating = rating;
        this.review = review;
        initCsvConfig();
    }
    //endregion

    //region Methods
    private void initCsvConfig() {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(this.getClass());
        strategy.setColumnMapping(CsvConfig.getColumns(this.getClass()));
        csvConfig = new CsvConfig('|','\'',strategy);
    }

    @Override
    public String toString() {
        return "FeedbackItem{" +
                "rating='" + getRating() + ",\'" +
                "review='" + getReview() + "\'" +
                "}, inherits " + super.toString();
    }

    @Override
    public String getPathFileData() {
        return Item.getDataPath() + "feedback/Feedback.csv";
    }
    //endregion
}
