package com.miage.bigdata.daos.loader;

import com.miage.bigdata.models.Item;
import com.opencsv.*;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvLoader<T extends Item> extends Loader<T> {

    @Override
    public List<T> load(Class<T> cl, String path) {
        try {
            ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(cl);
            strategy.setColumnMapping(getColumns(cl));

            CSVReader reader = new CSVReaderBuilder(new InputStreamReader(
                    new FileInputStream(path), StandardCharsets.UTF_8
            ))
                    .withCSVParser(new CSVParserBuilder()
                            .withSeparator('|')
                            .withQuoteChar('\'')
                            .build()
                    )
                    .build();
            CsvToBean<T> csv = new CsvToBean<>();

            return csv.parse(strategy, reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String[] getColumns(Class cl) {
        Field[] fields = cl.getDeclaredFields();
        String[] columns = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            columns[i] = fields[i].getName();
            System.out.println("col: " + columns[i]);
        }

        return columns;
    }
}
