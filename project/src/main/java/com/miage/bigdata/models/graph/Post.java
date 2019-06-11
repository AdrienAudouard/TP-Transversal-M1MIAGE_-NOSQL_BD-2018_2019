package com.miage.bigdata.models.graph;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.miage.bigdata.models.Item;
import com.miage.bigdata.utils.CsvConfig;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Post extends GraphItem {
    private Date createDate;
    private final String location;
    private final String browserUsed;
    private final String content;
    private final int length;
    private CsvConfig csvConfig;

    public Post() {
        initCsvConfig();

        location = "";
        browserUsed = "";
        content = "";
        length = 0;
    }

    public Post(String json) {
        super(json);

        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject)jsonParser.parse(json);
        JsonObject properties = jo.getAsJsonObject("properties");



        SimpleDateFormat format = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

        try {
            this.createDate = format.parse(getPropertyInJSON(properties, "createDate").toString().replace("\"", ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.location = getPropertyInJSON(properties, "location").toString();
        this.browserUsed = getPropertyInJSON(properties, "browserUsed").toString();
        this.content = getPropertyInJSON(properties, "content").toString();
        this.length = getPropertyInJSON(properties, "length").getAsInt();


    }

    public Post(String id, String location, String browserUsed, String content, int length) {
        this.itemId = id;
        this.createDate = new Date();
        this.location = location;
        this.browserUsed = browserUsed;
        this.content = content;
        this.length = length;
    }

    @Override
    public String getCreateQuery(String verticeName) {
        return "g.addV('" + verticeName + "')" +
                ".property('id', '"+ itemId +"')" +
                ".property('content', '"+ content +"')" +
                ".property('length', '"+ length +"')" +
                ".property('createDate', '"+ createDate.toString() +"')" +
                ".property('location', '"+ location +"')" +
                ".property('browserUsed', '"+ browserUsed +"')";
    }

    @Override
    public String getUpdateQuery() {
        return "g.addV('"+ itemId +"')" +
                ".property('content', '"+ content +"')" +
                ".property('length', '"+ length +"')" +
                ".property('createDate', '"+ createDate.toString() +"')" +
                ".property('location', '"+ location +"')" +
                ".property('browserUsed', '"+ browserUsed +"')";
    }

    @Override
    public String toString() {
        return "Post{" +
                "createDate=" + createDate +
                ", location='" + location + '\'' +
                ", browserUsed='" + browserUsed + '\'' +
                ", content='" + content + '\'' +
                ", length=" + length +
                ", id='" + itemId + '\'' +
                "} ";
    }

    private void initCsvConfig() {
        HeaderColumnNameMappingStrategy strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(this.getClass());
        csvConfig = new CsvConfig('|','\"',strategy);
    }

    @Override
    public String getPathFileData() {
        return Item.getDataPath() + "socialNetwork/post_0_0.csv";
    }
}
