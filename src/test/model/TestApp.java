package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestApp {
    private App testApp;

    @BeforeEach
    void runBefore() {
        testApp = new App();
    }

    @Test
    void testGenerateInvoice() {
        assertTrue(testApp.generateInvoice("test invoice"));
        assertEquals(1, testApp.getInvoices().size());
        assertFalse(testApp.generateInvoice("test invoice"));
        assertTrue(testApp.generateInvoice("test invoice 2"));
        assertEquals(2, testApp.getInvoices().size());
    }

    @Test
    void testDeleteInvoice() {
        assertTrue(testApp.generateInvoice("test invoice"));
        assertEquals(1, testApp.getInvoices().size());
        assertTrue(testApp.deleteInvoice("test invoice"));
        assertEquals(0, testApp.getInvoices().size());
        assertFalse(testApp.deleteInvoice("test invoice"));
    }

    @Test
    void testCreateInvoiceLineItem() {
        assertTrue(testApp.generateInvoice("test invoice"));
        assertTrue(testApp.createInvoiceLineItem("test invoice", "desc", 2, 19.25));
    }
}
