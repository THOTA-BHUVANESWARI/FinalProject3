<%@ page language="java"
         import="com.src.service.*, com.src.model.*, java.util.List, java.util.Date, java.text.SimpleDateFormat"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    // Prevent caching
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0L);

    // Check session
    Integer charityId = (Integer) session.getAttribute("charityId");
    if (charityId == null) {
        response.sendRedirect("CharityLogin.jsp");
        return;
    }

    // Services
    CharityServiceInterface csi = new CharityServiceImpl();
    Charity charity = csi.getCharityById(charityId);

    CampaignServiceInterface campSI = new CampaignServiceImpl();
    List<Campaign> campaigns = campSI.getCampaignsByCharityId(charityId);

    TransactionServiceInterface tsi = new TransactionServiceImpl();
    DonorServiceInterface dsi = new DonorServiceImpl();
    List<Transaction> transactions = tsi.getTransactionsByCharityId(charityId);

    // Date formatter (adjust pattern as you wish)
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Charity Dashboard</title>
<style>
/* General styling */
body {
    font-family: 'Nunito Sans', Arial, sans-serif;
    background-color: #e6f2f0;
    margin: 0;
    padding: 0;
    color: #2d6a4f;
}

/* Header */
header {
    background-color: #52b788;
    color: white;
    padding: 15px 30px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    box-shadow: 0 3px 8px rgba(0,0,0,0.1);
}
header .logo { font-size: 22px; font-weight: bold; }
header nav a {
    color: white; text-decoration: none; margin-left: 20px; font-weight: 600;
}
header nav a:hover { text-decoration: underline; }
header nav form button {
    background-color: #40916c; border: none; padding: 6px 12px; border-radius: 6px;
    color: white; font-weight: 600; cursor: pointer; margin-left: 10px;
}
header nav form button:hover { background-color: #2d6a4f; }

/* Container */
.container {
    max-width: 950px; margin: 50px auto; background: white; border-radius: 15px;
    padding: 30px; box-shadow: 0 5px 20px rgba(0,0,0,0.1);
}

/* Titles */
h2 { text-align: center; margin-bottom: 20px; }
h3 { color: #2d6a4f; margin-top: 30px; }

/* Info table */
table { width: 100%; border-collapse: collapse; margin-top: 15px; font-size: 14px; }
th, td { padding: 12px 15px; border-bottom: 1px solid #d9e6e2; text-align: left; }
th { background-color: #95d5b2; color: #1b4332; font-weight: 600; }
tr:nth-child(even) { background-color: #f1f9f6; }
tr:hover { background-color: #d8f3dc; }

/* Buttons */
button.add-btn {
    background-color: #52b788; color: white; border: none; border-radius: 6px;
    padding: 10px 20px; cursor: pointer; font-weight: 600; transition: all 0.3s;
}
button.add-btn:hover { background-color: #40916c; transform: translateY(-2px); }
button.delete-btn {
    background-color: #f45b69; color: white; border: none; border-radius: 6px;
    padding: 6px 12px; cursor: pointer; transition: all 0.3s;
}
button.delete-btn:hover { background-color: #d63447; transform: translateY(-2px); }

/* Responsive */
@media (max-width: 768px) {
    header { flex-direction: column; align-items: flex-start; }
    .container { margin: 20px 15px; padding: 20px; }
    table th, table td { font-size: 12px; padding: 8px 10px; }
}
</style>
</head>
<body>

<header>
    <div class="logo">Charity Dashboard</div>
    <nav>
        <a href="updateCharity.jsp?id=<%= charity != null ? charity.getCharityId() : "" %>">Edit</a>
        <form action="logoutCharity" method="post" style="display:inline;">
            <button type="submit">Logout</button>
        </form>
    </nav>
</header>

<div class="container">
    <h2>Welcome, <%= charity != null ? charity.getName() : "Charity" %></h2>

    <table>
        <tr>
            <th>Registration No</th><td><%= charity != null ? charity.getRegNo() : "N/A" %></td>
        </tr>
        <tr>
            <th>Contact</th><td><%= charity != null ? charity.getContact() : "N/A" %></td>
        </tr>
        <tr>
            <th>Email</th><td><%= charity != null ? charity.getEmail() : "N/A" %></td>
        </tr>
        <tr>
            <th>Location</th><td><%= charity != null ? charity.getLocation() : "N/A" %></td>
        </tr>
    </table>

    <h3>Your Campaigns</h3>
    <% if (campaigns != null && !campaigns.isEmpty()) { %>
    <table>
        <tr>
            <th>ID</th><th>Title</th><th>Description</th><th>Start Date</th><th>End Date</th><th>Actions</th>
        </tr>
        <% for (Campaign c : campaigns) { %>
        <tr>
            <td><%= c.getCampaignId() %></td>
            <td><%= c.getCampaignName() != null ? c.getCampaignName() : "-" %></td>
            <td><%= c.getDescription() != null ? c.getDescription() : "-" %></td>
            <td>
                <%
                java.time.LocalDate sd = c.getStartDate();
                out.print(sd != null ? sd.format(java.time.format.DateTimeFormatter.ofPattern("dd-MMM-yyyy")) : "-");

                %>
            </td>
            <td>
                <%
                java.time.LocalDate ed = c.getEndDate();
                out.print(ed != null ? ed.format(java.time.format.DateTimeFormatter.ofPattern("dd-MMM-yyyy")) : "-");

                %>
            </td>
            <td>
                <!-- Edit -->
                <form action="updateCampaign.jsp" method="get" style="display:inline;">
                    <input type="hidden" name="id" value="<%= c.getCampaignId() %>">
                    <button type="submit" class="add-btn" style="background-color:#1976d2;">Edit</button>
                </form>

                <!-- Delete -->
                <form action="<%= request.getContextPath() %>/deleteCampaign" method="post" style="display:inline;"
                      onsubmit="return confirm('Are you sure you want to delete this campaign?');">
                    <input type="hidden" name="campaignId" value="<%= c.getCampaignId() %>">
                    <button type="submit" class="delete-btn">Delete</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
    <% } else { %>
        <p>No campaigns found. Click “Add Campaign” to create one.</p>
    <% } %>

    <h3>Donations Received</h3>
    <% if (transactions != null && !transactions.isEmpty()) { %>
    <table>
        <thead>
            <tr>
                <th>Transaction ID</th>
                <th>Campaign ID</th>
                <th>Donor Name</th>
                <th>Donor Email</th>
                <th>Donor Phone</th>
                <th>Amount</th>
                <th>Date</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (Transaction t : transactions) {
                // Defensive donor lookup: try int signature first, else fallback to String
                Donor donor = null;
                try {
                    int donorId = Integer.parseInt(String.valueOf(t.getDonorId()));
                    donor = dsi.getDonorById(donorId);
                } catch (Exception e) {
                    donor = null;
                }

                
                
                java.util.Date txDate = t.getDonationDate(); // use the correct field getter
                java.text.SimpleDateFormat sd = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            %>
            <tr>
                <td><%= t.getTransactionId() %></td>
                <td><%= t.getCampaignId() %></td>
                <td><%= donor != null ? donor.getDonorName() : "Unknown" %></td>
                <td><%= donor != null ? donor.getEmail() : "N/A" %></td>
                <td><%= donor != null ? donor.getPhone() : "N/A" %></td>
                <td>₹<%= t.getAmount() %></td>
                <td><%= txDate != null ? sdf.format(txDate) : "-" %></td>
            </tr>


        <% } %>
        </tbody>
    </table>
    <% } else { %>
        <p>No donations received yet.</p>
    <% } %>

    <div style="margin-top:20px; text-align:center;">
        <form action="addCampaign.jsp" method="get">
            <input type="hidden" name="charityId" value="<%= charity != null ? charity.getCharityId() : "" %>">
            <button type="submit" class="add-btn">Add Campaign</button>
        </form>
    </div>
</div>

</body>
</html>
