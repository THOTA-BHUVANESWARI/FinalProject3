<%@ page language="java"
    import="com.src.service.*, java.util.List, com.src.model.*"
    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transactions Management</title>
<style>
table { border-collapse: collapse; width: 100%; margin-bottom: 40px; }
th, td { padding: 8px; text-align: center; border: 1px solid #ccc; }
th { background-color: #f2f2f2; }
a { display: block; padding: 5px; text-decoration: none; }
</style>
</head>
<body>
    <h1 style="text-align:center;">Transactions Management</h1>

    <%
        String msg = request.getParameter("msg");
        if(msg != null){
            out.print("<p style='color:green; text-align:center;'>" + msg + "</p>");
        }
        TransactionServiceInterface tsi = new TransactionServiceImpl();
        List<Transaction> transactions = tsi.getAllTransactions();
    %>

    <table>
        <tr>
            <th>ID</th>
            <th>Donor ID</th>
            <th>Campaign ID</th>
            <th>Amount</th>
            <th>Date</th>
            <th colspan="2" style="background-color:#0d6efd;color:white;">
                <a href="addTransaction.jsp" style="color:white;">Add Transaction</a>
            </th>
        </tr>
        <%
            for(Transaction t : transactions){
        %>
        <tr>
            <td><%= t.getTransactionId() %></td>
            <td><%= t.getDonorId() %></td>
            <td><%= t.getCampaignId() %></td>
            <td><%= t.getAmount() %></td>
            <td><%= t.getDonationDate() %></td>
            <td style="background-color:#DEDB40;"><a href="updateTransaction.jsp?id=<%= t.getTransactionId() %>">Update</a></td>
            <td style="background-color:red;"><a href="deleteTransaction?id=<%= t.getTransactionId() %>">Delete</a></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
