package com.example.hostel.beans.rooms;

public class Rooms {
    private Long id;
    private Long num;
    private float price;
    private String description;

    private Boolean deleted = false;

    public Rooms() {}

    public Rooms(Long num, float price, String description) {
        this.num = num;
        this.price = price;
        this.description = description;
    }

    public Rooms(Long id, Long num, float price, String description) {
        this.id = id;
        this.num = num;
        this.price = price;
        this.description = description;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
