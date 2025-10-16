package com.src.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.src.model.Charity;
import com.src.service.CharityServiceImpl;
import com.src.service.CharityServiceInterface;

@WebServlet("/loginCharity")
public class loginCharity extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email").trim();
        String password = request.getParameter("password");

        CharityServiceInterface csi = new CharityServiceImpl();
        Charity charity = csi.getCharityByEmail(email);

        if (charity != null) {
            // Check account lock
            if (charity.isLocked()) {
                response.sendRedirect("CharityLogin.jsp?msg=Account+locked.+Try+later");
                return;
            }

            // Plain-text comparison
            if (charity.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("charityEmail", charity.getEmail());
                session.setAttribute("charityId", charity.getCharityId());

                response.sendRedirect("charityDashboard.jsp");
            } else {
                response.sendRedirect("CharityLogin.jsp?msg=Invalid+email+or+password");
            }
        } else {
            response.sendRedirect("CharityLogin.jsp?msg=Invalid+email+or+password");
        }
    }
}
