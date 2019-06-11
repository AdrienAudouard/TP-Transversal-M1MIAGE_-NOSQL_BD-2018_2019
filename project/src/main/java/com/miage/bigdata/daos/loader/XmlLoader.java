package com.miage.bigdata.daos.loader;

import com.miage.bigdata.models.Item;
import com.miage.bigdata.utils.WrapperXml;

import javax.xml.bind.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.List;

public class XmlLoader<T extends Item> extends Loader {

    /**
     * TUTO : http://blog.bdoughan.com/2012/11/creating-generic-list-wrapper-in-jaxb.html
     */
    @Override
    public List<T> load(Class cl, String path) {
        try {
            XMLInputFactory xif = XMLInputFactory.newFactory();
            XMLStreamReader xsr = xif.createXMLStreamReader(new FileReader(path));

            JAXBContext jaxbContext = JAXBContext.newInstance(WrapperXml.class, cl);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            WrapperXml<T> wrapper = (WrapperXml<T>) jaxbUnmarshaller.unmarshal(xsr, WrapperXml.class).getValue();

            return wrapper.getItems();
        } catch (JAXBException | FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return null;
    }
}
