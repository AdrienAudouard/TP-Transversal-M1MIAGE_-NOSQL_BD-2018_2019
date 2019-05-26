package com.miage.bigdata.models.column;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Invoices")
public class InvoicesItem {

    private List<InvoiceItem> invoiceItems;

    public InvoicesItem(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public InvoicesItem() {
    }

    @XmlElement(name = "Invoice.xml")
    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }
}
