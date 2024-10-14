package com.hridaya.tickbill.view;

import com.hridaya.tickbill.database.DbConnection;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.Statement;

public class Utils {
    public static void executeUpdate(String sql, String successMessage, JFrame parentComponent) {
        try (Statement st = DbConnection.getConnection().createStatement()) {
            st.executeUpdate(sql);
            showInfo(successMessage, parentComponent);
        } catch (SQLException e) {
            showError("Error executing database operation", e, parentComponent);
        }
    }

    public static void showError(String message, JFrame parentComponent) {
        JOptionPane.showMessageDialog(parentComponent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showError(String message, Exception e, JFrame parentComponent) {
        e.printStackTrace();
        showError(message + ": " + e.getMessage(), parentComponent);
    }

    public static void showInfo(String message, JFrame parentComponent) {
        JOptionPane.showMessageDialog(parentComponent, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static double parseDoubleField(JTextField textField, JFrame parentComponent) {
        try {
            return Double.parseDouble(textField.getText());
        } catch (NumberFormatException e) {
            showError("Invalid input: " + textField.getText(), parentComponent);
            return 0.0;
        }
    }

    public static int parseIntField(JTextField textField, JFrame parentComponent) {
        try {
            return Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            showError("Invalid input: " + textField.getText(), parentComponent);
            return 0;
        }
    }
}
