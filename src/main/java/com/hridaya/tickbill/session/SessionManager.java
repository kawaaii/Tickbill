package com.hridaya.tickbill.session;

public class SessionManager {
    private static SessionManager instance;
    private int userId;
    private String username;
    private userRoleEnum userRole;
    private String fullname;
    private userStatusEnum userStatus;

    private SessionManager() {
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

    public userStatusEnum getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(userStatusEnum userStatus) {
        this.userStatus = userStatus;
    }

    public String getFullName() {
        return fullname;
    }

    public void setFullName(String fullname) {
        this.fullname = fullname;
    }

    public enum userRoleEnum {
        ADMIN,
        EMPLOYEE,
    }

    public enum userStatusEnum {
        VERIFIED,
        UNVERIFIED
    }
}
