package com.example.hostel.logic;

import com.example.hostel.exceptions.CommandException;
import com.example.hostel.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {

    String execute(HttpServletRequest request) throws CommandException, DaoException;

}
