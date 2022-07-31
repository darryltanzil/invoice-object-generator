package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.HashMap;

/**
 * The App class
 * contains a set of functions meant for generating, deleting, and modifying objects.
 * It is a handler for the entire program.
 */

public class App {

    private HashMap<String, Invoice> invoices;
    private String name;

    public App(String name) {
        this.invoices = new HashMap<String, Invoice>();
        this.name = name;
    }

    // REQUIRES: the name parameter must not equal a key in the invoices hashmap
    // MODIFIES: this
    // EFFECT: generates an invoice
    public Boolean generateInvoice(String invoiceName) {
        if (invoices.containsKey(invoiceName)) {
            System.out.println("Sorry, that invoice name is taken.");
            System.out.println("Consider renaming your new one, or deleting your old one first.");
            return false;
        } else {
            invoices.put(invoiceName, new Invoice());
            System.out.println("A new invoice has been generated named " + invoiceName);
            return true;
        }
    }

    // REQUIRES: the name parameter must not equal a key in the invoices hashmap
    // MODIFIES: this
    // EFFECT: removes an invoice from the app
    public Boolean deleteInvoice(String invoiceName) {
        if (!invoices.containsKey(invoiceName)) {
            System.out.println("An Invoice with that name does not exist. ");
            return false;
        } else {
            invoices.remove(invoiceName);
            return true;
        }
    }

    // MODIFIES: Invoice, InvoiceLineItem
    // EFFECTS: creates an invoice line item and relates it to the given invoice
    public Boolean createInvoiceLineItem(String invoiceName, String desc, double hours, double rate) {
        invoices.get(invoiceName).attachLineItem(new InvoiceLineItem(desc, hours, rate));
        System.out.println("Successfully added the invoice line entry to the " + invoiceName + "record!");
        return true;
    }

    public HashMap<String, Invoice> getInvoices() {
        return invoices;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("invoices", invoicesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONObject invoicesToJson() {
        return new JSONObject(invoices);
    }

    public String getName() {
        return name;
    }

    // EFFECTS: returns the number of invoices.
    public int numInvoices() {
        return invoices.size();
    }
}
