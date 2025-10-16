package com.src.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.src.service.CampaignServiceImpl;
import com.src.service.CampaignServiceInterface;

@WebServlet("/deleteCampaign")
public class DeleteCampaignServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CampaignServiceInterface campaignService = new CampaignServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String campaignIdStr = request.getParameter("campaignId");
        HttpSession session = request.getSession();
        Integer charityId = (Integer) session.getAttribute("charityId");

        if (campaignIdStr != null && charityId != null) {
            try {
                int campaignId = Integer.parseInt(campaignIdStr);

                boolean deleted = campaignService.deleteCampaign(campaignId);

                if (deleted) {
                    session.setAttribute("message", "Campaign deleted successfully!");
                } else {
                    session.setAttribute("message", "Failed to delete campaign.");
                }

            } catch (NumberFormatException e) {
                session.setAttribute("message", "Invalid campaign ID.");
            }
        } else {
            session.setAttribute("message", "No campaign selected or not logged in.");
        }

        // Redirect back to the dashboard
        response.sendRedirect(request.getContextPath() + "/charityDashboard.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Simple test to ensure servlet is reachable
        response.getWriter().println("DeleteCampaignServlet is reachable!");
    }
}
