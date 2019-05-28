package com.miage.bigdata.daos.loader;

import javax.xml.bind.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class XmlLoader<T> extends Loader {

    @Override
    public List<T> load(Class cl, String path) {
        try {
            XMLInputFactory xif = XMLInputFactory.newFactory();
            XMLStreamReader xsr = xif.createXMLStreamReader(new FileReader(path));
            xsr.nextTag();

            JAXBContext jaxbContext = JAXBContext.newInstance(cl);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            JAXBElement elements = jaxbUnmarshaller.unmarshal(xsr, cl);
            T result = (T) elements.getValue();

            return Arrays.asList(result);
        } catch (JAXBException | FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T load(Class cl, String path, boolean isRootElement) {
        if(isRootElement) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(cl);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                return (T) jaxbUnmarshaller.unmarshal(new FileReader(path));
            } catch (JAXBException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
