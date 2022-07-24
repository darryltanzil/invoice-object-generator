package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestInvoice {
    private Invoice testInvoice;
    private InvoiceLineItem testIli;

    @BeforeEach
    void runBefore() {
        testInvoice = new Invoice();
        testIli = new InvoiceLineItem("test description", 2, 19.25);
    }

    @Test
    void testAttachLineItem() {
        ArrayList<InvoiceLineItem> supposedResult = new ArrayList<>();
        supposedResult.add(testIli);
        assertTrue(testInvoice.attachLineItem(testIli));
        assertTrue(supposedResult.get(0).equals(testInvoice.getInvoiceLineItems().get(0)));
    }

}