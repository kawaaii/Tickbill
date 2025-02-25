/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.hridaya.tickbill.view;

import com.hridaya.tickbill.database.DbConnection;
import com.hridaya.tickbill.session.SessionManager;

import java.awt.HeadlessException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author hridaya
 */
public class SaleUI extends javax.swing.JPanel {

    /**
     * Creates new form SaleUI
     */
    public SaleUI() {
        initComponents();
        loadProduct();
        loadInvoice();
    }

    private void panelClearAll() {
        customerNameTextField.setText("");
        productNameComboBox.setSelectedIndex(0);
        productQuantityTextField.setText("1");
        productUnitPriceLabel.setText("00.00");
        productTotalPriceLabel.setText("00.00");

        DefaultTableModel dtm = (DefaultTableModel) salesTable.getModel();
        dtm.setRowCount(0);

        totalAmountTextField.setText("0.00");
        paidAmountTextField.setText("0.00");
        balanceTextField.setText("0.00");

        loadInvoice();
        loadProduct();
    }

    private void loadInvoice() {
        String sql = "SELECT invoice_id FROM sales ORDER BY invoice_id DESC LIMIT 1";
        try (Statement st = DbConnection.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(sql);

            // default if no invoice are found
            int currentInvoiceId = 1;
            if (rs.next()) {
                currentInvoiceId = rs.getInt("invoice_id") + 1;
            }
            showInvoiceLabel.setText(String.valueOf(currentInvoiceId));
        } catch (SQLException ex) {
            Utils.showError("Error: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadProduct() {
        try (Statement st = DbConnection.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM products");

            ArrayList<String> productName = new ArrayList<>();

            while (rs.next()) {
                productName.add(rs.getString("product_name"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel<>(productName.toArray());
            productNameComboBox.setModel(model);
            productQuantityTextField.setText("0");
            productNameComboBox.setSelectedIndex(-1);
        } catch (SQLException e) {
            Utils.showError("Error: " + e.getMessage());
        }
        cartTotalPrice();
        dueAmount();
    }

    private void queryProductQuantity() {
        String productName = String.valueOf(productNameComboBox.getSelectedItem());
        int requestedQuantity = Integer.parseInt(productQuantityTextField.getText());

        String querySql = "SELECT product_quantity FROM products WHERE product_name = ?";
        try (PreparedStatement stmt = DbConnection.getConnection().prepareStatement(querySql)) {
            stmt.setString(1, productName);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int productQuantity = rs.getInt("product_quantity");
                    if (requestedQuantity > productQuantity) {
                        requestedQuantity = productQuantity;
                        Utils.showInfo("Quantity exceeds stock. Adjusted to available."
                                + "\nContact admin.");
                        productQuantityTextField.setText(String.valueOf(requestedQuantity));
                    }
                }
            }
        } catch (SQLException e) {
            Utils.showError("Error: " + e.getMessage());
        }
    }

    private void totalPrice() {
        Double productQuantity = Double.valueOf(productQuantityTextField.getText());
        Double productPrice = Double.valueOf(productUnitPriceLabel.getText());

        Double totalPrice = productQuantity * productPrice;
        productTotalPriceLabel.setText(String.format("%.2f", totalPrice));
    }

    private void cartTotalPrice() {
        DefaultTableModel model = (DefaultTableModel) salesTable.getModel();
        double totalPrice = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            Object value = model.getValueAt(i, 4);
            if (value != null) {
                try {
                    totalPrice += Double.parseDouble(value.toString());
                } catch (NumberFormatException e) {
                    System.err.println("Invalid value at row " + i + ": " + value);
                }
            }
        }

        totalAmountTextField.setText(String.format("%.2f", totalPrice));
    }

    private void dueAmount() {
        double totalPrice = Double.parseDouble(totalAmountTextField.getText());
        double paidAmount = Double.parseDouble(paidAmountTextField.getText());

        double dueAmount = totalPrice - paidAmount;
        balanceTextField.setText(String.format("%.2f", dueAmount));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        removeAllFromCartButton = new javax.swing.JButton();
        addToCartButton = new javax.swing.JButton();
        removeFromCartButton = new javax.swing.JButton();
        payAndPrintButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        salesTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        productNameComboBox = new javax.swing.JComboBox<>();
        productQuantityLabel = new javax.swing.JLabel();
        productUnitPriceLabel = new javax.swing.JLabel();
        customerNameLabel = new javax.swing.JLabel();
        productPriceLabel = new javax.swing.JLabel();
        productQuantityTextField = new javax.swing.JTextField();
        customerNameTextField = new javax.swing.JTextField();
        productNameLabel = new javax.swing.JLabel();
        productTotalLabel = new javax.swing.JLabel();
        productTotalPriceLabel = new javax.swing.JLabel();
        showInvoiceLabel = new javax.swing.JLabel();
        invoiceLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        balanceTextField = new javax.swing.JTextField();
        balanceLabel = new javax.swing.JLabel();
        totalAmountTextField = new javax.swing.JTextField();
        totalAmountLabel = new javax.swing.JLabel();
        paidAmountLabel = new javax.swing.JLabel();
        paidAmountTextField = new javax.swing.JTextField();

        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(1300, 680));
        setMinimumSize(new java.awt.Dimension(1300, 680));
        setPreferredSize(new java.awt.Dimension(1300, 680));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        removeAllFromCartButton.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        removeAllFromCartButton.setText("Remove All");
        removeAllFromCartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllFromCartButtonActionPerformed(evt);
            }
        });

        addToCartButton.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        addToCartButton.setText("Add to Cart");
        addToCartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartButtonActionPerformed(evt);
            }
        });

        removeFromCartButton.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        removeFromCartButton.setText("Remove");
        removeFromCartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFromCartButtonActionPerformed(evt);
            }
        });

        payAndPrintButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        payAndPrintButton.setText("Pay & Print");
        payAndPrintButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payAndPrintButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(removeAllFromCartButton, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(removeFromCartButton, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(addToCartButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(payAndPrintButton, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addToCartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(removeFromCartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(removeAllFromCartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(payAndPrintButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        salesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice ID", "Name", "Quantity", "Unit Price", "Total Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(salesTable);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        productNameComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        productNameComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        productNameComboBox.setSelectedIndex(-1);
        productNameComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameComboBoxActionPerformed(evt);
            }
        });

        productQuantityLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        productQuantityLabel.setText("Quantity:");

        productUnitPriceLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        productUnitPriceLabel.setText("00.00");

        customerNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        customerNameLabel.setText("Customer:");

        productPriceLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        productPriceLabel.setText("Unit Price:");

        productQuantityTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        productQuantityTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                productQuantityTextFieldKeyReleased(evt);
            }
        });

        customerNameTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        productNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        productNameLabel.setText("Product:");

        productTotalLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        productTotalLabel.setText("Total Price:");

        productTotalPriceLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        productTotalPriceLabel.setText("00.00");

        showInvoiceLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        showInvoiceLabel.setText("00");

        invoiceLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        invoiceLabel.setText("INVOICE NO.");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(customerNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(customerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(productNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(productQuantityLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(productQuantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(productPriceLabel)
                        .addGap(18, 18, 18)
                        .addComponent(productUnitPriceLabel)
                        .addGap(36, 36, 36)
                        .addComponent(productTotalLabel)
                        .addGap(18, 18, 18)
                        .addComponent(productTotalPriceLabel))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(invoiceLabel)
                        .addGap(18, 18, 18)
                        .addComponent(showInvoiceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(513, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(invoiceLabel)
                    .addComponent(showInvoiceLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerNameLabel)
                    .addComponent(customerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productNameLabel)
                    .addComponent(productNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productQuantityLabel)
                    .addComponent(productQuantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productPriceLabel)
                    .addComponent(productUnitPriceLabel)
                    .addComponent(productTotalLabel)
                    .addComponent(productTotalPriceLabel))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        balanceTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        balanceTextField.setEnabled(false);

        balanceLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        balanceLabel.setText("Balance / Due:");

        totalAmountTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totalAmountTextField.setEnabled(false);

        totalAmountLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        totalAmountLabel.setText("Total Amount:");

        paidAmountLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        paidAmountLabel.setText("Paid Amount:");

        paidAmountTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        paidAmountTextField.setText("0.00");
        paidAmountTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paidAmountTextFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(totalAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(totalAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(balanceLabel)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(paidAmountLabel)
                                .addGap(23, 23, 23)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(balanceTextField)
                            .addComponent(paidAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalAmountLabel)
                    .addComponent(totalAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paidAmountLabel)
                    .addComponent(paidAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(balanceLabel)
                    .addComponent(balanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void productNameComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameComboBoxActionPerformed
        String productName;
        if (productNameComboBox.getSelectedItem() == null) {
            productName = "";
        } else {
            productName = productNameComboBox.getSelectedItem().toString();
        }

        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement("SELECT product_rate FROM products WHERE product_name = ?")) {
            ps.setString(1, productName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    productUnitPriceLabel.setText(rs.getString("product_rate"));
                }
            }
            totalPrice();
        } catch (SQLException | NumberFormatException ex) {
            Utils.showError("Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_productNameComboBoxActionPerformed

    private void addToCartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartButtonActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) salesTable.getModel();
        ArrayList<String> cartItems = new ArrayList<>();

        if (productNameComboBox.getSelectedItem() == null) {
            Utils.showError("Select an item first.");
            return;
        }

        if (productQuantityTextField.getText().isEmpty()) {
            Utils.showError("Product quantity cannot be empty.");
            return;
        }

        cartItems.add(showInvoiceLabel.getText());
        cartItems.add(productNameComboBox.getSelectedItem().toString());
        cartItems.add(productQuantityTextField.getText());
        cartItems.add(productUnitPriceLabel.getText());
        cartItems.add(productTotalPriceLabel.getText());

        dtm.addRow(cartItems.toArray());
        cartTotalPrice();
        dueAmount();
    }//GEN-LAST:event_addToCartButtonActionPerformed

    private void productQuantityTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productQuantityTextFieldKeyReleased
        queryProductQuantity();
        totalPrice();
    }//GEN-LAST:event_productQuantityTextFieldKeyReleased

    private void removeFromCartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFromCartButtonActionPerformed
        int removeRow = salesTable.getSelectedRow();
        if (removeRow != -1) {
            try {
                DefaultTableModel dtm = (DefaultTableModel) salesTable.getModel();
                dtm.removeRow(removeRow);
            } catch (Exception ex) {
                Utils.showError("Error: " + ex.getMessage());
            }
        } else {
            Utils.showInfo("No item selected");
        }
        cartTotalPrice();
        dueAmount();
    }//GEN-LAST:event_removeFromCartButtonActionPerformed

    private void removeAllFromCartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllFromCartButtonActionPerformed
        int response = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to remove all items from the cart?",
                "Remove all items", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            DefaultTableModel dtm = (DefaultTableModel) salesTable.getModel();
            dtm.setRowCount(0);
            cartTotalPrice();
            dueAmount();
        }
    }//GEN-LAST:event_removeAllFromCartButtonActionPerformed

    private void paidAmountTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paidAmountTextFieldKeyReleased
        dueAmount();
    }//GEN-LAST:event_paidAmountTextFieldKeyReleased

    private void payAndPrintButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payAndPrintButtonActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) salesTable.getModel();
        int rowCount = dtm.getRowCount();

        if (rowCount == 0) {
            Utils.showError("No products in the cart.");
            return;
        }

        // rt inventory qty changes
        try {
            String selectSql = "SELECT product_quantity FROM products WHERE product_name = ?";
            String updateSql = "UPDATE products SET product_quantity = ? WHERE product_name = ?";

            try (PreparedStatement pstSelect = DbConnection.getConnection().prepareStatement(selectSql); PreparedStatement pstUpdate = DbConnection.getConnection().prepareStatement(updateSql)) {

                for (int i = 0; i < rowCount; i++) {
                    String productName = (String) dtm.getValueAt(i, 1);
                    String productQuantityStr = (String) dtm.getValueAt(i, 2);
                    int requestedQuantity = Integer.parseInt(productQuantityStr); // requested qty

                    pstSelect.setString(1, productName);
                    try (ResultSet rs = pstSelect.executeQuery()) {
                        if (rs.next()) {
                            int currentQuantity = rs.getInt("product_quantity"); // inventory qty
                            int finalQuantity = currentQuantity - requestedQuantity;

                            pstUpdate.setInt(1, finalQuantity);
                            pstUpdate.setString(2, productName);
                            pstUpdate.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Utils.showError("Error updating inventory: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            Utils.showError("Error converting quantity to number: " + ex.getMessage());
        }

        // Sales DB
        try {
            // Retrieve and validate input data
            String customerName = customerNameTextField.getText();
            int userId = SessionManager.getInstance().getUserId();
            if (customerName.isEmpty()) {
                Utils.showInfo("Customer name is required");
                return;
            }

            int invoiceId = Integer.parseInt(showInvoiceLabel.getText().trim());
            double totalProductPrice = Double.parseDouble(totalAmountTextField.getText().trim());
            double dueAmount = Double.parseDouble(balanceTextField.getText().trim());
            double paidAmount = Double.parseDouble(paidAmountTextField.getText().trim());

            // Determine payment status
            String status;
            if (dueAmount == 0.0 || dueAmount < 0) {
                status = "Paid";
            } else if (paidAmount == 0.0) {
                status = "Pending";
            } else if (dueAmount > paidAmount) {
                status = "Due";
            } else {
                status = "Partial";
            }

            // Insert sales data into the database
            String salesSql = "INSERT INTO sales (invoice_id, user_id, customer_name, total_bill, status, due) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pst = DbConnection.getConnection().prepareStatement(salesSql)) {
                pst.setInt(1, invoiceId);
                pst.setInt(2, userId);
                pst.setString(3, customerName);
                pst.setDouble(4, totalProductPrice);
                pst.setString(5, status);
                pst.setDouble(6, dueAmount);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data has been saved.");
            }

        } catch (NumberFormatException nfe) {
            Utils.showError("Invalid number format:" + nfe.getMessage());
        } catch (SQLException sqlEx) {
            Utils.showError("Database error: " + sqlEx.getMessage());
        } catch (HeadlessException ex) {
            Utils.showError("Error: " + ex.getMessage());
        }

        // Save Sales History, this is for generating invoices on pay & print
        try {
            int invoiceId = Integer.parseInt(showInvoiceLabel.getText());
            String customerName = customerNameTextField.getText();
            int userId = SessionManager.getInstance().getUserId();

            String salesHistorySql = "INSERT INTO sales_history (SN, invoice_id, user_id, customer_name, product_name, "
                    + "product_rate, product_quantity, product_price, total_bill) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            int serialNumber = 1;
            try (PreparedStatement pst = DbConnection.getConnection().prepareStatement(salesHistorySql)) {
                for (int i = 0; i < rowCount; i++) {
                    String productName = dtm.getValueAt(i, 1).toString();
                    String productQuantity = dtm.getValueAt(i, 2).toString();
                    String productUnitPrice = dtm.getValueAt(i, 3).toString();
                    String productTotalPrice = dtm.getValueAt(i, 4).toString();
                    String totalBill = totalAmountTextField.getText();

                    pst.setInt(1, serialNumber);
                    pst.setInt(2, invoiceId);
                    pst.setInt(3, userId);
                    pst.setString(4, customerName);
                    pst.setString(5, productName);
                    pst.setString(6, productUnitPrice);
                    pst.setString(7, productQuantity);
                    pst.setString(8, productTotalPrice);
                    pst.setString(9, totalBill);

                    pst.addBatch();
                    serialNumber++;
                }
                pst.executeBatch();
            }
        } catch (SQLException ex) {
            Utils.showError("Database error: " + ex.getMessage());
        }

        // generate invoice
        try {
            HashMap<String, Object> param = new HashMap<>();
            param.put("invoiceId", showInvoiceLabel.getText());
            ReportView reportView = new ReportView("src/main/java/com/hridaya/tickbill/report/SaleInvoice.jasper", param);
            reportView.setVisible(true);
        } catch (Exception ex) {
            Utils.showError(ex.getMessage());
        }

        panelClearAll();
    }//GEN-LAST:event_payAndPrintButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToCartButton;
    private javax.swing.JLabel balanceLabel;
    private javax.swing.JTextField balanceTextField;
    private javax.swing.JLabel customerNameLabel;
    private javax.swing.JTextField customerNameTextField;
    private javax.swing.JLabel invoiceLabel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel paidAmountLabel;
    private javax.swing.JTextField paidAmountTextField;
    private javax.swing.JButton payAndPrintButton;
    private javax.swing.JComboBox<String> productNameComboBox;
    private javax.swing.JLabel productNameLabel;
    private javax.swing.JLabel productPriceLabel;
    private javax.swing.JLabel productQuantityLabel;
    private javax.swing.JTextField productQuantityTextField;
    private javax.swing.JLabel productTotalLabel;
    private javax.swing.JLabel productTotalPriceLabel;
    private javax.swing.JLabel productUnitPriceLabel;
    private javax.swing.JButton removeAllFromCartButton;
    private javax.swing.JButton removeFromCartButton;
    private javax.swing.JTable salesTable;
    private javax.swing.JLabel showInvoiceLabel;
    private javax.swing.JLabel totalAmountLabel;
    private javax.swing.JTextField totalAmountTextField;
    // End of variables declaration//GEN-END:variables
}
