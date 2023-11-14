package com.example.hostel.logic.commands;

import com.example.hostel.controller.JspPageName;
import com.example.hostel.exceptions.CommandException;
import com.example.hostel.logic.ICommand;

import javax.servlet.http.HttpServletRequest;

public class SingOutCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        logout(request);
        return JspPageName.MAIN_PAGE;
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
