package com.example.hostel.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The LocalizationFilter class implements the Filter interface to handle localization of requests.
 */
public class LocalizationFilter implements Filter {
    /**
     * Filters the request and response to handle localization.
     *
     * @param servletRequest  the ServletRequest object
     * @param servletResponse the ServletResponse object
     * @param filterChain     the FilterChain object
     * @throws IOException      if there is an I/O exception
     * @throws ServletException if there is a servlet exception
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if(request.getParameter("sessionLocale") != null) {
            request.getSession().setAttribute("lang", request.getParameter("sessionLocale"));
        }
        filterChain.doFilter(request, response);
    }
}
