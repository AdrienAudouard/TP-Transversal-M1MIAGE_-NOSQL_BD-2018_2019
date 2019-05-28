package com.miage.bigdata.daos.loader;

import com.miage.bigdata.models.Item;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class Loader<T extends Item> {

    public abstract List<T> load(Class<T> cl, String path);

    protected T instanciateItemFromDocument(Class<T> cl, String type) {
        try {
            return cl.getConstructor(String.class).newInstance(type);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected String getTypeDataFile(String path) {
        return path.split(".")[1];
    }
}
