package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

/**
 * The Invoice class
 * represents an Invoice where users can add or delete invoice line items.
 * Users are meant to generate different invoices for different companies or products that they are working for.
 */

public class Invoice {
    private ArrayList<InvoiceLineItem> invoiceItems; // invoice items
    private App app;

    public Invoice(App app) {
        invoiceItems = new ArrayList<InvoiceLineItem>();
        this.app = app;
    }

    // MODIFIES: this, InvoiceLineItem
    // EFFECTS: creates an Invoice Line Items and associates it with a given invoice
    public Boolean attachLineItem(InvoiceLineItem ili) {
        invoiceItems.add(ili);
        return true;
    }

    public ArrayList<InvoiceLineItem> getInvoiceLineItems() {
        app.getEventLog().logEvent(new Event("Getting invoice line items to be viewed."));
        return invoiceItems;
    }
}
