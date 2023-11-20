package com.example.hostel.beans.orders;

import java.util.Date;

public class Orders {
   /**
 * This class represents an order in the system.
 */
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

    /**
     * Default constructor for the Orders class.
     */
    public Orders() {}

    /**
     * Constructor for the Orders class with parameters.
     *
     * @param roomID     The ID of the room for the order.
     * @param userID     The ID of the user for the order.
     * @param type       The type of the order.
     * @param name       The name of the order.
     * @param surname    The surname of the order.
     * @param phoneNumber The phone number for the order.
     * @param email      The email address for the order.
     * @param date       The date of the order.
     * @param orderPaid  Indicates if the order has been paid.
     * @param dateBegin  The start date of the order.
     * @param dateEnd    The end date of the order.
     */
    public Orders(Long roomID, Long userID, OrderTypes type, String name, String surname, String phoneNumber, String email, Date date, Boolean orderPaid, Date dateBegin, Date dateEnd) {
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

    /**
     * Get the ID of the order.
     *
     * @return The ID of the order.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the ID of the order.
     *
     * @param id The ID of the order.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the ID of the room for the order.
     *
     * @return The ID of the room for the order.
     */
    public Long getRoomID() {
        return roomID;
    }

    /**
     * Set the ID of the room for the order.
     *
     * @param roomID The ID of the room for the order.
     */
    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    /**
     * Get the type of the order.
     *
     * @return The type of the order.
     */
    public OrderTypes getType() {
        return type;
    }

    /**
     * Set the type of the order.
     *
     * @param type The type of the order.
     */
    public void setType(OrderTypes type) {
        this.type = type;
    }

    /**
     * Get the name of the order.
     *
     * @return The name of the order.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the order.
     *
     * @param name The name of the order.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the surname of the order.
     *
     * @return The surname of the order.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set the surname of the order.
     *
     * @param surname The surname of the order.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Get the phone number for the order.
     *
     * @return The phone number for the order.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the phone number for the order.
     *
     * @param phoneNumber The phone number for the order.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the email address for the order.
     *
     * @return The email address for the order.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address for the order.
     *
     * @param email The email address for the order.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the status of the order.
     *
     * @return The status of the order.
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Set the status of the order.
     *
     * @param status The status of the order.
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * Get the start date of the order.
     *
     * @return The start date of the order.
     */
    public Date getDateBegin() {
        return dateBegin;
    }

    /**
     * Set the start date of the order.
     *
     * @param dateBegin The start date of the order.
     */
    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    /**
     * Get the end date of the order.
     *
     * @return The end date of the order.
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * Set the end date of the order.
     *
     * @param dateEnd The end date of the order.
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * Check if the order has been paid.
     *
     * @return True if the order has been paid, false otherwise.
     */
    public Boolean getOrderPaid() {
        return orderPaid;
    }

    /**
     * Set whether the order has been paid or not.
     *
     * @param orderPaid True if the order has been paid, false otherwise.
     */
    public void setOrderPaid(Boolean orderPaid) {
        this.orderPaid = orderPaid;
    }

    /**
     * Get the date of the order.
     *
     * @return The date of the order.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the date of the order.
     *
     * @param date The date of the order.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the ID of the user for the order.
     *
     * @return The ID of the user for the order.
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Set the ID of the user for the order.
     *
     * @param userID The ID of the user for the order.
     */
    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
