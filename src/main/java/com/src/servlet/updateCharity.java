package com.src.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.model.Charity;
import com.src.service.CharityServiceImpl;
import com.src.service.CharityServiceInterface;

@WebServlet("/updateCharity")
public class updateCharity extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public updateCharity() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1️⃣ Get form data
        int id = Integer.parseInt(request.getParameter("charityId"));
        String name = request.getParameter("name");
        String regno = request.getParameter("regno");
        String contact = request.getParameter("contact");
        String description = request.getParameter("description");
        String email = request.getParameter("email");
        String location = request.getParameter("location");

      


        // 2️⃣ Create Charity object
        Charity charity = new Charity();
        charity.setCharityId(id);
        charity.setName(name);
        charity.setRegNo(regno);
        charity.setContact(contact);
        charity.setEmail(email);
        charity.setLocation(location);
        

        // 3️⃣ Call service layer
        CharityServiceInterface service = new CharityServiceImpl();
        boolean updated = service.updateCharity(charity);

        // 4️⃣ Redirect or show error
        if (updated) {
            response.sendRedirect(request.getContextPath() + "/Charitymain.jsp");
        } else {
            response.getWriter().println("<h3>❌ Failed to update charity. Please try again.</h3>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Optional: forward GET requests to JSP if needed
        response.sendRedirect(request.getContextPath() + "/updateCharity.jsp?id=" + request.getParameter("id"));
    }
}
