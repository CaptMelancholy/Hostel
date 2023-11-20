package com.example.hostel.logic.commands;

import com.example.hostel.beans.user.User;
import com.example.hostel.controller.JspPageName;
import com.example.hostel.dao.UserDAO;
import com.example.hostel.dao.impl.JdbcUserDAO;
import com.example.hostel.exceptions.CommandException;
import com.example.hostel.exceptions.DaoException;
import com.example.hostel.logic.ICommand;

import javax.servlet.http.HttpServletRequest;

public class BanUserCommand implements ICommand {
    private final UserDAO userDAO = JdbcUserDAO.getInstance();
    @Override
    public String execute(HttpServletRequest request) throws CommandException, DaoException {
        String login = request.getParameter("login");

        if(!request.getMethod().equals("GET")) {
            User user = userDAO.findUserByLogin(login);
            userDAO.setUserBan(user);
        }
        return JspPageName.ADMIN_PAGE;
    }

}
