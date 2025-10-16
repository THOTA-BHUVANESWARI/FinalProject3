package com.src.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DonorForgotPasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/planon", "root", "Bhuv@n@2004");

            // Check if email exists
            PreparedStatement ps = con.prepareStatement("SELECT * FROM donor WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Generate token and expiry
                String token = UUID.randomUUID().toString();
                LocalDateTime expiry = LocalDateTime.now().plusHours(1);

                PreparedStatement psUpdate = con.prepareStatement(
                        "UPDATE donor SET password_reset_token=?, token_expiry=? WHERE email=?");
                psUpdate.setString(1, token);
                psUpdate.setString(2, expiry.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                psUpdate.setString(3, email);
                psUpdate.executeUpdate();

                // Send email (optional: implement JavaMail)
                String resetLink = "http://localhost:9090/project/DonorResetPassword.jsp?token=" + token;
                System.out.println("Reset Link: " + resetLink);

                response.getWriter().println("Reset link sent to your email.");
            } else {
                response.getWriter().println("Email not registered.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
