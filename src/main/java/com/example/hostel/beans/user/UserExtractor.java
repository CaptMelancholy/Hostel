package com.example.hostel.beans.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserExtractor {

    public List<User> extractData(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id_user"));
            user.setLogin(resultSet.getString("user_login"));
            user.setPassword(resultSet.getString("user_password"));
            user.setUserName(resultSet.getString("user_name"));
            user.setUserSurname(resultSet.getString("user_surname"));
            user.setAdminRole(resultSet.getBoolean("user_role"));
            user.setDiscount(resultSet.getFloat("user_discount"));
            user.setBanStatus(resultSet.getBoolean("user_ban"));
            user.setEmail(resultSet.getString("user_email"));
            users.add(user);
        }
        return users;
    }

    public User extractDataOnce(ResultSet resultSet) throws SQLException {
        User user = new User();
        resultSet.next();
        user.setId(resultSet.getLong("id_user"));
        user.setLogin(resultSet.getString("user_login"));
        user.setPassword(resultSet.getString("user_password"));
        user.setUserName(resultSet.getString("user_name"));
        user.setUserSurname(resultSet.getString("user_surname"));
        user.setAdminRole(resultSet.getBoolean("user_role"));
        user.setDiscount(resultSet.getFloat("user_discount"));
        user.setBanStatus(resultSet.getBoolean("user_ban"));
        user.setEmail(resultSet.getString("user_email"));
        return user;
    }
}