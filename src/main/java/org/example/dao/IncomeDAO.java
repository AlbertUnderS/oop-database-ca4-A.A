package org.example.dao;

import org.example.dto.Income;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncomeDAO {
    private final String url = "jdbc:mysql://localhost/databases";
    private final String user = "root";
    private final String password = "";

    // Get all income records
    public List<Income> getAllIncome() {
        List<Income> incomeList = new ArrayList<>();
        String query = "SELECT * FROM income";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Income income = new Income(
                        rs.getInt("incomeID"),
                        rs.getString("title"),
                        rs.getDouble("amount"),
                        rs.getDate("dateEarned")
                );
                incomeList.add(income);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return incomeList;
    }

    // Calculate total income
    public double getTotalIncome() {
        String query = "SELECT SUM(amount) AS total FROM income";
        double totalIncome = 0.0;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                totalIncome = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return totalIncome;
    }

    public void addIncome(String title, double amount, Date dateEarned) {
        String query = "INSERT INTO income (title, amount, dateEarned) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, title);
            pstmt.setDouble(2, amount);
            pstmt.setDate(3, dateEarned); // Make sure this is java.sql.Date

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Income record added successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("❌ Error inserting income record.");
        }
    }

}
