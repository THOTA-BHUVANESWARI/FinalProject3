package com.src.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.model.Donor;
import com.src.service.DonorServiceImpl;
import com.src.service.DonorServiceInterface;

/**
 * Servlet implementation class updateDonor
 */
@WebServlet("/updateDonor")
public class updateDonor extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public updateDonor() {
        super();
    }

    /**
     * Handles GET requests
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form parameters
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");

        // Create and set donor data
        Donor donor = new Donor();
        donor.setDonorId(id);
        donor.setDonorName(name);
        donor.setEmail(email);
        donor.setPhone(mobile);
        donor.setAddress(address);

        // Update donor using service
        DonorServiceInterface service = new DonorServiceImpl();
        boolean isUpdated = service.updateDonor(donor);

        // Redirect or show message
        if (isUpdated) {
            response.sendRedirect("Donormain.jsp"); // redirect after successful update
        } else {
            response.getWriter().println("<h3>Failed to update donor details. Please try again.</h3>");
        }
    }

    /**
     * Handles POST requests (delegates to doGet)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
