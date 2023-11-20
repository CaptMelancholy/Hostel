package com.example.hostel.beans.rooms;

/**
 * The Rooms class represents a room in the system.
 */
public class Rooms {
    private Long id;
    private Long num;
    private float price;
    private String description;

    private Boolean deleted = false;

    /**
     * Default constructor for the Rooms class.
     */
    public Rooms() {}

    /**
     * Constructor for the Rooms class with parameters.
     *
     * @param num        The number of the room.
     * @param price      The price of the room.
     * @param description The description of the room.
     */
    public Rooms(Long num, float price, String description) {
        this.num = num;
        this.price = price;
        this.description = description;
    }

    /**
     * Constructor for the Rooms class with parameters.
     *
     * @param id         The ID of the room.
     * @param num        The number of the room.
     * @param price      The price of the room.
     * @param description The description of the room.
     */
    public Rooms(Long id, Long num, float price, String description) {
        this.id = id;
        this.num = num;
        this.price = price;
        this.description = description;
    }

    /**
     * Get the ID of the room.
     *
     * @return The ID of the room.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the ID of the room.
     *
     * @param id The ID of the room.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the number of the room.
     *
     * @return The number of the room.
     */
    public Long getNum() {
        return num;
    }

    /**
     * Set the number of the room.
     *
     * @param num The number of the room.
     */
    public void setNum(Long num) {
        this.num = num;
    }

    /**
     * Get the price of the room.
     *
     * @return The price of the room.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Set the price of the room.
     *
     * @param price The price of the room.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Get the description of the room.
     *
     * @return The description of the room.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the room.
     *
     * @param description The description of the room.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the deleted status of the room.
     *
     * @return The deleted status of the room.
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * Set the deleted status of the room.
     *
     * @param deleted The deleted status of the room.
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
