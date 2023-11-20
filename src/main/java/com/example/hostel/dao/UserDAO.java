package com.example.hostel.dao;

import com.example.hostel.beans.user.User;
import com.example.hostel.exceptions.DaoException;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The UserDAO interface provides methods for accessing and manipulating user data in the database.
 */
public interface UserDAO {

    /**
     * Finds a user by login and password.
     *
     * @param login    the login of the user
     * @param password the password of the user
     * @return an Optional containing the user, or an empty Optional if no user is found
     * @throws DaoException if there is an error during the DAO method execution
     */
    Optional<User> findUserByLoginAndPass(String login, String password) throws DaoException;

    /**
     * Finds a user by login.
     *
     * @param login the login of the user
     * @return the user with the given login
     * @throws DaoException if there is an error during the DAO method execution
     */
    User findUserByLogin(String login) throws DaoException;

    /**
     * Adds a new user to the database.
     *
     * @param user    the user to be added
     * @param session the HttpSession of the adding operation
     * @return a map of errors
     * @throws DaoException if there is an error during the DAO method execution
     */
    Map<String, String> addUser(User user, HttpSession session) throws DaoException;

    /**
     * Sets the ban status of a user.
     *
     * @param user the user to set the ban status for
     * @throws DaoException if there is an error during the DAO method execution
     */
    void setUserBan(User user) throws DaoException;

    /**
     * Sets the admin role of a user.
     *
     * @param user the user to set the admin role for
     * @throws DaoException if there is an error during the DAO method execution
     */
    void setUserAdmin(User user) throws DaoException;

    /**
     * Sets the discount of a user.
     *
     * @param user the user to set the discount for
     * @throws DaoException if there is an error during the DAO method execution
     */
    void setUserDiscount(User user) throws DaoException;

    /**
     * Finds all users in the database.
     *
     * @return a list of all users
     * @throws DaoException if there is an error during the DAO method execution
     */
    List<User> findAllUsers() throws DaoException;

}
