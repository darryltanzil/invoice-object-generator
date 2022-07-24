package ui;

import java.util.Scanner;
import model.InvoiceLineItem;
import model.Invoice;
import model.App;

/**
 * The TerminalUI class
 * provides a console-based interface in which users can interact with the Invoice Generator App.
 */

public class TerminalUI {
    private App invoiceGeneratorApp;

    public TerminalUI() {
        System.out.println("Invoice Generator App Version 0.0.1");
        invoiceGeneratorApp = new App();
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
                    System.out.println("Here is a list of invoices to generate:");
                    for (String invoiceNames : invoiceGeneratorApp.getInvoices().keySet()) {
                        System.out.println(invoiceNames);
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
                case "ili":
                    String invoiceName = sc.nextLine();
                    String desc = sc.nextLine();
                    Double hours = Double.parseDouble(sc.nextLine());
                    Double rate = Double.parseDouble(sc.nextLine());
                    invoiceGeneratorApp.createInvoiceLineItem(invoiceName, desc, hours, rate);
                default:
                    System.out.println("Invalid command. Enter \"help\" for a list of available commands.");
                    break;
            }
            init(sc);
        }
    }
}
