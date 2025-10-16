package com.src.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DonorResetPasswordServlet")
public class DonorResetPasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            response.sendRedirect("DonorResetPassword.jsp?error=Passwords+do+not+match");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/planon", "root", "Bhuv@n@2004");

            // Check if email exists
            PreparedStatement psCheck = con.prepareStatement("SELECT * FROM donor WHERE email=?");
            psCheck.setString(1, email);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                // Update password
                PreparedStatement psUpdate = con.prepareStatement(
                        "UPDATE donor SET password=? WHERE email=?");
                psUpdate.setString(1, newPassword); // Ideally hash the password
                psUpdate.setString(2, email);
                psUpdate.executeUpdate();

                response.sendRedirect("Donormain.jsp?msg=Password+reset+successful");
            } else {
                response.sendRedirect("DonorResetPassword.jsp?error=Email+not+registered");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("DonorResetPassword.jsp?error=Something+went+wrong");
        }
    }
}
