package com.hridaya.tickbill.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection conn;
    private static DbConnection instance;

    private DbConnection() {
    }

    public static synchronized DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public static Connection getConnection() {
        if (isClosed(conn)) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3307/pos", "root", "root");
            } catch (ClassNotFoundException | SQLException ex) {
                System.err.println("Failed to create a database connection: " + ex.getMessage());
            }
        }
        return conn;
    }

    private static boolean isClosed(Connection conn) {
        try {
            return conn == null || conn.isClosed();
        } catch (SQLException ex) {
            System.err.println("Failed to check connection status: " + ex.getMessage());
            return true;
        }
    }
}
