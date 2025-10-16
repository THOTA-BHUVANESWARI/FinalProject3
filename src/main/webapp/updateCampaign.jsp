<%@ page language="java" import="com.src.service.*, com.src.model.*, java.util.*" 
    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Integer charityId = (Integer) session.getAttribute("charityId");
    if (charityId == null) {
        response.sendRedirect("CharityLogin.jsp");
        return;
    }

    int id = Integer.parseInt(request.getParameter("id"));
    CampaignServiceInterface campSI = new CampaignServiceImpl();
    Campaign campaign = campSI.getCampaignById(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Campaign</title>
<style>
    body { font-family: 'Segoe UI', Arial, sans-serif; background: #f0f8f5; padding: 30px; }
    h2 { color: #2d6a4f; }
    form { background: #fff; padding: 25px 30px; border-radius: 12px; max-width: 500px; box-shadow: 0 4px 12px rgba(0,0,0,0.1);}
    label { font-weight: bold; margin-top: 10px; display: block; }
    input[type=text], input[type=date] { width: 100%; padding: 10px; margin: 8px 0; border-radius: 8px; border: 1px solid #ccc; }
    input[type=submit] { background: #52b788; color: white; padding: 12px; border: none; border-radius: 8px; cursor: pointer; margin-top: 15px; }
    input[type=submit]:hover { background: #40916c; }
</style>
</head>
<body>

<h2>Update Campaign</h2>
<form action="<%= request.getContextPath() %>/updateCampaign" method="post">
    <!-- Campaign ID hidden to prevent modification -->
    <input type="hidden" name="id" value="<%= campaign.getCampaignId() %>">
    <p><strong>Campaign ID: </strong><%= campaign.getCampaignId() %></p>

    <!-- Fixed Charity ID -->
    <input type="hidden" name="charityId" value="<%= charityId %>">
    <p><strong>Charity ID: </strong><%= charityId %></p>

    <label>Title:</label>
    <input type="text" name="title" value="<%= campaign.getCampaignName() %>" required>

    <label>Description:</label>
    <input type="text" name="description" value="<%= campaign.getDescription() %>" required>

    <label>Start Date:</label>
    <input type="date" name="startDate" id="startDate" value="<%= campaign.getStartDate() %>" required>

    <label>End Date:</label>
    <input type="date" name="endDate" id="endDate" value="<%= campaign.getEndDate() %>" required>

    <input type="submit" value="Update Campaign">
</form>

<script>
// Disable past dates and ensure start date <= end date
window.addEventListener('DOMContentLoaded', () => {
    const today = new Date().toISOString().split('T')[0];
    const startDateInput = document.getElementById('startDate');
    const endDateInput = document.getElementById('endDate');

    startDateInput.setAttribute('min', today);
    endDateInput.setAttribute('min', today);

    startDateInput.addEventListener('change', function() {
        endDateInput.min = this.value;
        if (endDateInput.value < this.value) {
            endDateInput.value = this.value;
        }
    });
});
</script>

</body>
</html>
