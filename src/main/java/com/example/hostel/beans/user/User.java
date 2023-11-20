package com.example.hostel.beans.user;

/**
 * The User class represents a user in the system.
 */
public class User {
    private Long id;
    private Boolean adminRole = false;
    private String login;
    private String password;
    private String email;
    private float discount = 0;
    private Boolean banStatus = false;
    private String userName = "John";
    private String userSurname = "Doe";

    /**
     * Constructs a new User object with the given parameters.
     * @param adminRole the admin role of the user
     * @param login the login of the user
     * @param password the password of the user
     * @param userName the user's first name
     * @param userSurname the user's last name
     * @param email the email of the user
     */
    public User(Boolean adminRole, String login, String password, String userName, String userSurname, String email) {
        this.adminRole = adminRole;
        this.login = login;
        this.password = password;
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
    }

    /**
     * Constructs a new empty User object.
     */
    public User() {

    }

    /**
     * Returns the ID of the user.
     * @return the ID of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     * @param id the ID to be set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the login of the user.
     * @return the login of the user
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login of the user.
     * @param login the login to be set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Returns the password of the user.
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * @param password the password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the admin role of the user.
     * @return the admin role of the user
     */
    public Boolean getAdminRole() {
        return adminRole;
    }

    /**
     * Sets the admin role of the user.
     * @param adminRole the admin role to be set
     */
    public void setAdminRole(Boolean adminRole) {
        this.adminRole = adminRole;
    }

    /**
     * Returns the discount of the user.
     * @return the discount of the user
     */
    public float getDiscount() {
        return discount;
    }

    /**
     * Sets the discount of the user.
     * @param discount the discount to be set
     */
    public void setDiscount(float discount) {
        this.discount = discount;
    }

    /**
     * Returns the ban status of the user.
     * @return the ban status of the user
     */
    public Boolean getBanStatus() {
        return banStatus;
    }

    /**
     * Sets the ban status of the user.
     * @param banStatus the ban status to be set
     */
    public void setBanStatus(Boolean banStatus) {
        this.banStatus = banStatus;
    }

    /**
     * Returns the first name of the user.
     * @return the first name of the user
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the first name of the user.
     * @param userName the first name to be set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the last name of the user.
     * @return the last name of the user
     */
    public String getUserSurname() {
        return userSurname;
    }

    /**
     * Sets the last name of the user.
     * @param userSurname the last name to be set
     */
    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    /**
     * Returns the email of the user.
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * @param email the email to be set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
