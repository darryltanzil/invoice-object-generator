package model;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class App {

    private ArrayList<Invoice> invoices;

    public void generateInvoice(String invoiceName) {
        invoices.add(new Invoice(invoiceName));

        System.out.println("A new invoice has been generated named " + invoiceName);
    }

}
