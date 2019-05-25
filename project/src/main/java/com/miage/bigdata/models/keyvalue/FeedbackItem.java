package com.miage.bigdata.models.keyvalue;

import com.miage.bigdata.models.Item;

public class FeedbackItem extends KeyValueItem {
    private String personId;
    private String feedback;

    public FeedbackItem() {
    }

    public FeedbackItem(String personId, String feedback) {
        personId = personId;
        this.feedback = feedback;
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
            "} " + super.toString();
    }

    @Override
    public String getPathFileData() {
        return Item.getResourcesPath() + "feedback/Feedback.csv";
    }
}
