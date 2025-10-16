package com.src.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.model.Charity;
import com.src.service.CharityServiceImpl;
import com.src.service.CharityServiceInterface;

@WebServlet("/addCharity")
public class addCharity extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String regNo = request.getParameter("regno");
        String contact = request.getParameter("contact");
        String email = request.getParameter("email");
        String location = request.getParameter("location");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // ✅ Server-side regex validation
        if (!Pattern.matches("^[A-Za-z\\s]+$", name)) {
            request.setAttribute("errorMessage", "Name should contain only letters and spaces.");
            request.getRequestDispatcher("addCharity.jsp").forward(request, response);
            return;
        }

        if (!Pattern.matches("^[0-9]{5}$", regNo)) {
            request.setAttribute("errorMessage", "Registration number must contain exactly 5 digits.");
            request.getRequestDispatcher("addCharity.jsp").forward(request, response);
            return;
        }

        if (!Pattern.matches("^[0-9]{10}$", contact)) {
            request.setAttribute("errorMessage", "Contact number must be exactly 10 digits.");
            request.getRequestDispatcher("addCharity.jsp").forward(request, response);
            return;
        }

        if (!Pattern.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", email)) {
            request.setAttribute("errorMessage", "Invalid email format.");
            request.getRequestDispatcher("addCharity.jsp").forward(request, response);
            return;
        }

        if (location == null || location.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Location cannot be empty.");
            request.getRequestDispatcher("addCharity.jsp").forward(request, response);
            return;
        }

        if (!Pattern.matches("^(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$", password)) {
            request.setAttribute("errorMessage", "Password must be at least 8 characters long and contain one special character.");
            request.getRequestDispatcher("addCharity.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match!");
            request.getRequestDispatcher("addCharity.jsp").forward(request, response);
            return;
        }

        // ✅ Proceed if all validations pass
        Charity charity = new Charity();
        charity.setName(name);
        charity.setRegNo(regNo);
        charity.setContact(contact);
        charity.setEmail(email);
        charity.setLocation(location);
        charity.setPassword(password);

        CharityServiceInterface service = new CharityServiceImpl();

        try {
            boolean isAdded = service.addCharity(charity);
            if (isAdded) {
                request.setAttribute("successMessage", "Registration successful! Please log in.");
                request.getRequestDispatcher("CharityLogin.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.getRequestDispatcher("addCharity.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Unexpected error: " + e.getMessage());
            request.getRequestDispatcher("addCharity.jsp").forward(request, response);
        }
    }
}
