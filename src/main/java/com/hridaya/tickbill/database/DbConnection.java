package com.hridaya.tickbill.database;

import com.hridaya.tickbill.view.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.*;

public class DbConnection {

    private static final String USER = "root";
    private static final String HOST = "localhost";
    private static final String DATABASE_NAME = "pos";
    private static final String DATABASE_TYPE = "mysql";
    private static String password = "root";
    private static String serverUrl;
    private static String jdbcDatabaseUrl;
    private static Connection conn;

    public static void setPort(int port) {
        serverUrl = "jdbc:" + DATABASE_TYPE + "://" + HOST + ":" + port;
        jdbcDatabaseUrl = serverUrl + "/" + DATABASE_NAME;
    }

    public static synchronized Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(jdbcDatabaseUrl, USER, password);
            } catch (ClassNotFoundException | SQLException ex) {
                System.err.println("Failed to create a database connection: " + ex.getMessage());
            }
        }
        return conn;
    }

    public static void getInitialConnection() {
        if (System.getProperty("os.name").startsWith("Mac")) {
            password = "";
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection initialConn = DriverManager.getConnection(serverUrl, USER, password);

            String queryDb = "SELECT SCHEMA_NAME FROM information_schema.SCHEMATA WHERE SCHEMA_NAME = ?";
            try (PreparedStatement stmt = initialConn.prepareStatement(queryDb)) {
                stmt.setString(1, DATABASE_NAME);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (!rs.next()) {
                        Utils.showInfo("No database found. Creating new database.");
                        createDatabase(initialConn);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Failed to create a database connection: " + ex.getMessage());
        }
    }

    private static void createDatabase(Connection conn) {
        String filePath = "src/main/java/com/hridaya/tickbill/database/pos.sql";
        File sqlFile = new File(filePath);

        if (!sqlFile.exists()) {
            Utils.showError("SQL file for database creation not found: " + filePath);
            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(sqlFile))) {
            StringBuilder query = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().startsWith("--")) {
                    continue;
                }
                query.append(line).append(" ");
                if (line.trim().endsWith(";")) {
                    try (Statement stmt = conn.createStatement()) {
                        stmt.execute(query.toString().trim());
                        query.setLength(0);
                    }
                }
            }
        } catch (IOException e) {
            Utils.showError("Error reading SQL file: " + e.getMessage());
        } catch (SQLException e) {
            Utils.showError("Error executing SQL: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                System.out.println("Database connection closed successfully.");
            } catch (SQLException ex) {
                System.err.println("Failed to close the database connection: " + ex.getMessage());
            }
        }
    }
}
