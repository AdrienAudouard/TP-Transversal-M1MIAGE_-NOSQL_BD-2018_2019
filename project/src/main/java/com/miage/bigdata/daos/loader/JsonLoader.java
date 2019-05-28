package com.miage.bigdata.daos.loader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.miage.bigdata.models.Item;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class JsonLoader<T extends Item> extends Loader<T> {

    private Gson gson = new Gson();

    @Override
    public List load(Class cl, String path, String[] properties) {
        T[] tList = (T[]) Array.newInstance(cl, 0);
        try {
            JsonReader reader = new JsonReader(new FileReader(path));
            return Arrays.asList(gson.fromJson(reader, tList.getClass()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
