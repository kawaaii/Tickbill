package com.hridaya.tickbill.database;

import com.hridaya.tickbill.view.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DbConnection {

    private static final String user = "root";
    private static final String password = "root";
    private static final String host = "localhost";
    private static final String databaseName = "pos";
    private static final String databaseType = "mysql";
    private static String serverUrl;
    private static String jdbcDatabaseUrl;
    private static Connection conn;

    private DbConnection() {
    }

    public static void setPort(int port) {
        serverUrl = "jdbc:" + databaseType + "://" + host + ":" + port;
        jdbcDatabaseUrl = "jdbc:" + databaseType + "://" + host + ":" + port + "/" + databaseName;
    }

    public static synchronized Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(jdbcDatabaseUrl, user, password);
            } catch (ClassNotFoundException | SQLException ex) {
                System.err.println("Failed to create a database connection: " + ex.getMessage());
            }
        }
        return conn;
    }

    public static boolean getInitialConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection initialConn = DriverManager.getConnection(serverUrl, user, password);

            String queryDb = "SELECT SCHEMA_NAME FROM information_schema.SCHEMATA WHERE SCHEMA_NAME = ?";
            try (PreparedStatement stmt = initialConn.prepareStatement(queryDb)) {
                stmt.setString(1, databaseName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return true;
                    } else {
                        Utils.showInfo("""
                                Creating new database, since no database is found.
                                Login again.""");
                        createDatabase(initialConn);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Failed to create a database connection: " + ex.getMessage());
        }
        return false;
    }

    private static void createDatabase(Connection conn) {
        String filePath = "src/main/java/com/hridaya/tickbill/database/pos.sql";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
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
                    }
                    query = new StringBuilder();
                }
            }
        } catch (IOException | SQLException e) {
            Utils.showError(e.getMessage());
        }
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
