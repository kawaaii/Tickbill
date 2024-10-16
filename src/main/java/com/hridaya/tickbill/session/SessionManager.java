package com.hridaya.tickbill.session;

public class SessionManager {
    private static SessionManager instance;
    private int userId;

    // Private constructor to prevent instantiation
    private SessionManager() {}

    // Method to get the singleton instance
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    // Getter and Setter for userId
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
