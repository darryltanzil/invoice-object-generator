package persistence;

import model.App;

import model.Invoice;
import model.InvoiceLineItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 *  The JSONReader class
 *  represents a reader that reads App from JSON data stored in file.
 *  This code was originally forked from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 *  and modified to fit the needs of this project.
  */
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads app from file and returns it;
    // throws IOException if an error occurs reading data from file
    public App read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseApp(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses app from JSON object and returns it
    private App parseApp(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        App app = new App(name);
        addInvoices(app, jsonObject);
        return app;
    }

    // MODIFIES: app
    // EFFECTS: parses thingies from JSON object and adds them to app
    private void addInvoices(App app, JSONObject jsonObject) {
        JSONObject invoicesFromLoadedFile = jsonObject.getJSONObject("invoices");
        invoicesFromLoadedFile.keySet().forEach(keyStr -> {
            Invoice invoice = new Invoice();
            JSONArray invoiceLineItems = invoicesFromLoadedFile.getJSONObject(keyStr).getJSONArray("invoiceLineItems");
            System.out.println("name: " + keyStr + " value: " + invoiceLineItems);

            if (invoiceLineItems.length() > 0) {
                for (int i = 0; i < invoiceLineItems.length(); i++) {
                    JSONObject currentObj = invoiceLineItems.getJSONObject(i);
                    invoice.attachLineItem(new InvoiceLineItem(currentObj.getString("desc"),
                            currentObj.getInt("hours"), currentObj.getInt("rate")));
                }
            }

            app.getInvoices().put(keyStr, invoice);
        });
    }
}
