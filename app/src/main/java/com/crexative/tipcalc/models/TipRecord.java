package com.crexative.tipcalc.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TipRecord {

    private double bill;
    private int tipPercentaje;
    private Date timestamp;

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public int getTipPercentaje() {
        return tipPercentaje;
    }

    public void setTipPercentaje(int tipPercentaje) {
        this.tipPercentaje = tipPercentaje;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getTip(){
        return bill*(tipPercentaje/100d);
    }

    public String getDateFormat(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        return simpleDateFormat.format(timestamp);
    }
}
