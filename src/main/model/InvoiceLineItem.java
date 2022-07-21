package model;

import com.sun.org.apache.xpath.internal.operations.Bool;

// InvoiceLineItem represents specific items within invoice
// It includes a description of the task, how many hours you've worked on that task, and the rate you charge at.
public class InvoiceLineItem {
    private int hours;
    private String desc;
    private int rate;

    public InvoiceLineItem(int hours, String desc, int rate) {
        this.hours = hours;
        this.desc = desc;
        this.rate = rate;
    }

    public void setDesc(String value) {
        desc = value;
    }

    public void setHours(int value) {
        hours = value;
    }

    public void setRate(int value) {
        rate = value;
    }

    public int getHours() {
        return hours;
    }

    public String getDesc() {
        return desc;
    }

    public int getRate() {
        return rate;
    }
}
