package persistence;

import model.App;
import model.Invoice;
import model.InvoiceLineItem;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            App app = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            App app = reader.read();
            assertEquals("Invoice Generator", app.getName());
            assertEquals(0, app.numInvoices());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            App app = reader.read();
            assertEquals("Invoice Generator", app.getName());
            HashMap<String, Invoice> invoices = app.getInvoices();
            assertEquals(2, invoices.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}