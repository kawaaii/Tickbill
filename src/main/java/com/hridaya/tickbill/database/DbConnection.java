package com.hridaya.tickbill.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbConnection {
    private Connection conn;
    private static DbConnection instance;

    private DbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/pos", "root", "root");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    public static void main(String[] args) {
        DbConnection dbConnection = DbConnection.getInstance();
        System.out.println("Connection established: " + (dbConnection.getConnection() != null));
    }
}
