package model;

import com.sun.org.apache.xpath.internal.operations.Bool;

// InvoiceLineItem represents specific items within invoice
// It includes a description of the task, how many hours you've worked on that task, and the rate you charge at.
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

    public void setDesc(String value) {
        desc = value;
    }

    public void setHours(double value) {
        hours = value;
    }

    public void setRate(double value) {
        rate = value;
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
