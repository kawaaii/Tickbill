/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.hridaya.tickbill.view;

import com.hridaya.tickbill.database.DbConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author hridaya
 */
public class UserUI extends javax.swing.JPanel {

    /**
     * Creates new form UserUI
     */
    public UserUI() {
        initComponents();
        userLoad();
        createUserPanel.setBorder(null); // don't add border
    }

    private void userLoad() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) userTable.getModel();
            dtm.setRowCount(0);

            Statement st = DbConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                v.add(rs.getString(8));
                v.add(rs.getString(9));

                dtm.addRow(v);
            }
        } catch (SQLException e) {
            Utils.showError("Error: " + e.getMessage());
        }
    }

    private boolean userCreationValidation(String firstName, String lastName, String address, String emailAddress, String phoneNumber) {
        String nameRegex = "^[A-Za-z]+$";
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        String addressRegex = "^[\\w\\s,.-]+$";
        String phoneNumberRegex = "^\\d{10}$";

        if (!firstName.matches(nameRegex)) {
            Utils.showError("Set valid first name.");
            return false;
        }

        if (!lastName.matches(nameRegex)) {
            Utils.showError("Set valid last name.");
            return false;
        }

        if (!emailAddress.matches(emailRegex)) {
            Utils.showError("Set valid email address.");
            return false;
        }

        if (!address.matches(addressRegex)) {
            Utils.showError("Set valid address.");
            return false;
        }

        if (!phoneNumber.matches(phoneNumberRegex)) {
            Utils.showError("Set valid phone number.");
            return false;
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createUserPanel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        phoneNumberTextField = new javax.swing.JTextField();
        firstNameTextField = new javax.swing.JTextField();
        addressLabel = new javax.swing.JLabel();
        userNameTextField = new javax.swing.JTextField();
        lastNameTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        userIdLabel = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();
        userIdTextField = new javax.swing.JTextField();
        firstNameLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        lastNameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        phoneNumberLabel = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JTextField();
        addressTextField = new javax.swing.JTextField();
        userRoleComboBox = new javax.swing.JComboBox<>();
        userRoleLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        userUpdateButton = new javax.swing.JButton();
        userDeleteButton = new javax.swing.JButton();
        userCreateButton = new javax.swing.JButton();
        userSearchButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(1506, 703));
        setMinimumSize(new java.awt.Dimension(1506, 703));
        setPreferredSize(new java.awt.Dimension(1506, 703));

        createUserPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        createUserPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createUserPanelMouseClicked(evt);
            }
        });

        phoneNumberTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        firstNameTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        addressLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addressLabel.setText("Address:");

        userNameTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lastNameTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        emailLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        emailLabel.setText("Email:");

        userIdLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userIdLabel.setText("User ID:");

        userNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userNameLabel.setText("Username:");

        userIdTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        firstNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        firstNameLabel.setText("First Name:");

        emailTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lastNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lastNameLabel.setText("Last Name:");

        passwordLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        passwordLabel.setText("Password:");

        phoneNumberLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        phoneNumberLabel.setText("Phone No:");

        passwordTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        addressTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        userRoleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Employee" }));
        userRoleComboBox.setSelectedIndex(-1);

        userRoleLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userRoleLabel.setText("Role:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lastNameLabel)
                    .addComponent(emailLabel)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(phoneNumberLabel)
                        .addGap(18, 18, 18)
                        .addComponent(phoneNumberTextField))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(addressLabel)
                                .addGap(34, 34, 34)
                                .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstNameLabel)
                            .addComponent(userIdLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(userNameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordLabel)
                            .addComponent(userRoleLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userRoleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordTextField))))
                .addGap(40, 40, 40))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userIdLabel)
                    .addComponent(userIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel)
                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userNameLabel)
                    .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameLabel)
                    .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLabel)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userRoleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userRoleLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneNumberLabel)
                    .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        userUpdateButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        userUpdateButton.setText("Update");
        userUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userUpdateButtonActionPerformed(evt);
            }
        });

        userDeleteButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        userDeleteButton.setText("Delete");
        userDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userDeleteButtonActionPerformed(evt);
            }
        });

        userCreateButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        userCreateButton.setText("Create");
        userCreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userCreateButtonActionPerformed(evt);
            }
        });

        userSearchButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        userSearchButton.setText("Search");
        userSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userSearchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(userCreateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userCreateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(838, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(276, Short.MAX_VALUE))
        );

        createUserPanel.addTab("Create new user", jPanel1);

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "First Name", "Last Name", "Username", "Password", "Role", "Address", "Email", "Phone No."
            }
        ));
        userTable.setShowGrid(true);
        jScrollPane1.setViewportView(userTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(197, Short.MAX_VALUE))
        );

        createUserPanel.addTab("All users", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(createUserPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(createUserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void userCreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userCreateButtonActionPerformed
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String address = addressTextField.getText();
        String emailAddress = emailTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String username = userNameTextField.getText();
        String password = passwordTextField.getText();
        String role = null;
        if (userRoleComboBox.getSelectedItem() != null) {
            role = userRoleComboBox.getSelectedItem().toString().trim();
        }

        if (userCreationValidation(firstName, lastName, address, emailAddress, phoneNumber)) {
            String sql = "INSERT INTO user "
                    + "(first_name, last_name, username, password, user_role, user_address, user_email, phone_no) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pst = DbConnection.getConnection().prepareStatement(sql)) {
                pst.setString(1, firstName);
                pst.setString(2, lastName);
                pst.setString(3, username);
                pst.setString(4, password);
                pst.setString(5, role);
                pst.setString(6, address);
                pst.setString(7, emailAddress);
                pst.setString(8, phoneNumber);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    Utils.showInfo("User created successfully.");
                } else {
                    Utils.showError("User creation failed.");
                }
            } catch (SQLException sqle) {
                Utils.showError("Error while registering new user" + sqle.getMessage());
            }
        }
    }//GEN-LAST:event_userCreateButtonActionPerformed

    private void userUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userUpdateButtonActionPerformed
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String address = addressTextField.getText();
        String emailAddress = emailTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String username = userNameTextField.getText();
        String password = passwordTextField.getText();
        String userId = userIdTextField.getText();
        String role = null;
        if (userRoleComboBox.getSelectedItem() != null) {
            role = userRoleComboBox.getSelectedItem().toString();
        }

        if (userCreationValidation(firstName, lastName, address, emailAddress, phoneNumber)) {
            String sql = "UPDATE user SET first_name = ?, last_name = ?, username = ?, password = ?"
                    + ", user_role = ?, user_address = ?, user_email = ?, phone_no = ? "
                    + "WHERE user_id = ?";
            try (PreparedStatement pst = DbConnection.getConnection().prepareStatement(sql)) {
                pst.setString(1, firstName);
                pst.setString(2, lastName);
                pst.setString(3, username);
                pst.setString(4, password);
                pst.setString(5, role);
                pst.setString(6, address);
                pst.setString(7, emailAddress);
                pst.setString(8, phoneNumber);
                pst.setString(9, userId);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    Utils.showInfo("User updated successfully.");
                } else {
                    Utils.showError("Failed to update user.");
                }
            } catch (Exception ex) {
                Utils.showError("Error while registering new user" + ex.getMessage());
            }
        }
    }//GEN-LAST:event_userUpdateButtonActionPerformed

    private void userDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userDeleteButtonActionPerformed
        String userId = userIdTextField.getText();
        String sql = "DELETE FROM user WHERE user_id = ?";
        if (userId.isEmpty()) {
            Utils.showError("Enter User ID");
            return;
        }

        try (PreparedStatement pst = DbConnection.getConnection().prepareStatement(sql)) {
            pst.setString(1, userId);
            pst.executeUpdate();
            Utils.showInfo("User deleted successfully.");
        } catch (SQLException ex) {
            Utils.showError("Error while deleting user" + ex.getMessage());
        }

    }//GEN-LAST:event_userDeleteButtonActionPerformed

    private void userSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userSearchButtonActionPerformed
        String userId = userIdTextField.getText();
        String sql = "SELECT * FROM user WHERE user_id = ?";

        try (PreparedStatement pst = DbConnection.getConnection().prepareStatement(sql)) {
            pst.setString(1, userId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("user_role");
                String address = rs.getString("user_address");
                String email = rs.getString("user_email");
                String phoneNumber = rs.getString("phone_no");

                // db has everything in lowerspace, meanwhile combobox have first letter capital
                // so while getting user role assign it to another string which will then capitalize first word
                String userRole = role.substring(0, 1).toUpperCase() + role.substring(1).toLowerCase();

                firstNameTextField.setText(firstName);
                lastNameTextField.setText(lastName);
                userNameTextField.setText(username);
                passwordTextField.setText(password);
                userRoleComboBox.setSelectedItem(userRole);
                addressTextField.setText(address);
                emailTextField.setText(email);
                phoneNumberTextField.setText(phoneNumber);
            } else {
                Utils.showInfo("User not found");
            }
            rs.close();
        } catch (Exception ex) {
            Utils.showError("Error while searching user" + ex.getMessage());
        }
    }//GEN-LAST:event_userSearchButtonActionPerformed

    private void createUserPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createUserPanelMouseClicked
        userLoad();
    }//GEN-LAST:event_createUserPanelMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JTabbedPane createUserPanel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JTextField phoneNumberTextField;
    private javax.swing.JButton userCreateButton;
    private javax.swing.JButton userDeleteButton;
    private javax.swing.JLabel userIdLabel;
    private javax.swing.JTextField userIdTextField;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JTextField userNameTextField;
    private javax.swing.JComboBox<String> userRoleComboBox;
    private javax.swing.JLabel userRoleLabel;
    private javax.swing.JButton userSearchButton;
    private javax.swing.JTable userTable;
    private javax.swing.JButton userUpdateButton;
    // End of variables declaration//GEN-END:variables
}
