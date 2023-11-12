package com.example.hostel.beans.orders;

import com.example.hostel.beans.rooms.Rooms;

import java.sql.Time;

public class Orders {
    private Long id;
    private Long roomID;
    private Rooms room;
    private OrderStatus type;

    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String dateBegin;
    private String dateEnd;


    private Boolean orderPaid;
    private Time date;

}
