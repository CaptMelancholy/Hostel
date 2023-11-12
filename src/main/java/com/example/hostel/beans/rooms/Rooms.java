package com.example.hostel.beans.rooms;

public class Rooms {
    private Long id;
    private Long num;
    private String service;
    private float price;
    private Boolean statusBooked;
    private String discription;

    public Rooms() {}

    public Rooms(Long num, String service, float price, Boolean statusBooked, String discription) {
        this.num = num;
        this.service = service;
        this.price = price;
        this.statusBooked = statusBooked;
        this.discription = discription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Boolean getStatusBooked() {
        return statusBooked;
    }

    public void setStatusBooked(Boolean statusBooked) {
        this.statusBooked = statusBooked;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
