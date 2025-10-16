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

@WebServlet("/updateCampaign")
public class updateCampaign extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public updateCampaign() { super(); }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("charityId") == null) {
            response.sendRedirect("CharityLogin.jsp");
            return;
        }

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
            LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));

            // Validate dates
            if (startDate.isAfter(endDate)) {
                request.setAttribute("error", "Start date cannot be after End date.");
                request.getRequestDispatcher("/updateCampaign.jsp?id=" + id).forward(request, response);
                return;
            }

            int charityId = (Integer) session.getAttribute("charityId");

            Campaign campaign = new Campaign();
            campaign.setCampaignId(id);
            campaign.setCampaignName(title);
            campaign.setDescription(description);
            campaign.setStartDate(startDate);
            campaign.setEndDate(endDate);
            campaign.setCharityId(charityId);

            CampaignServiceInterface service = new CampaignServiceImpl();
            boolean updated = service.updateCampaign(campaign);

            if (updated) {
                response.sendRedirect(request.getContextPath() + "/Charitymain.jsp");
            } else {
                request.setAttribute("error", "Failed to update campaign. Please try again.");
                request.getRequestDispatcher("/updateCampaign.jsp?id=" + id).forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("/updateCampaign.jsp?id=" + request.getParameter("id")).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CampaignServiceInterface service = new CampaignServiceImpl();
        Campaign campaign = service.getCampaignById(id);

        request.setAttribute("campaign", campaign);
        request.getRequestDispatcher("/updateCampaign.jsp").forward(request, response);
    }
}
