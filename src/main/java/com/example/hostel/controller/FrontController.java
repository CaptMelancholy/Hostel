package com.example.hostel.controller;

import com.example.hostel.exceptions.CommandException;
import com.example.hostel.exceptions.DaoException;
import com.example.hostel.logic.CommandHelper;
import com.example.hostel.logic.ICommand;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * Name of command parameter
     */
    private static final String COMMAND_NAME = "command";

    /**
     * Simple constructor
     */

    public FrontController() {
        super();
    }

    /**
     * Catch get requests
     *
     * @param request  http request
     * @param response http respouse
     * @throws ServletException
     * @throws IOException
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Catch post requests
     *
     * @param request  http request
     * @param response http response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND_NAME);
        String page;
        if(request.getSession().getAttribute("role") == "BANNED") {
            page = JspPageName.BAN_PAGE;
        } else if (commandName != null) {
            try {
                ICommand command = CommandHelper.getInstance().getCommand(commandName);
                page = command.execute(request);
            } catch (CommandException | DaoException e) {
                request.setAttribute("message", e.getMessage());
                page = JspPageName.ERROR_PAGE;
            }
        } else if (request.getParameter("sessionLocale") != null) {
            try {
                page = CommandHelper.getInstance().getCommand("MAIN_PAGE").execute(request);
            } catch (CommandException | DaoException e) {
                request.setAttribute("message", e.getMessage());
                page = JspPageName.ERROR_PAGE;
            }
        } else {
            page = JspPageName.ERROR_PAGE;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        } else {
            errorMessageDireclyFromresponse(response);
        }
    }


    /**
     * Error response when no jsp to send
     *
     * @param response
     * @throws IOException
     */
    private void errorMessageDireclyFromresponse(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("E R R O R");
    }

}