package com.example.hostel.logic.commands;

import com.example.hostel.exceptions.CommandException;
import com.example.hostel.exceptions.DaoException;
import com.example.hostel.logic.ICommand;

import javax.servlet.http.HttpServletRequest;

public class UserOrdersListCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException, DaoException {
        return null;
    }
}
