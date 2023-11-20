package com.example.hostel.logic;

import com.example.hostel.exceptions.CommandException;
import com.example.hostel.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * The ICommand interface provides a method for executing a command.
 */
public interface ICommand {
    /**
     * Executes a command
     *
     * @param request the HttpServletRequest object
     * @return the result of executing the command
     * @throws CommandException if there is an error executing the command
     * @throws DaoException if there is an error accessing the data layer
     * @throws ParseException if there is an error parsing data
     */
    String execute(HttpServletRequest request) throws CommandException, DaoException, ParseException;

}
