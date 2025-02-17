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
}
