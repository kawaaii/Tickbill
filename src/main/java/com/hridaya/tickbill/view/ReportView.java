package com.hridaya.tickbill.view;

import com.hridaya.tickbill.database.DbConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ReportView extends JFrame {

    public ReportView(String fileName, HashMap<String, Object> params) {
        super("Tickbill Invoice Generator");
        this.setSize(800, 1280);
        this.setLocationRelativeTo(null);

        try {
            JasperPrint print = JasperFillManager.fillReport(fileName, params, DbConnection.getConnection());
            if (print != null) {
                JRViewer viewer = new JRViewer(print);
                Container container = getContentPane();
                container.add(viewer);
            }
        } catch (JRException e) {
            Utils.showError(e.getMessage());
        }
    }
}
