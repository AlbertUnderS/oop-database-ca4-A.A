package org.example;

import org.example.dao.ExpenseDAO;
import org.example.dto.Expense;

import java.util.List;

public class AppMain {
    public static void main(String[] args) {
        AppMain appMain = new AppMain();
        appMain.start();
    }

    public void start() {
        ExpenseDAO expenseDAO = new ExpenseDAO();

        System.out.println("\n📋 List of Expenses:");
        List<Expense> expenses = expenseDAO.getAllExpenses();

        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }

        double totalSpending = expenseDAO.getTotalSpending();
        System.out.println("\n💰 Total Spending: €" + totalSpending);
    }
}

