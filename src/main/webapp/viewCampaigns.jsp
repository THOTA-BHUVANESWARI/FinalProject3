<%@ page language="java"
    import="com.src.service.*, com.src.model.*, java.util.*"
    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Prevent caching and enforce login
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    Integer charityId = (Integer) session.getAttribute("charityId");
    if (charityId == null) {
        response.sendRedirect("CharityLogin.jsp");
        return;
    }

    CharityServiceInterface csi = new CharityServiceImpl();
    Charity charity = csi.getCharityById(charityId);

    CampaignServiceInterface campService = new CampaignServiceImpl();
    List<Campaign> campaigns = campService.getCampaignsByCharityId(charityId);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Campaigns</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f2f4f7;
    margin: 0;
    padding: 0;
}
header {
    background-color: #007bff;
    color: white;
    padding: 15px 30px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
header .logo {
    font-size: 20px;
    font-weight: bold;
}
header nav a {
    color: white;
    text-decoration: none;
    margin-left: 20px;
    font-weight: 500;
}
header nav a:hover {
    text-decoration: underline;
}
.container {
    background: white;
    max-width: 900px;
    margin: 50px auto;
    padding: 30px;
    border-radius: 12px;
    box-shadow: 0 4px 15px rgba(0,0,0,0.2);
}
h2 {
    text-align: center;
    color: #333;
}
table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}
th, td {
    border: 1px solid #ccc;
    padding: 10px;
    text-align: left;
}
th {
    background-color: #007bff;
    color: white;
}
tr:nth-child(even) {
    background-color: #f9f9f9;
}
</style>
</head>
<body>

<!-- Header -->
<header>
    <div class="logo">Charity Dashboard</div>
    <nav>
        <a href="editProfile.jsp?charityId=<%= charity.getCharityId() %>">Edit Profile</a>
        <a href="viewCampaigns.jsp?charityId=<%= charity.getCharityId() %>">View All Campaigns</a>
        <form action="logoutCharity" method="post" style="display:inline;">
            <button type="submit" style="padding:5px 12px; font-size:14px; background-color:#dc3545; color:white; border:none; border-radius:4px;">Logout</button>
        </form>
    </nav>
</header>

<div class="container">
    <h2>All Campaigns Added by <%= charity.getName() %></h2>

    <% if (campaigns == null || campaigns.isEmpty()) { %>
        <p>No campaigns found. Click <a href="addCampaign.jsp?charityId=<%= charity.getCharityId() %>">here</a> to add a new campaign.</p>
    <% } else { %>
        <table>
            <tr>
                <th>Campaign ID</th>
               
                <th>Description</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Status</th>
            </tr>
            <% for (Campaign c : campaigns) { %>
            <tr>
                <td><%= c.getCampaignId() %></td>
               
                <td><%= c.getDescription() %></td>
                <td><%= c.getStartDate() %></td>
                <td><%= c.getEndDate() %></td>
                <td><%= c.getStatus() %></td>
            </tr>
            <% } %>
        </table>
    <% } %>
    <a href="charityDashboard.jsp"><-back</a>
</div>

</body>
</html>
