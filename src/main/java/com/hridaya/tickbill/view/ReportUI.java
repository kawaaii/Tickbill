/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.hridaya.tickbill.view;

import com.hridaya.tickbill.database.DbConnection;
import raven.datetime.component.date.DatePicker;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author hridaya
 */
public class ReportUI extends javax.swing.JPanel {

    /**
     * Creates new form ReportUI
     */
    public ReportUI() {
        initComponents();

        loadCurrentDate();
        setupData();
    }

    private void calculateReport() {
        DefaultTableModel dtm = (DefaultTableModel) reportTable.getModel();
        int rowCounts = dtm.getRowCount();
        float totalAmount = 0;
        float totalRevenue = 0;
        float dueAmount = 0;
        for (int i = 0; i < rowCounts; i++) {
            totalAmount += Float.parseFloat(String.valueOf(dtm.getValueAt(i, 2)));
            totalRevenue += Float.parseFloat(String.valueOf(dtm.getValueAt(i, 3)));
            dueAmount += Float.parseFloat(String.valueOf(dtm.getValueAt(i, 4)));
        }

        totalRevenueTextField.setText(String.valueOf(totalRevenue));
        float profitLossAmount = 0;
        if (profitLossAmount > totalAmount) {
            profitLossLabel.setText("Profit");
            profitLossAmount = totalRevenue - totalAmount;
        } else {
            profitLossLabel.setText("Loss");
            profitLossAmount = dueAmount;
        }
        profitLossTextField.setText(String.valueOf(profitLossAmount));
        String highestSellingProductSql
                = "SELECT product_name, SUM(product_quantity) AS total_quantity_sold "
                + "FROM sales_history "
                + "GROUP BY product_name "
                + "ORDER BY total_quantity_sold DESC "
                + "LIMIT 1";

        try (Statement st = DbConnection.getConnection().createStatement(); ResultSet rs = st.executeQuery(highestSellingProductSql)) {

            if (rs.next()) {
                String highestSellingProduct = rs.getString("product_name");
                String totalHighestSellingProduct = rs.getString("total_quantity_sold");
                highestSellingProductTextField.setText(highestSellingProduct + " | " + totalHighestSellingProduct);
            } else {
                highestSellingProductTextField.setText("No data available");
            }
        } catch (SQLException e) {
            Utils.showError(e.getMessage());
            highestSellingProductTextField.setText("Error fetching data");
        }
    }

    private void setupData() {
        DefaultTableModel dtm = (DefaultTableModel) reportTable.getModel();
        dtm.setRowCount(0);
        String sql = "SELECT * FROM sales WHERE DATE_FORMAT(created_at, '%Y-%m-%d') BETWEEN ? AND ?";

        LocalDate[] dates = reportDatePicker.getSelectedDateRange();
        String startDate = dates[0].toString();
        String endDate = dates[1].toString();

        try (PreparedStatement pst = DbConnection.getConnection().prepareStatement(sql)) {
            pst.setString(1, startDate);
            pst.setString(2, endDate);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ArrayList<String> reportLoad = new ArrayList<>();
                reportLoad.add(rs.getString("invoice_id"));
                reportLoad.add(rs.getString("customer_name"));

                Double totalAmount = Double.valueOf(rs.getString("total_bill"));
                Double dueAmount = Double.valueOf(rs.getString("due"));
                Double paidAmount = totalAmount - dueAmount;
                if (dueAmount < 0) {
                    dueAmount = 0.00;
                }
                reportLoad.add(String.valueOf(totalAmount));
                reportLoad.add(String.valueOf(paidAmount));
                reportLoad.add(String.valueOf(dueAmount));

                reportLoad.add(rs.getString("status"));
                dtm.addRow(reportLoad.toArray());
            }
            calculateReport();
            reportTable.setModel(dtm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final DatePicker reportDatePicker = new DatePicker();

    private void loadCurrentDate() {
        reportDatePicker.setDateSelectionMode(DatePicker.DateSelectionMode.BETWEEN_DATE_SELECTED);
        reportDatePicker.setSeparator(" - ");
        reportDatePicker.setUsePanelOption(true);
        reportDatePicker.setEditor(dateEditor);
        reportDatePicker.now();
        reportDatePicker.setCloseAfterSelected(true);
        reportDatePicker.setDateFormat("dd-MM-yyyy");
        reportDatePicker.setDateSelectionAble(localDate -> !localDate.isAfter(LocalDate.now()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        profitLossLabel = new javax.swing.JLabel();
        totalRevenueTextField = new javax.swing.JTextField();
        profitLossTextField = new javax.swing.JTextField();
        highestSellingProductTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        dateEditor = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        generateReportButton = new javax.swing.JButton();
        showReportButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1300, 680));
        setMinimumSize(new java.awt.Dimension(1300, 680));
        setPreferredSize(new java.awt.Dimension(1300, 680));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Total revenue:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Highest selling product:");

        profitLossLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        profitLossLabel.setText("Profit/Loss:");

        totalRevenueTextField.setEnabled(false);

        profitLossTextField.setEnabled(false);

        highestSellingProductTextField.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(profitLossLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(profitLossTextField)
                    .addComponent(totalRevenueTextField)
                    .addComponent(highestSellingProductTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(totalRevenueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profitLossLabel)
                    .addComponent(profitLossTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(highestSellingProductTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        reportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SN", "Customer", "Total Amount", "Paid Amount", "Due Amount", "Status"
            }
        ));
        reportTable.setShowGrid(true);
        jScrollPane1.setViewportView(reportTable);

        generateReportButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        generateReportButton.setText("Generate");
        generateReportButton.setMaximumSize(new java.awt.Dimension(90, 30));
        generateReportButton.setMinimumSize(new java.awt.Dimension(90, 30));
        generateReportButton.setPreferredSize(new java.awt.Dimension(90, 30));
        generateReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateReportButtonActionPerformed(evt);
            }
        });

        showReportButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        showReportButton.setText("Show");
        showReportButton.setMaximumSize(new java.awt.Dimension(90, 30));
        showReportButton.setMinimumSize(new java.awt.Dimension(90, 30));
        showReportButton.setPreferredSize(new java.awt.Dimension(90, 30));
        showReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showReportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(showReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(generateReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generateReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Date:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(dateEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dateEditor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 435, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(94, 94, 94)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel3))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void showReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showReportButtonActionPerformed
        setupData();
    }//GEN-LAST:event_showReportButtonActionPerformed

    private void generateReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateReportButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generateReportButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField dateEditor;
    private javax.swing.JButton generateReportButton;
    private javax.swing.JTextField highestSellingProductTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel profitLossLabel;
    private javax.swing.JTextField profitLossTextField;
    private javax.swing.JTable reportTable;
    private javax.swing.JButton showReportButton;
    private javax.swing.JTextField totalRevenueTextField;
    // End of variables declaration//GEN-END:variables
}
