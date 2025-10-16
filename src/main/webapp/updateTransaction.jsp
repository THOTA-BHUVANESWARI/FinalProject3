<%@ page language="java" import="com.src.service.*, com.src.model.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    TransactionServiceInterface tsi = new TransactionServiceImpl();
    Transaction trans = tsi.getTransactionById(id);   // ✅ Correct method
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Transaction</title>
</head>
<body>
	<h2>Update Transaction</h2>
	<form action="updateTransaction" method="post">
		<input type="hidden" name="id" value="<%= trans.getTransactionId() %>">
		Donor ID: <input type="text" name="donorId"
			value="<%= trans.getDonorId() %>" required><br>
		<br> Campaign ID: <input type="text" name="campaignId"
			value="<%= trans.getCampaignId() %>" required><br>
		<br> Amount: <input type="number" name="amount"
			value="<%= trans.getAmount() %>" required><br>
		<br> Date: <input type="date" name="date"
			value="<%= trans.getDonationDate() %>" required><br>
		<br> <input type="submit" value="Update Transaction">
	</form>
</body>
</html>
