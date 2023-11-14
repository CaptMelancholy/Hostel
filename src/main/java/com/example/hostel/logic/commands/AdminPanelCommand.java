package com.example.hostel.logic.commands;

import com.example.hostel.beans.user.User;
import com.example.hostel.controller.JspPageName;
import com.example.hostel.dao.UserDAO;
import com.example.hostel.dao.impl.JdbcUserDAO;
import com.example.hostel.exceptions.CommandException;
import com.example.hostel.exceptions.DaoException;
import com.example.hostel.logic.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminPanelCommand implements ICommand {

    private final UserDAO userDAO = JdbcUserDAO.getInstance();
    private static final String USERS_ATTRIBUTE = "users";

    @Override
    public String execute(HttpServletRequest request) throws CommandException, DaoException {
        String login = request.getParameter("login");
        String subCommand = request.getParameter("subCommand");
        if(!request.getMethod().equals("GET")) {
            User user = userDAO.findUserByLogin(login);
            switch (subCommand) {
                case "BAN_USER_COMMAND":
                    request.setAttribute("message", userDAO.setUserBan(user));
                    break;
                case "SET_DISCOUNT_COMMAND":
                    float discount = (float) Math.round(Float.parseFloat(request.getParameter("discount")) * 100) / 100;
                    user.setDiscount(discount);
                    request.setAttribute("message", userDAO.setUserDiscount(user));
                    break;
                case "SET_ADMIN_COMMAND":
                    request.setAttribute("message", userDAO.setUserAdmin(user));
                    break;
            }

        }

        request.setAttribute(USERS_ATTRIBUTE, findUsers());
        return JspPageName.ADMIN_PAGE;
    }

    private List<User> findUsers() throws CommandException {
        try {
           return userDAO.findAllUsers();
        } catch (DaoException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
