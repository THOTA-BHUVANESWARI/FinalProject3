package com.src.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.src.model.Campaign;
import com.src.service.CampaignServiceImpl;
import com.src.service.CampaignServiceInterface;

@WebServlet("/addCampaign")
public class addCampaign extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public addCampaign() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession(false);

            // 1️⃣ Get charityId (prefer session value)
            int charityId;
            if (session != null && session.getAttribute("charityId") != null) {
                charityId = (int) session.getAttribute("charityId");
            } else {
                charityId = Integer.parseInt(request.getParameter("charityId"));
            }

            // 2️⃣ Get form data
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
            LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
            String Status = (request.getParameter("status"));

            // 3️⃣ Create campaign object
            Campaign campaign = new Campaign();
            campaign.setCharityId(charityId);
            campaign.setCampaignName(title);
            campaign.setDescription(description);
            campaign.setStartDate(startDate);
            campaign.setEndDate(endDate);
            campaign.setStatus(Status);

            // 4️⃣ Add campaign using service
            CampaignServiceInterface service = new CampaignServiceImpl();
            boolean inserted = service.addCampaign(campaign);

            // 5️⃣ Use Redirect to prevent duplicate on reload
            if (inserted) {
                response.sendRedirect(request.getContextPath() + "/charityDashboard.jsp?msg=success");
            } else {
                response.sendRedirect(request.getContextPath() + "/AddCampaign.jsp?msg=error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
