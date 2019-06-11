package com.miage.bigdata.utils;

import com.opencsv.bean.AbstractMappingStrategy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvConfig {
    private char separator;
    private char quoteChar;
    private AbstractMappingStrategy strategy;

    public CsvConfig() {
    }

    public CsvConfig(char separator, char quoteChar, AbstractMappingStrategy strategy) {
        this.separator = separator;
        this.quoteChar = quoteChar;
        this.strategy = strategy;
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public char getQuoteChar() {
        return quoteChar;
    }

    public void setQuoteChar(char quoteChar) {
        this.quoteChar = quoteChar;
    }

    public AbstractMappingStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(AbstractMappingStrategy strategy) {
        this.strategy = strategy;
    }

    public static String[] getColumns(Class cl) {
        List<Field> declaredFields = Arrays.asList(cl.getDeclaredFields());
        List<Field> fields = Arrays.asList(cl.getFields());

        ArrayList<Field> allFields = new ArrayList<>(declaredFields);
        for (Field field : fields) {
            if (!allFields.contains(field)) {
                allFields.add(field);
            }
        }

        String[] columns = new String[allFields.size()];

        for (int i = 0; i < allFields.size(); i++) {
            columns[i] = allFields.get(i).getName();
        }

        return columns;
    }

    @Override
    public String toString() {
        return "CsvConfig{" +
                "separator='" + separator + '\'' +
                ", quoteChar=" + quoteChar +
                ", strategy=" + strategy.toString() +
            "} " + super.toString();
    }
}
