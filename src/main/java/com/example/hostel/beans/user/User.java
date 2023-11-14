package com.example.hostel.beans.user;

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

    public User(Boolean adminRole, String login, String password, String userName, String userSurname, String email) {
        this.adminRole = adminRole;
        this.login = login;
        this.password = password;
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(Boolean adminRole) {
        this.adminRole = adminRole;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Boolean getBanStatus() {
        return banStatus;
    }

    public void setBanStatus(Boolean banStatus) {
        this.banStatus = banStatus;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
