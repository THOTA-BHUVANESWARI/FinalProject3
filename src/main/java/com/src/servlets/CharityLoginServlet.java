package com.src.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharityLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CharityLoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.getWriter().append("Charity Login Servlet is running.");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // TODO: Replace with actual DB validation
        if ("charity@example.com".equals(email) && "password123".equals(password)) {
            response.getWriter().println("<h3>Charity Login Successful!</h3>");
        } else {
            response.getWriter().println("<h3>Invalid Email or Password!</h3>");
        }
    }
}
