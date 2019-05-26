package com.miage.bigdata.daos.loader;

import javax.xml.bind.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class XmlLoader<T> extends Loader {

    /*@Override
    public List<T> load(Class<T> cl, String path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(cl);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            *//*InvoicesItem result = (InvoicesItem) jaxbUnmarshaller.unmarshal(new File(path));
            for (InvoiceItem invoiceItem : result.getInvoiceItems()) {
                System.out.println(invoiceItem.toString());
            }*//*

            return (List<T>) jaxbUnmarshaller.unmarshal(new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    @Override
    public List<T> load(Class cl, String path, String[] properties) {
        try {
            XMLInputFactory xif = XMLInputFactory.newFactory();
            XMLStreamReader xsr = xif.createXMLStreamReader(new FileReader(path));
            xsr.nextTag();

            JAXBContext jaxbContext = JAXBContext.newInstance(cl);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            JAXBElement result = jaxbUnmarshaller.unmarshal(xsr, cl);
            T foo = (T) result.getValue();

            return Arrays.asList(foo);
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
