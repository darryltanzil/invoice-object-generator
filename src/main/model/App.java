package model;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class App {

    private int latestId;
    private HashMap<String, Invoice> invoices;

    public App() {
        this.invoices = new HashMap<String, Invoice>();
    }

    public void generateInvoice(String invoiceName) {
        invoices.put(invoiceName, new Invoice());

        System.out.println("A new invoice has been generated named " + invoiceName);
    }

    public void createInvoiceLineItem(String invoiceName, String desc, int hours, int rate) {

    }

    public HashMap<String, Invoice> getInvoices() {
        return invoices;
    }

}
