package com.miage.bigdata.daos.itemDao;

import com.miage.bigdata.models.Item;
import lombok.NonNull;

import java.util.List;

public interface ItemDao<T> {

    String getDatabaseID();

    List<T> readAll();

    T create(T item);

    T getByID(@NonNull String id);

    boolean delete(@NonNull String id);

    T update(@NonNull Item item);

    String generateID();

    boolean createTable();

    boolean populateTable();

    boolean deleteTable();

    void reinitializeTable();
}
