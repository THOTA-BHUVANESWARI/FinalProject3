package com.src.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.src.model.Donor;
import com.src.service.DonorServiceImpl;
import com.src.service.DonorServiceInterface;

@WebServlet("/loginDonor")
public class loginDonor extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        DonorServiceInterface dsi = new DonorServiceImpl();
        Donor donor = dsi.getDonorByEmail(email);

        if (donor != null && donor.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("donorEmail", donor.getEmail());
            session.setAttribute("donorId", donor.getDonorId());
            response.sendRedirect("Charitymain.jsp");
        } else {
            response.sendRedirect("Donormain.jsp?msg=Invalid+email+or+password");
        }
    }
}
