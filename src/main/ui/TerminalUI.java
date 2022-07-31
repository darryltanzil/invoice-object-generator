package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import model.InvoiceLineItem;
import model.Invoice;
import model.App;
import persistence.JsonReader;
import persistence.JsonWriter;

/**
 * The TerminalUI class
 * provides a console-based interface in which users can interact with the Invoice Generator App.
 */

public class TerminalUI {
    private App invoiceGeneratorApp;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/workroom.json";

    public TerminalUI() {
        System.out.println("Invoice Generator App Version 0.0.1");
        invoiceGeneratorApp = new App("Invoice Generator");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: saves the workroom to file
    private void saveApp() {
        try {
            jsonWriter.open();
            jsonWriter.write(invoiceGeneratorApp);
            jsonWriter.close();
            System.out.println("Saved " + invoiceGeneratorApp.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadApp() {
        try {
            invoiceGeneratorApp = jsonReader.read();
            System.out.println("Loaded " + invoiceGeneratorApp.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: initializes the terminal UI for the Invoice Generator
    @SuppressWarnings("methodlength")
    public void init(Scanner sc) {
        System.out.println("What would you like to do?");
        while (true) {
            String statement = sc.next();
            switch (statement) {
                case "list":
                case "list invoices":
                    System.out.println("Here is a list of invoices generated:");
                    for (String invoiceNames : invoiceGeneratorApp.getInvoices().keySet()) {
                        System.out.println(invoiceNames);
                    }
                    break;
                case "ililist":
                    String invName = sc.nextLine();
                    if (invoiceGeneratorApp.getInvoices().get(invName) != null) {
                        System.out.println("Here is a list of invoice line items for " + invName);
                        System.out.println("Hours        Rate        Description");
                        for (InvoiceLineItem ili :
                                invoiceGeneratorApp.getInvoices().get(invName).getInvoiceLineItems()) {
                            System.out.println(ili.getHours() + "        " + ili.getRate() + "       " + ili.getDesc());
                        }
                    }
                    break;
                case "add":
                    String invoiceToAdd = sc.nextLine();
                    invoiceGeneratorApp.generateInvoice(invoiceToAdd);
                    break;
                case "e":
                case "exit":
                    System.out.println("Thanks for using the Invoice Generator app!");
                    System.exit(0);
                    break;
                case "del":
                case "delete":
                    String invoiceToDelete = sc.nextLine();
                    invoiceGeneratorApp.deleteInvoice(invoiceToDelete);
                    break;
                case "save":
                    saveApp();
                    break;
                case "load":
                    loadApp();
                    break;
                case "ili":
                    String invoiceName = sc.nextLine();
                    if (invoiceName != null) {
                        System.out.print("Enter Description:");
                        String desc = sc.nextLine();
                        System.out.print("Enter Hours:");
                        Double hours = Double.parseDouble(sc.nextLine());
                        System.out.print("Enter Rate:");
                        Double rate = Double.parseDouble(sc.nextLine());
                        invoiceGeneratorApp.createInvoiceLineItem(invoiceName, desc, hours, rate);
                    }
                default:
                    System.out.println("Invalid command. Enter \"help\" for a list of available commands.");
                    break;
            }
            init(sc);
        }
    }
}
