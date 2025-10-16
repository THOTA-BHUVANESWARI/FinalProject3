<%@ page language="java"
    import="com.src.service.*, java.util.List, com.src.model.*"
    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String donorEmail = (String) session.getAttribute("donorEmail");
    if (donorEmail == null) {
        response.sendRedirect("Donormain.jsp");
        return;
    }

    DonorServiceInterface dsi = new DonorServiceImpl();
    Donor donor = dsi.getDonorByEmail(donorEmail);

    String charityIdStr = request.getParameter("charityId");
    if (charityIdStr == null) {
        out.println("<p style='color:red; text-align:center;'>No charity selected.</p>");
        return;
    }

    int charityId = Integer.parseInt(charityIdStr);
    CampaignServiceInterface campSI = new CampaignServiceImpl();
    List<Campaign> campaigns = campSI.getCampaignsByCharityId(charityId);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Campaigns Management</title>
<style>
    body {
        font-family: "Poppins", "Segoe UI", Arial, sans-serif;
        background: linear-gradient(135deg, #e3f2fd, #e8f5e9);
        margin: 0;
        padding: 0;
    }

    header {
        background-color: #1565c0;
        color: white;
        text-align: center;
        padding: 25px 0;
        font-size: 26px;
        font-weight: 600;
        letter-spacing: 1px;
        box-shadow: 0 3px 10px rgba(0, 0, 0, 0.2);
    }

    .container {
        width: 90%;
        max-width: 1000px;
        margin: 50px auto;
        background: #ffffff;
        border-radius: 16px;
        padding: 30px;
        box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
        animation: fadeIn 0.8s ease;
    }

    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(20px); }
        to { opacity: 1; transform: translateY(0); }
    }

    h2 {
        color: #1b5e20;
        text-align: center;
        margin-bottom: 25px;
        font-size: 22px;
        text-transform: uppercase;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        background-color: #fff;
        border-radius: 12px;
        overflow: hidden;
    }

    thead {
        background: #2e7d32;
        color: white;
    }

    th, td {
        padding: 14px 16px;
        text-align: center;
        border-bottom: 1px solid #ddd;
        font-size: 15px;
    }

    tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    tr:hover {
        background-color: #e8f5e9;
        transition: background-color 0.3s ease;
    }

    button {
        background-color: #43a047;
        color: white;
        border: none;
        border-radius: 8px;
        padding: 8px 14px;
        cursor: pointer;
        font-size: 15px;
        font-weight: 500;
        box-shadow: 0 3px 8px rgba(0, 0, 0, 0.2);
        transition: background-color 0.3s ease, transform 0.2s ease;
    }

    button:hover {
        background-color: #2e7d32;
        transform: scale(1.05);
    }

    .back-link {
        display: inline-block;
        text-align: center;
        text-decoration: none;
        background-color: #1565c0;
        color: white;
        padding: 10px 20px;
        border-radius: 8px;
        box-shadow: 0 3px 8px rgba(0, 0, 0, 0.2);
        transition: background-color 0.3s ease, transform 0.2s ease;
        margin-top: 25px;
    }

    .back-link:hover {
        background-color: #0d47a1;
        transform: scale(1.05);
    }

    p {
        text-align: center;
        color: #555;
        font-size: 1.1em;
    }
</style>
</head>
<body>

<header>Available Campaigns for Charity ID: <%= charityId %></header>

<div class="container">
    <% if (campaigns != null && !campaigns.isEmpty()) { %>
        <h2>Campaign List</h2>
        <table>
            <thead>
                <tr>
                    <th>Campaign ID</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% for (Campaign c : campaigns) { %>
                <tr>
                    <td><%= c.getCampaignId() %></td>
                    <td><%= c.getCampaignName() %></td>
                    <td><%= c.getDescription() %></td>
                    <td><%= c.getStartDate() %></td>
                    <td><%= c.getEndDate() %></td>
                    <td>
                        <form action="addTransaction.jsp" method="get">
    <input type="hidden" name="campaignId" value="<%= c.getCampaignId() %>">
    <input type="hidden" name="donorId" value="<%= donor.getDonorId() %>">
    <input type="hidden" name="charityId" value="<%= c.getCharityId() %>"> <%-- Pass charityId from campaign --%>
    <button type="submit">Donate</button>
</form>

                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>No campaigns found for this charity.</p>
    <% } %>

    <div style="text-align:center;">
        <a href="Charitymain.jsp" class="back-link">⬅ Back to Dashboard</a>
    </div>
</div>

</body>
</html>
