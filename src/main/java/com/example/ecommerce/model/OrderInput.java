package com.example.ecommerce.model;

import java.util.List;

public class OrderInput {

    private String fullName;
    private String address;
    private String contactNumber;
    private String email;
    private List<CountProductQuantity> countProductQuantityList;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<CountProductQuantity> getCountProductQuantityList() {
        return countProductQuantityList;
    }

    public void setCountProductQuantityList(List<CountProductQuantity> countProductQuantityList) {
        this.countProductQuantityList = countProductQuantityList;
    }
}
