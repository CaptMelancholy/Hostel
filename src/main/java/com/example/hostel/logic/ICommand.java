package com.example.hostel.logic;

import com.example.hostel.exceptions.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {

    String execute(HttpServletRequest request) throws CommandException;
    // TO-DO: Add exception

}
