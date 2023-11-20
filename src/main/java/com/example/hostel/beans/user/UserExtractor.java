package com.example.hostel.beans.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The UserExtractor class is responsible for extracting User objects from a ResultSet.
 */
public class UserExtractor {
    /**
     * Extracts a list of User objects from the given ResultSet.
     * @param resultSet the ResultSet containing user data
     * @return a list of User objects extracted from the ResultSet
     * @throws SQLException if there is an error accessing the ResultSet
     */
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

    /**
     * Extracts a single User object from the given ResultSet.
     * This method assumes that the ResultSet has only one row of data.
     * @param resultSet the ResultSet containing user data
     * @return a User object extracted from the ResultSet
     * @throws SQLException if there is an error accessing the ResultSet
     */
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