package com.hridaya.tickbill.session;

public class SessionManager {
    private static SessionManager instance;
    private int userId;
    private String username;
    private userRoleEnum userRole;

    private SessionManager() {
    }

    public enum userRoleEnum {
        ADMIN,
        EMPLOYEE,
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public userRoleEnum getUserRole() {
        return userRole;
    }

    public void setUserRole(userRoleEnum userRole) {
        this.userRole = userRole;
    }
}
