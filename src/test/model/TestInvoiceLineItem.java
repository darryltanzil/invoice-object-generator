package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestInvoiceLineItem {

    private InvoiceLineItem testIli;

    @BeforeEach
    void runBefore () {
        testIli = new InvoiceLineItem("test desc", 2, 19.25);
    }

    @Test
    void testGetterAndSetter() {
        assertTrue(testIli.setDesc("test"));
        assertEquals("test" ,testIli.getDesc());
        assertTrue(testIli.setHours(2));
        assertEquals(2 ,testIli.getDesc());
        assertTrue(testIli.setRate(19.25));
        assertEquals(19.25, testIli.getDesc());
    }
}
