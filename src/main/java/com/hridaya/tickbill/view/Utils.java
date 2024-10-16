package com.hridaya.tickbill.view;

import com.hridaya.tickbill.database.DbConnection;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.Statement;

public class Utils {

    public static void executeUpdate(String sql, String successMessage) {
        try (Statement st = DbConnection.getConnection().createStatement()) {
            st.executeUpdate(sql);
            showInfo(successMessage);
        } catch (SQLException e) {
            showError("Error executing database operation", e);
        }
    }

    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showError(String message, Exception e) {
        showError(message + ": " + e.getMessage());
    }

    public static void showInfo(String message) {
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static double parseDoubleField(JTextField textField) {
        try {
            return Double.parseDouble(textField.getText());
        } catch (NumberFormatException e) {
            showError("Invalid input: " + textField.getText());
            return 0.0;
        }
    }

    public static int parseIntField(JTextField textField) {
        try {
            return Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            showError("Invalid input: " + textField.getText());
            return 0;
        }
    }
}
