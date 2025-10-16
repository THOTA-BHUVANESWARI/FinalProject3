package com.src.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.service.DonorServiceImpl;
import com.src.service.DonorServiceInterface;


public class DonorRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DonorServiceInterface dsi=new DonorServiceImpl();
    

    public DonorRegistrationServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
     
        response.getWriter().println("Name: " + name + ", Email: " + email );
    }
}
