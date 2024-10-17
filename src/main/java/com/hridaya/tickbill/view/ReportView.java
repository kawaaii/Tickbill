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

    public ReportView(String fileName) {
        this(fileName, null);
    }

    public ReportView(String fileName, HashMap params) {
        super("Tickbill Invoice Generator");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        try {
            JasperPrint print = JasperFillManager.fillReport(fileName, params, DbConnection.getConnection());
            JRViewer viewer = new JRViewer(print);
            Container container = getContentPane();
            container.add(viewer);
        } catch (JRException e) {
            System.out.println(e.getMessage());
        }
    }

    void print() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
