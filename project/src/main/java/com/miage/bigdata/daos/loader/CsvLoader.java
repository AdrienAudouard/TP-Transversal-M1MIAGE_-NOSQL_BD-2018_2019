package com.miage.bigdata.daos.loader;

import com.miage.bigdata.models.Item;
import com.miage.bigdata.utils.CsvConfig;
import com.opencsv.*;
import com.opencsv.bean.CsvToBean;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvLoader<T extends Item> extends Loader<T> {
//([0-9]{2}:[0-9]{2}:[0-9]{2}).[0-9]{3}\+[0-9]{4}
    @Override
    public List<T> load(Class<T> cl, String path) {
        try {
            Method getCsvConfig = cl.getMethod("getCsvConfig");
            CsvConfig csvConfig = (CsvConfig) getCsvConfig.invoke(cl.newInstance());

            CSVReader reader = new CSVReaderBuilder(new InputStreamReader(
                    new FileInputStream(path), StandardCharsets.UTF_8
            ))
                    .withCSVParser(new CSVParserBuilder()
                            .withSeparator(csvConfig.getSeparator())
                            .withQuoteChar(csvConfig.getQuoteChar())
                            .build()
                    )
                    .build();
            CsvToBean<T> csv = new CsvToBean<>();

            csv.setMappingStrategy(csvConfig.getStrategy());
            csv.setCsvReader(reader);
            return csv.parse();
        } catch (FileNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
