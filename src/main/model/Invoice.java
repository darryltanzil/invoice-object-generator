package model;

import java.util.ArrayList;

public class Invoice {

    private ArrayList<InvoiceLineItem> invoiceItems; // invoice items

    public Invoice() {
    }

    public void attachLineItem(String desc, double hours, double rate) {
        invoiceItems.add(new InvoiceLineItem(desc, hours, rate));
    }


}
