package com.example.osos.apprecycler.model;

public class RequestModel {

    String name;
    String phone;
    String officeName;
    String type;
    String quantity;


    public RequestModel() {

    }


    public RequestModel(String name, String phone, String officeName, String type, String quantity) {
        this.name = name;
        this.phone = phone;
        this.officeName = officeName;
        this.type = type;
        this.quantity = quantity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
