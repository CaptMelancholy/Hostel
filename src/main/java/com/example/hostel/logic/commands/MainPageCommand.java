package com.example.hostel.logic.commands;

import com.example.hostel.controller.JspPageName;
import com.example.hostel.exceptions.CommandException;
import com.example.hostel.logic.ICommand;

import javax.servlet.http.HttpServletRequest;

public class MainPageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return JspPageName.MAIN_PAGE;
    }
}
