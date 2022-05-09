package com.example.androidassignment.Model;

public class AddressModel {
    private int id;
    private String address;
    private String description;
    private byte[] image;


    public AddressModel(String address, String description) {
        this.address = address;
        this.description = description;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
