package com.src.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.model.Donor;
import com.src.service.DonorServiceImpl;
import com.src.service.DonorServiceInterface;

@WebServlet("/deleteDonor")
public class deleteDonor extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public deleteDonor() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int donorId = Integer.parseInt(request.getParameter("donorId"));

            DonorServiceInterface donorService = new DonorServiceImpl();
            donorService.deleteDonor(donorId);

            // After deletion, get updated list and forward to JSP
            List<Donor> donors = donorService.getAllDonors();
            request.setAttribute("donors", donors);
            request.getRequestDispatcher("/deleteDonor.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>❌ Error: " + e.getMessage() + "</h3>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Load donor list on initial page load
        DonorServiceInterface donorService = new DonorServiceImpl();
        List<Donor> donors = donorService.getAllDonors();
        request.setAttribute("donors", donors);
        request.getRequestDispatcher("/deleteDonor.jsp").forward(request, response);
    }
}
