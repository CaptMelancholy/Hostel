package com.example.hostel.logic.commands;

import com.example.hostel.beans.password.Password;
import com.example.hostel.beans.user.User;
import com.example.hostel.controller.JspPageName;
import com.example.hostel.dao.UserDAO;
import com.example.hostel.dao.impl.JdbcUserDAO;
import com.example.hostel.exceptions.CommandException;
import com.example.hostel.exceptions.DaoException;
import com.example.hostel.logic.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class RegistrationPageCommand implements ICommand {
    private final UserDAO userDAO = JdbcUserDAO.getInstance();
    private final Password hash = Password.getInstance();
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        if(!request.getMethod().equals("GET")) {
            request.setAttribute("message", registration(login, hash.hashPassword(password), name, surname, email, request));
        }
        return JspPageName.REG_PAGE;
    }

    private Map<String, String> registration(String login, String password, String name, String surname, String email, HttpServletRequest request) throws CommandException {
        User user = new User(false, login, password, name, surname, email);
        try {
            return userDAO.addUser(user, request.getSession());
        } catch (DaoException e) {
            throw new CommandException(e.getMessage());
        }
    }

}
