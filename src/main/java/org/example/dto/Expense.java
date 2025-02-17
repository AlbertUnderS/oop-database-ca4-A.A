package org.example.dto;

import java.util.Date;

public class Expense {
    private int expenseID;
    private String title;
    private String category;
    private double amount;
    private Date dateIncurred;

    // Constructor
    public Expense(int expenseID, String title, String category, double amount, Date dateIncurred) {
        this.expenseID = expenseID;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.dateIncurred = dateIncurred;
    }

    // Getters and Setters
    public int getExpenseID() { return expenseID; }
    public void setExpenseID(int expenseID) { this.expenseID = expenseID; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getDateIncurred() { return dateIncurred; }
    public void setDateIncurred(Date dateIncurred) { this.dateIncurred = dateIncurred; }

    @Override
    public String toString() {
        return expenseID + " | " + title + " | " + category + " | €" + amount + " | " + dateIncurred;
    }
}
