package com.example.hostel.beans.orders;

import com.example.hostel.beans.rooms.Rooms;

import java.sql.Time;
import java.util.Date;

public class Orders {
    private Long id;

    private Long roomID;
    private Long userID;
    private OrderTypes type;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private OrderStatus status = OrderStatus.SENT;
    private Date dateBegin;
    private Date dateEnd;
    private Boolean orderPaid;
    private Date date;

    public Orders() {}

    public Orders(Long roomID, Long userID, OrderTypes type, String name, String surname, String phoneNumber, String email, Date dateBegin, Date dateEnd, Boolean orderPaid, Date date) {
        this.roomID = roomID;
        this.userID = userID;
        this.type = type;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.orderPaid = orderPaid;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    public OrderTypes getType() {
        return type;
    }

    public void setType(OrderTypes type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Boolean getOrderPaid() {
        return orderPaid;
    }

    public void setOrderPaid(Boolean orderPaid) {
        this.orderPaid = orderPaid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
