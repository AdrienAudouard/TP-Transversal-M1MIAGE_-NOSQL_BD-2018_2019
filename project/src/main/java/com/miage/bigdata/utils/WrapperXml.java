package com.miage.bigdata.utils;

import com.miage.bigdata.models.Item;

import javax.xml.bind.annotation.XmlAnyElement;
import java.util.ArrayList;
import java.util.List;

public class WrapperXml<T extends Item> {

    private List<T> items = new ArrayList<T>();

    @XmlAnyElement(lax=true)
    public List<T> getItems() {
        return items;
    }

}
