package com.miage.bigdata.daos.loader;

import com.miage.bigdata.models.Item;
import com.opencsv.*;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class CsvLoader<T extends Item> extends Loader<T> {

    @Override
    public List<T> load(Class<T> cl, String path, String[] properties) {
        try {

            ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(cl);
            strategy.setColumnMapping(properties);

            CSVReader reader = new CSVReaderBuilder(new FileReader(path))
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
}
