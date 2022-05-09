package com.example.androidassignment.Model;

public class UserModel {
    private String userName;
    private String userAddress;
    private String userPassword;
    private int id;

    public UserModel(String userName, String userAddress, String userPassword) {
        this.userName = userName;
        this.userAddress = userAddress;
        this.userPassword = userPassword;
    }

    public UserModel(){};

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
