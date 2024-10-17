package com.hridaya.tickbill.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection conn;

    private DbConnection() {
    }

    public static synchronized Connection getConnection() {
        if (conn != null) {
            return conn;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/pos", "root", "root");
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Failed to create a database connection: " + ex.getMessage());
        }

        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
                System.err.println("Failed to close the database connection: " + ex.getMessage());
            }
        }
    }
}
