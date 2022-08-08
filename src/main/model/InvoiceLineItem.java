package model;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * The InvoiceLineItem class
 * represents specific items within an invoice.
 * It includes a description of the task, how many hours you've worked on that task, and the rate you charge at.
 */

public class InvoiceLineItem {
    private double hours;
    private String desc;
    private double rate;

    private int id;

    public InvoiceLineItem(String desc, double hours, double rate) {
        this.hours = hours;
        this.rate = rate;
        this.desc = desc;
    }

    public Boolean setDesc(String value) {
        desc = value;
        return true;
    }

    public Boolean setHours(double value) {
        hours = value;
        return true;
    }

    public Boolean setRate(double value) {
        rate = value;
        return true;
    }

    public double getHours() {
        return hours;
    }

    public String getDesc() {
        return desc;
    }

    public double getRate() {
        return rate;
    }
}
