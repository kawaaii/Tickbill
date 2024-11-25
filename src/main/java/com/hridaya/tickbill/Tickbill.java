/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.hridaya.tickbill;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.hridaya.tickbill.database.DbConnection;
import com.hridaya.tickbill.view.LoginUI;

import javax.swing.*;

/**
 * @author hridaya
 */
public class Tickbill {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
            UIManager.put("TextComponent.arc", 10);
            UIManager.put("Component.focusWidth", 2);
            UIManager.put("Component.arrowType", "chevron");
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        DbConnection.setPort(3306); // keep the default one
        DbConnection.getInitialConnection();
        SwingUtilities.invokeLater(() -> {
            LoginUI loginUI = new LoginUI();
            loginUI.setVisible(true);
        });
    }
}
