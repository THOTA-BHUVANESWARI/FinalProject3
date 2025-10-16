<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.src.model.*, com.src.service.*" %>
<%
    // Prevent caching
   
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxies


    HttpSession session1 = request.getSession(false);

    if (session1 == null || session1.getAttribute("donorId") == null) {
        response.sendRedirect("home.jsp");
        return;
    }

    Integer donorId = (Integer) session1.getAttribute("donorId");
    Donor donor = null;
    if (donorId != null) {
        DonorServiceInterface dsi = new DonorServiceImpl();
        donor = dsi.getDonorById(donorId);
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registered Charities</title>

<style>
body {
    font-family: "Segoe UI", Arial, sans-serif;
    background: linear-gradient(135deg, #e3f2fd, #a5d6a7);
    margin: 0;
    padding: 0;
}

/* Header styling */
.header {
    background-color: #2e7d32;
    color: white;
    padding: 15px 25px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    box-shadow: 0 3px 8px rgba(0,0,0,0.2);
}

.header a, .header form button {
    color: white;
    text-decoration: none;
    font-weight: 500;
    margin-left: 15px;
    background: none;
    border: none;
    cursor: pointer;
    font-size: 16px;
}

.header a:hover,
.header form button:hover {
    text-decoration: underline;
}

/* Container box */
.container {
    max-width: 1100px;
    margin: 50px auto;
    background: white;
    border-radius: 12px;
    padding: 25px 30px;
    box-shadow: 0 4px 15px rgba(0,0,0,0.15);
}

h2 {
    text-align: center;
    color: #1b5e20;
    margin-bottom: 25px;
}

/* Table styling */
table {
    width: 100%;
    border-collapse: collapse;
    font-size: 15px;
}

table thead {
    background-color: #66bb6a;
    color: white;
}

table th, table td {
    padding: 12px 15px;
    border-bottom: 1px solid #ddd;
    text-align: left;
}

table tr:hover {
    background-color: #f1f8e9;
}

/* Button styling */
button {
    background-color: #43a047;
    color: white;
    border: none;
    border-radius: 6px;
    padding: 6px 12px;
    cursor: pointer;
    font-size: 14px;
    transition: 0.3s;
}

button:hover {
    background-color: #2e7d32;
}
</style>
</head>
<body>

<!-- Header for Donor -->
<% if (donor != null) { %>
<div class="header">
    <div>Welcome, <strong><%= donor.getDonorName() %></strong></div>
    <div>
        <a href="donorDashboard.jsp">View Profile</a>
        <a href="DonorTransactions.jsp">Previous Transactions</a>
        <form action="LogoutDonor" method="post" style="display:inline;">
            <button type="submit">Logout</button>
        </form>
    </div>
</div>
<% } %>

<div class="container">
    <h2>Registered Charities</h2>

<%
    CharityServiceInterface csi = new CharityServiceImpl();
    List<Charity> charities = csi.getAllCharities();

    if (charities != null && !charities.isEmpty()) {
%>
    <table>
        <thead>
            <tr>
                <th>Charity ID</th>
                <th>Name</th>
                <th>Reg No</th>
                <th>Contact</th>
                <th>Email</th>
                <th>Location</th>
                <th>View Campaigns</th>
            </tr>
        </thead>
        <tbody>
<%
        for (Charity c : charities) {
%>
            <tr>
                <td><%= c.getCharityId() %></td>
                <td><%= c.getName() %></td>
                <td><%= c.getRegNo() %></td>
                <td><%= c.getContact() %></td>
                <td><%= c.getEmail() %></td>
                <td><%= c.getLocation() %></td>
                <td>
                    <a href="Campaignmain.jsp?charityId=<%= c.getCharityId() %>">
                        <button>View</button>
                    </a>
                </td>
            </tr>
<%
        }
%>
        </tbody>
    </table>
<%
    } else {
%>
    <p style="text-align:center;">No charities found in the database.</p>
<%
    }
%>
</div>

</body>
</html>
