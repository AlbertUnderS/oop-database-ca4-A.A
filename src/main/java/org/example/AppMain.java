package org.example;

import org.example.dao.ExpenseDAO;
import org.example.dto.Expense;

import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class AppMain {
    public static void main(String[] args) {
        AppMain appMain = new AppMain();
        appMain.start();
    }

    public void start() {
        ExpenseDAO expenseDAO = new ExpenseDAO();
        Scanner scanner = new Scanner(System.in);

        // Display all expenses
        System.out.println("\n📋 List of Expenses:");
        List<Expense> expenses = expenseDAO.getAllExpenses();
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }

        // Display total spending
        System.out.println("\n💰 Total Spending: €" + expenseDAO.getTotalSpending());

        // Ask user if they want to add a new expense
        System.out.print("\n➕ Do you want to add a new expense? (yes/no): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("yes")) {
            try {
                // Get user input
                System.out.print("Enter title: ");
                String title = scanner.nextLine();

                System.out.print("Enter category: ");
                String category = scanner.nextLine();

                System.out.print("Enter amount: ");
                double amount = Double.parseDouble(scanner.nextLine());

                System.out.print("Enter date (yyyy-MM-dd): ");
                String dateString = scanner.nextLine();

                // Convert input string to java.util.Date
                Date dateIncurred = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

                // Convert java.util.Date to java.sql.Date
                java.sql.Date sqlDate = new java.sql.Date(dateIncurred.getTime());

                // Add expense to database
                expenseDAO.addExpense(title, category, amount, sqlDate);
            } catch (Exception e) {
                System.out.println("❌ Error adding expense. Please check your input and try again.");
            }
        }

        scanner.close();
    }
}
