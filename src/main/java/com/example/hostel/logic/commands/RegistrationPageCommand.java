package com.example.hostel.logic.commands;

import com.example.hostel.beans.user.User;
import com.example.hostel.controller.JspPageName;
import com.example.hostel.dao.UserDAO;
import com.example.hostel.dao.impl.JdbcUserDAO;
import com.example.hostel.exceptions.CommandException;
import com.example.hostel.exceptions.DaoException;
import com.example.hostel.logic.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class RegistrationPageCommand implements ICommand {
    private UserDAO userDAO = JdbcUserDAO.getInstance();
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        if(!request.getMethod().equals("GET")) {
            request.setAttribute("message", registration(login, hashPassword(password), name, surname, request));
        }
        return JspPageName.REG_PAGE;
    }

    private Map<String, String> registration(String login, String password, String name, String surname, HttpServletRequest request) throws CommandException {
        User user = new User(false, login, password, name, surname);
        try {
            return userDAO.addUser(user, request.getSession());
        } catch (DaoException e) {
            throw new CommandException(e.getMessage());
        }
    }
    private String hashPassword(String password) throws CommandException {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new CommandException(e.getMessage());
        }
        byte[] hashedBytes = md.digest(password.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashedBytes) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }
}
