/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.hridaya.tickbill;

import com.formdev.flatlaf.FlatLightLaf;
import com.hridaya.tickbill.view.LoginUI;

import javax.swing.*;

/**
 * @author hridaya
 */
public class Tickbill {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        SwingUtilities.invokeLater(() -> {
            LoginUI loginUI = new LoginUI();
            loginUI.setVisible(true);
        });
    }
}
