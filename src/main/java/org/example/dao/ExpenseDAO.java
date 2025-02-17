package org.example.dao;

import org.example.dto.Expense;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {
    private final String url = "jdbc:mysql://localhost/databases";
    private final String user = "root";
    private final String password = "";

    // Retrieve all expenses
    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM expenses";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Expense expense = new Expense(
                        rs.getInt("expenseID"),
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getDate("dateIncurred")
                );
                expenses.add(expense);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return expenses;
    }

    // Calculate total spending
    public double getTotalSpending() {
        String query = "SELECT SUM(amount) AS total FROM expenses";
        double total = 0;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return total;
    }
    public void addExpense(String title, String category, double amount, Date dateIncurred) {
        String query = "INSERT INTO expenses (title, category, amount, dateIncurred) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, title);
            stmt.setString(2, category);
            stmt.setDouble(3, amount);
            stmt.setDate(4, new java.sql.Date(dateIncurred.getTime())); // Convert java.util.Date to java.sql.Date

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Expense added successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void deleteExpenseById(int expenseID) {
        String query = "DELETE FROM expenses WHERE expenseID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, expenseID);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Expense deleted successfully!");
            } else {
                System.out.println("No expense found with ID: " + expenseID);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Expense> getExpensesByMonth(int year, int month) {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM expenses WHERE YEAR(dateIncurred) = ? AND MONTH(dateIncurred) = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, year);
            pstmt.setInt(2, month);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                expenses.add(new Expense(
                        rs.getInt("expenseID"),
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getDate("dateIncurred")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error retrieving expenses for the selected month.");
        }
        return expenses;
    }

}
