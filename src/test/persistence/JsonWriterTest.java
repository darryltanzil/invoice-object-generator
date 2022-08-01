package persistence;

import model.App;
import model.Invoice;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            App app = new App("Invoice Generator");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            App app = new App("Invoice Generator");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyApp.json");
            writer.open();
            writer.write(app);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyApp.json");
            app = reader.read();
            assertEquals("Invoice Generator", app.getName());
            assertEquals(0, app.numInvoices());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            App app = new App("Invoice Generator");
            app.generateInvoice("Invoice 1");
            app.generateInvoice("Invoice 2");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralApp.json");
            writer.open();
            writer.write(app);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralApp.json");
            app = reader.read();
            assertEquals("Invoice Generator", app.getName());
            HashMap<String, Invoice> invoices = app.getInvoices();
            assertEquals(2, invoices.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}