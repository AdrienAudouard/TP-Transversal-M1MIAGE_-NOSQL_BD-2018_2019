package com.miage.bigdata.daos.loader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.miage.bigdata.models.Item;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class JsonLoader<T extends Item> extends Loader<T> {

    private Gson gson = new Gson();
    private String path = "/home/romain/Documents/Cours/Cours-Master-MIAGE/Master-1/Semestre-2/UE-Composants_logiciels_Entreprise/BigData/TP/resources/order/Order.json";

    @Override
    public List load(Class cl) {
        try {
            JsonReader reader = new JsonReader(new FileReader(path));
            return Arrays.asList(gson.fromJson(reader, cl));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
