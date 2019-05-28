package com.miage.bigdata.utils;

import com.opencsv.bean.AbstractMappingStrategy;

import java.lang.reflect.Field;

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
        Field[] fields = cl.getDeclaredFields();
        String[] columns = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            columns[i] = fields[i].getName();
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
