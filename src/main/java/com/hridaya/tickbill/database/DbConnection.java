package com.hridaya.tickbill.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static Connection conn;
    private static final String user = "root";
    private static final String password = "root";
    private static final String host = "localhost";
    private static final int port = 3307;

    private static final String databaseName = "pos";
    private static final String databaseType = "mysql";

    private static final String jdbcDatabaseUrl = "jdbc:" + databaseType + "://" + host + ":" + port + "/" + databaseName;

    private DbConnection() {
    }

    public static synchronized Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcDatabaseUrl, user, password);
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
