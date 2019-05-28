package com.miage.bigdata.models.keyvalue;

import com.miage.bigdata.models.Item;

public class FeedbackItem extends KeyValueItem {
    private String asin;
    private String personId;
    private String feedback;

    public FeedbackItem() {
    }

    public FeedbackItem(String personId, String feedback) {
        this.personId = personId;
        this.feedback = feedback;
    }

    public FeedbackItem(String asin, String personId, String feedback) {
        this.asin = asin;
        this.personId = personId;
        this.feedback = feedback;
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

    @Override
    public String toString() {
        return "FeedbackItem{" +
                "PersonId='" + personId + '\'' +
                ", feedback=" + feedback +
                ", asin=" + asin +
            "} " + super.toString();
    }

    @Override
    public String getPathFileData() {
        return Item.getResourcesPath() + "feedback/Feedback.csv";
    }
}
