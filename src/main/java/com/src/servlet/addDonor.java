package com.src.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.model.Donor;
import com.src.service.DonorServiceImpl;

@WebServlet("/addDonor")
public class addDonor extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DonorServiceImpl donorService = new DonorServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form values
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");

        Donor donor = new Donor();
        donor.setDonorName(name);
        donor.setEmail(email);
        donor.setPhone(phone);
        donor.setAddress(address);
        donor.setPassword(password);
        donor.setConfirmPassword(confirmPassword);

        boolean added = donorService.addDonor(donor);

        if (added) {
            // Forward success message
            request.setAttribute("msg", "Registration successful! Please login below.");
            request.setAttribute("status", "success");
        } else {
            // Forward failure message
            request.setAttribute("msg", "Registration failed! Not registered.");
            request.setAttribute("status", "error");
        }

        // Forward back to the same JSP (not redirect)
        request.getRequestDispatcher("Donormain.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Just forward to registration page
        request.getRequestDispatcher("Donormainr.jsp").forward(request, response);
    }
}
