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

public class AuthorizationPageCommand implements ICommand {

    private static final Password hashPass = Password.getInstance();
    private final UserDAO userDAO = JdbcUserDAO.getInstance();
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if(!request.getMethod().equals("GET")) {
            request.setAttribute("messages", login(request, login, hashPass.hashPassword(password)));
        }
        return JspPageName.MAIN_PAGE;
    }

    private Map<String, String> login(HttpServletRequest request, String login, String password) {
        User user;
        try {
            user = userDAO.findUserByLoginAndPass(login, password).orElse(null);

        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        if(user == null) {

        } else {
            if(user.getBanStatus()) {
                request.getSession().setAttribute("role", "BANNED");
            } else {
                request.getSession().setAttribute("role", user.getAdminRole() ? "admin" : "client" );
            }

            request.getSession().setAttribute("name", user.getUserName() + " " + user.getUserSurname());
            request.getSession().setAttribute("id", user.getId());
            request.getSession().setAttribute("discount", user.getDiscount() == 0.0 ? 0 : user.getDiscount());
        }
        return null;
    }
}
