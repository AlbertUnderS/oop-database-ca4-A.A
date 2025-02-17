package org.example.dto;

import java.sql.Date;

public class Income {
    private int incomeID;
    private String title;
    private double amount;
    private Date dateEarned;

    public Income(int incomeID, String title, double amount, Date dateEarned) {
        this.incomeID = incomeID;
        this.title = title;
        this.amount = amount;
        this.dateEarned = dateEarned;
    }

    public int getIncomeID() { return incomeID; }
    public String getTitle() { return title; }
    public double getAmount() { return amount; }
    public Date getDateEarned() { return dateEarned; }

    @Override
    public String toString() {
        return incomeID + ", " + title + ", €" + amount + ", " + dateEarned;
    }
}
