package com.miage.bigdata.daos.loader;

import com.miage.bigdata.models.Item;
import com.miage.bigdata.models.column.InvoiceItem;
import com.miage.bigdata.models.column.InvoicesItem;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

public class XmlLoader<T extends Item> extends Loader<T> {

    @Override
    public List<T> load(Class<T> cl, String path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(cl);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            /*InvoicesItem result = (InvoicesItem) jaxbUnmarshaller.unmarshal(new File(path));
            for (InvoiceItem invoiceItem : result.getInvoiceItems()) {
                System.out.println(invoiceItem.toString());
            }*/

            return (List<T>) jaxbUnmarshaller.unmarshal(new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
