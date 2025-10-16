<%@ page language="java" 
    import="com.src.model.*, com.src.service.*, java.util.List" 
    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>Donor Dashboard</title>
<style>
/* General body styles */
body {
    font-family: 'Poppins', Arial, sans-serif;
    background: linear-gradient(135deg, #e3f2fd, #c8e6c9);
    margin: 0;
    padding: 0;
}

/* Container box */
.container {
    max-width: 950px;
    margin: 60px auto;
    background: #ffffff;
    padding: 35px 40px;
    border-radius: 15px;
    box-shadow: 0 8px 25px rgba(0,0,0,0.15);
    animation: fadeIn 0.8s ease;
}

/* Fade in animation */
@keyframes fadeIn {
    from { opacity: 0; transform: translateY(20px);}
    to { opacity: 1; transform: translateY(0);}
}

/* Header */
h2 {
    text-align: center;
    color: #2e7d32;
    margin-bottom: 30px;
    text-transform: uppercase;
    letter-spacing: 1px;
}

/* Donor info table */
table {
    width: 100%;
    border-collapse: collapse;
    font-size: 16px;
    margin-bottom: 30px;
}

th, td {
    padding: 12px 18px;
    border-bottom: 1px solid #ddd;
    text-align: left;
}

th {
    background-color: #43a047;
    color: white;
    font-weight: 600;
    text-transform: uppercase;
}

tr:nth-child(even) {
    background-color: #f1f8e9;
}

tr:hover {
    background-color: #e8f5e9;
    transition: background-color 0.3s ease;
}

/* Action buttons */
.actions {
    text-align: center;
    margin-top: 20px;
}

button {
    padding: 12px 25px;
    margin: 0 10px;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-weight: 500;
    font-size: 15px;
    color: white;
    background: #0d6efd;
    box-shadow: 0 4px 10px rgba(0,0,0,0.15);
    transition: 0.3s ease;
}

button:hover {
    background-color: #0b5ed7;
    transform: translateY(-2px);
}

/* Logout button style */
.logout-btn {
    background-color: #dc3545;
}

.logout-btn:hover {
    background-color: #bb2d3b;
}
</style>
</head>
<body>
<div class="container">
    <h2>Donor Dashboard</h2>
    
    <!-- Donor Details -->
    <table>
        <tr><th>Name:</th><td><%= donor.getDonorName() %></td></tr>
        <tr><th>Email:</th><td><%= donor.getEmail() %></td></tr>
        <tr><th>Phone:</th><td><%= donor.getPhone() %></td></tr>
        <tr><th>Address:</th><td><%= donor.getAddress() %></td></tr>
    </table>

    <!-- Actions -->
    <div class="actions">
        <form action="updateDonor.jsp" method="get" style="display:inline;">
            <button type="submit">Edit Profile</button>
        </form>
        
    </div>
</div>
</body>
</html>
