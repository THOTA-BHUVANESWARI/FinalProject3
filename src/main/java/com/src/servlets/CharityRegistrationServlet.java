package com.src.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharityRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CharityRegistrationServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.getWriter().append("Charity Registration Servlet is running.");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // TODO: Replace with actual DB insertion
        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            response.getWriter().println("<h3>Charity Registered Successfully!</h3>");
        } else {
            response.getWriter().println("<h3>All fields are required!</h3>");
        }
    }
}
