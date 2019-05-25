package com.miage.bigdata.models.column;

import com.datastax.driver.core.Row;
import com.miage.bigdata.models.Item;

public class ColumnItem extends Item {
    public ColumnItem() {}

    public ColumnItem(Row row) {
    }
}
