package com.miage.bigdata.daos.itemDao;

import com.miage.bigdata.models.Item;
import lombok.NonNull;

import java.util.List;

public interface ItemDao {

    String getDatabaseID();

    List<Item> readAll();

    Item create(Item item);

    Item getByID(@NonNull String id);

    boolean delete(@NonNull String id);

    Item update(@NonNull Item item);

    String generateID();

    boolean createTable();

    boolean populateTable();

    boolean deleteTable();

    void reinitializeTable();
}
