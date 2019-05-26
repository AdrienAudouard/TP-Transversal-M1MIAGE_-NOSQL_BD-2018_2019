package com.miage.bigdata.models.graph;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.miage.bigdata.models.Item;

public abstract class GraphItem extends Item {

    public GraphItem() {}

    public GraphItem(String json) {
        JsonParser jsonParser = new JsonParser();

        JsonObject jo = (JsonObject)jsonParser.parse(json);

        this.id = jo.get("id").toString();
    }

    public abstract String getCreateQuery(String verticeName);

    public abstract String getUpdateQuery();

    protected static JsonElement getPropertyInJSON(JsonObject jsonObject, String property) {
        return jsonObject.get(property).getAsJsonArray().get(0).getAsJsonObject().get("value");
    }
}
