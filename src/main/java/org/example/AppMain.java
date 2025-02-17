package org.example;

import org.example.dao.ExpenseDAO;
import org.example.dao.IncomeDAO;
import org.example.dto.Expense;
import org.example.dto.Income;

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
        IncomeDAO incomeDAO = new IncomeDAO();
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

        // Display all income
        System.out.println("\n📋 List of Income:");
        List<Income> incomeList = incomeDAO.getAllIncome();
        if (incomeList.isEmpty()) {
            System.out.println("No income records found.");
        } else {
            for (Income income : incomeList) {
                System.out.println(income);
            }
        }

        // Display total income
        System.out.println("\n💵 Total Income: €" + incomeDAO.getTotalIncome());

        // Ask user if they want to add a new expense
        System.out.print("\n➕ Do you want to add a new expense? (yes/no): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("yes")) {
            try {
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
                System.out.println("✅ Expense added successfully!");
            } catch (Exception e) {
                System.out.println("❌ Error adding expense. Please check your input and try again.");
            }
        }

        // Ask user if they want to delete an expense
        System.out.print("\n🗑 Do you want to delete an expense? (yes/no): ");
        String deleteChoice = scanner.nextLine();

        if (deleteChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter the ID of the expense to delete: ");
            int expenseID = Integer.parseInt(scanner.nextLine());
            expenseDAO.deleteExpenseById(expenseID);
        }

        System.out.print("\n➕ Do you want to add a new income? (yes/no): ");
        String incomeChoice = scanner.nextLine();

        if (incomeChoice.equalsIgnoreCase("yes")) {
            try {
                System.out.print("Enter title: ");
                String title = scanner.nextLine();

                System.out.print("Enter amount: ");
                double amount = Double.parseDouble(scanner.nextLine());

                System.out.print("Enter date (yyyy-MM-dd): ");
                String dateString = scanner.nextLine();

                // Convert input string to java.util.Date
                Date dateEarned = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

                // Convert java.util.Date to java.sql.Date
                java.sql.Date sqlDate = new java.sql.Date(dateEarned.getTime());

                // Add income to database
                incomeDAO.addIncome(title, amount, sqlDate);
            } catch (Exception e) {
                System.out.println("❌ Error adding income. Please check your input and try again.");
            }
        }

        scanner.close();
    }
}
