package com.miage.bigdata.models.keyvalue;

import com.miage.bigdata.models.Item;
import com.miage.bigdata.utils.CsvConfig;
import com.opencsv.bean.ColumnPositionMappingStrategy;

public class FeedbackItem extends KeyValueItem {
    private String asin;
    private String personId;
    private String feedback;
    private CsvConfig csvConfig;

    public FeedbackItem() {
        initCsvConfig();
    }

    public FeedbackItem(String personId, String feedback) {
        this.personId = personId;
        this.feedback = feedback;
        initCsvConfig();
    }

    public FeedbackItem(String asin, String personId, String feedback) {
        this.asin = asin;
        this.personId = personId;
        this.feedback = feedback;
        initCsvConfig();
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public CsvConfig getCsvConfig() {
        return csvConfig;
    }

    private void initCsvConfig() {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(this.getClass());
        strategy.setColumnMapping(CsvConfig.getColumns(this.getClass()));
        csvConfig = new CsvConfig('|','\'',strategy);
    }

    @Override
    public String toString() {
        return "FeedbackItem{" +
                "PersonId='" + personId + '\'' +
                ", asin=" + asin +
                ", feedback=" + feedback +
            "} " + super.toString();
    }

    @Override
    public String getPathFileData() {
        return Item.getResourcesPath() + "feedback/Feedback.csv";
    }
}
