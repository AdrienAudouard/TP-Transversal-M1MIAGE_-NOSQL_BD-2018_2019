package com.miage.bigdata.models.graph;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.miage.bigdata.models.Item;
import com.miage.bigdata.utils.CsvConfig;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Person extends GraphItem {
    @CsvBindByName
    private String firstName;
    @CsvBindByName
    private String lastName;
    @CsvBindByName
    private String gender;
    @CsvBindByName
    @CsvDate
    private Date birthdate = new Date();
    private Date createDate = new Date();
    @CsvBindByName(column = "locationIP")
    private String location;
    @CsvBindByName
    private String browserUsed;
    @CsvBindByName
    private int place;
    private CsvConfig csvConfig;

    public Person() {
        initCsvConfig();
    }

    public Person(String id, String firstName, String lastName, String gender, Date birthdate, String location, String browserUsed, int place) {
        this.createDate = new Date();
        this.firstName = firstName;
        this.itemId = id;
        this.lastName = lastName;
        this.gender = gender;
        this.birthdate = birthdate;
        this.location = location;
        this.browserUsed = browserUsed;
        this.place = place;
    }

    public Person(String json) {
        super(json);

        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject)jsonParser.parse(json);
        JsonObject properties = jo.getAsJsonObject("properties");

        SimpleDateFormat format = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);


        this.firstName = getPropertyInJSON(properties, "firstName").toString();
        this.lastName = getPropertyInJSON(properties, "lastName").toString();
        this.gender = getPropertyInJSON(properties, "gender").toString();

        try {
            this.birthdate = format.parse(getPropertyInJSON(properties, "birthdate").toString().replace("\"", ""));
            this.createDate = format.parse(getPropertyInJSON(properties, "createDate").toString().replace("\"", ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.location = getPropertyInJSON(properties, "location").toString();
        this.browserUsed = getPropertyInJSON(properties, "browserUsed").toString();
        this.place = getPropertyInJSON(properties, "place").getAsInt();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBrowserUsed() {
        return browserUsed;
    }

    public void setBrowserUsed(String browserUsed) {
        this.browserUsed = browserUsed;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate=" + birthdate +
                ", createDate=" + createDate +
                ", location='" + location + '\'' +
                ", browserUsed='" + browserUsed + '\'' +
                ", place=" + place +
                ", id='" + itemId + '\'' +
                "} ";
    }



    @Override
    public String getCreateQuery(String verticeName) {
        return "g.addV('" + verticeName + "')" +
                ".property('id', '"+ itemId +"')" +
                ".property('firstName', '"+ firstName +"')" +
                ".property('lastName', '"+ lastName +"')" +
                ".property('gender', '"+ gender +"')" +
                ".property('birthdate', '"+ birthdate.toString() +"')" +
                ".property('createDate', '"+ createDate.toString() +"')" +
                ".property('location', '"+ location +"')" +
                ".property('browserUsed', '"+ browserUsed +"')" +
                ".property('place', "+ place +")";
    }

    @Override
    public String getUpdateQuery() {
        return "g.V('"+ itemId +"')" +
                ".property('firstName', '"+ firstName +"')" +
                ".property('lastName', '"+ lastName +"')" +
                ".property('gender', '"+ gender +"')" +
                ".property('birthdate', '"+ birthdate.toString() +"')" +
                ".property('createDate', '"+ createDate.toString() +"')" +
                ".property('location', '"+ location +"')" +
                ".property('browserUsed', '"+ browserUsed +"')" +
                ".property('place', "+ place +")";
    }

    private void initCsvConfig() {
        HeaderColumnNameMappingStrategy strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(this.getClass());
        csvConfig = new CsvConfig('|','\'',strategy);
    }

    public CsvConfig getCsvConfig() {
        return csvConfig;
    }

    @Override
    public String getPathFileData() {
        return Item.getDataPath() + "customer/person_0_0.csv";
    }
}
