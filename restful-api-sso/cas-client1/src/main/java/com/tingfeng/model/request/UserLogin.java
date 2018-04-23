package com.tingfeng.model.request;

public class UserLogin {

    private String username;
    private String password;
    private String service;

    public UserLogin() {
    }

    public UserLogin(String username, String password, String service) {
        this.username = username;
        this.password = password;
        this.service = service;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "UserLoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", service='" + service + '\'' +
                '}';
    }
}
