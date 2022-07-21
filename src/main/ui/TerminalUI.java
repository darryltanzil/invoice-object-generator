package ui;

import java.util.Scanner;
import model.InvoiceLineItem;
import model.Invoice;
import model.App;

public class TerminalUI {

    private App invoiceGeneratorApp;

    public TerminalUI() {
        System.out.println("Invoice Generator App Version 0.0.1");
        invoiceGeneratorApp = new App();
    }

    public void init(Scanner sc) {
        System.out.println("What would you like to do?");

        while (true) {
            String initStatement = sc.next();
            switch (initStatement) {
                case "add":
                    String invoiceName = sc.nextLine();
                    invoiceGeneratorApp.generateInvoice(invoiceName);
                    break;
                case "e":
                case "exit":
                    System.out.println("Thanks for using the Invoice Generator app!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command. Enter \"help\" for a list of available commands.");
            }
            init(sc);
        }
    }
}
