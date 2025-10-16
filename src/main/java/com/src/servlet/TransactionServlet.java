package com.src.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.model.Transaction;
import com.src.service.TransactionServiceImpl;
import com.src.service.TransactionServiceInterface;

@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {

    private TransactionServiceInterface service = new TransactionServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int donorId = Integer.parseInt(request.getParameter("donorId"));
            int charityId = Integer.parseInt(request.getParameter("charityId"));
            int campaignId = Integer.parseInt(request.getParameter("campaignId"));
            double amount = Double.parseDouble(request.getParameter("amount"));

            String dateStr = request.getParameter("donationDate");
            Date donationDate = null;
            if (dateStr != null && !dateStr.isEmpty()) {
                donationDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            }

            // PAN number
            String panNumber = request.getParameter("panNumber");

            Transaction transaction = new Transaction();
            transaction.setDonorId(donorId);
            transaction.setCharityId(charityId);
            transaction.setCampaignId(campaignId);
            transaction.setAmount(amount);
            transaction.setDonationDate(donationDate);
            transaction.setPanNumber(panNumber); // store the URL in DB

            boolean inserted = service.addTransaction(transaction);

            if (inserted) {
                request.setAttribute("message", "Transaction added successfully!");
            } else {
                request.setAttribute("error", "Failed to add transaction.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
        }

        request.getRequestDispatcher("addTransaction.jsp").forward(request, response);
    }
}
