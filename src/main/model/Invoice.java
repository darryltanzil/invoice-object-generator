package model;

import java.util.ArrayList;

public class Invoice {

    private ArrayList<InvoiceLineItem> invoiceItems; // invoice items
    private String name;

    public Invoice(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
