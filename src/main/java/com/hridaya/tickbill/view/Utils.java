package com.hridaya.tickbill.view;

import javax.swing.*;

public class Utils {
    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showError(String message, Exception e) {
        showError(message + ": " + e.getMessage());
    }

    public static void showInfo(String message) {
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
