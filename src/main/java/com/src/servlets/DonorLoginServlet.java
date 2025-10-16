package com.src.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.src.util.DBConnection; // make sure you have a DB connection class

public class DonorLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DonorLoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.getWriter().append("Donor Login Servlet is running.");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM donors WHERE email=? AND phone=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, phone);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                // Login successful
                HttpSession session = request.getSession();
                session.setAttribute("donorName", rs.getString("name")); // store name in session
                session.setAttribute("donorId", rs.getInt("donor_id"));
                
                // Redirect to donor dashboard or next page
                response.sendRedirect("charityRegistration.jsp");
            } else {
                // Login failed
                response.getWriter().println("<h3>Invalid Email or Phone!</h3>");
            }

            rs.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Something went wrong. Try again later.</h3>");
        }
    }
}
