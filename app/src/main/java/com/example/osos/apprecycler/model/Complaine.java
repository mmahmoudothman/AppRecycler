package com.example.osos.apprecycler.model;

public class Complaine {


    String name;
    String phone;
    String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Complaine(String name, String phone, String desc) {
        this.name = name;
        this.phone = phone;
        this.message = desc;
    }
    public Complaine() {
    }
}
