<%@ page language="java" import="com.src.model.*, com.src.service.*, java.util.List" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String donorEmail = (String) session.getAttribute("donorEmail");
    if(donorEmail == null){
        response.sendRedirect("Donormain.jsp");
        return;
    }

    DonorServiceInterface dsi = new DonorServiceImpl();
    Donor donor = dsi.getDonorByEmail(donorEmail);

    TransactionServiceInterface tsi = new TransactionServiceImpl();
    List<Transaction> transactions = tsi.getTransactionsByDonorId(donor.getDonorId());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction History</title>
<style>
    body {
        font-family: "Poppins", Arial, sans-serif;
        background: linear-gradient(135deg, #e3f2fd, #f0f4c3);
        margin: 0;
        padding: 0;
    }

    .container {
        max-width: 900px;
        margin: 50px auto;
        background: #fff;
        padding: 30px;
        border-radius: 12px;
        box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        animation: fadeIn 0.8s ease;
    }

    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(20px);}
        to { opacity: 1; transform: translateY(0);}
    }

    h2 {
        text-align: center;
        color: #2e7d32;
        margin-bottom: 25px;
        text-transform: uppercase;
        letter-spacing: 1px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        font-size: 15px;
        border-radius: 8px;
        overflow: hidden;
    }

    thead {
        background-color: #43a047;
        color: white;
    }

    th, td {
        padding: 12px 15px;
        text-align: center;
        border-bottom: 1px solid #ddd;
    }

    tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    tr:hover {
        background-color: #e8f5e9;
        transition: 0.3s;
    }

    p {
        text-align: center;
        color: #555;
        font-size: 1.1em;
        margin-top: 20px;
    }
</style>
</head>
<body>
<div class="container">
    <h2>Transaction History</h2>
    <% if(transactions != null && !transactions.isEmpty()){ %>
        <table>
            <thead>
                <tr>
                    <th>Transaction ID</th>
                    <th>Charity Name</th>
                    <th>Amount</th>
                    <th>Date</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <% for(Transaction t : transactions){ %>
                <tr>
                    <td><%= t.getTransactionId() %></td>
                    <td><%= t.getCharityId() %></td>
                    <td>₹<%= t.getAmount() %></td>
                    <td><%= t.getDonationDate() %></td>
                    
                </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>No transactions found.</p>
    <% } %>
</div>
</body>
</html>
