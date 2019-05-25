package com.miage.bigdata.daos.loader;

import com.miage.bigdata.models.Item;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class CsvLoader<T extends Item> extends Loader<T> {

    private String path = "/home/romain/Documents/Cours/Cours-Master-MIAGE/Master-1/Semestre-2/UE-Composants_logiciels_Entreprise/BigData/TP/resources/feedback/Feedback.csv";

    @Override
    public List<T> load(Class<T> cl) {
        try {
            String[] columns = new String[]{"personId","feedback"};

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
}
