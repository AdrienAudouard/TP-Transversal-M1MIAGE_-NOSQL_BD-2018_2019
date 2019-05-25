package com.miage.bigdata.daos.loader;

import com.miage.bigdata.models.Item;
import com.miage.bigdata.models.keyvalue.FeedbackItem;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.List;

public class CsvLoader<T extends Item> extends Loader<T> {

    private String path = "/home/romain/Documents/Cours/Cours-Master-MIAGE/Master-1/Semestre-2/UE-Composants_logiciels_Entreprise/BigData/TP/resources/feedback/Feedback.csv";

    @Override
    public List<T> load(Class<T> cl) {
        try {
            String[] columns = getColumns(cl);

            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(cl);
            strategy.setColumnMapping(columns);

            CSVReader reader = new CSVReader(new FileReader(path));
            CsvToBean csv = new CsvToBean();

            return csv.parse(strategy, reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String[] getColumns(Class<T> cl) {
        Field[] fields = cl.getDeclaredFields();
        String[] columns = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            columns[i] = fields[i].getName();
        }

        return columns;
    }
}
