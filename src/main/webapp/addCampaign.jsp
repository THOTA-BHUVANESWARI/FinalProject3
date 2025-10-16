<%@ page language="java" import="com.src.service.*, com.src.model.*, java.util.*" 
    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Campaign</title>
<style>
/* Reset & body */
body {
    font-family: 'Nunito', 'Segoe UI', Arial, sans-serif;
    background: linear-gradient(135deg, #d0f0c0, #a8e6cf);
    margin: 0;
    padding: 0;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

/* Container styling */
.container {
    background-color: #ffffff;
    padding: 35px 45px;
    border-radius: 16px;
    box-shadow: 0 8px 25px rgba(0,0,0,0.15);
    width: 420px;
    max-width: 90%;
}

/* Heading */
h2 {
    text-align: center;
    color: #2d6a4f;
    font-size: 24px;
    margin-bottom: 30px;
}

/* Form styling */
form {
    display: flex;
    flex-direction: column;
}

label {
    font-weight: 600;
    margin-bottom: 6px;
    color: #333;
}

input[type="text"],
input[type="date"],
select {
    padding: 10px 12px;
    border: 1px solid #b7d4c7;
    border-radius: 8px;
    margin-bottom: 18px;
    font-size: 14px;
    transition: all 0.2s ease-in-out;
}

input[type="text"]:focus,
input[type="date"]:focus,
select:focus {
    outline: none;
    border-color: #40916c;
    box-shadow: 0 0 6px rgba(64, 145, 108, 0.3);
}

/* Submit button */
input[type="submit"] {
    background-color: #52b788;
    color: #ffffff;
    font-size: 16px;
    padding: 12px;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-weight: 600;
    transition: all 0.3s ease-in-out;
}

input[type="submit"]:hover {
    background-color: #40916c;
    transform: translateY(-2px);
}

/* Success & error messages */
.message {
    text-align: center;
    font-weight: 600;
    margin-bottom: 20px;
    font-size: 14px;
    padding: 8px;
    border-radius: 6px;
}

.success {
    color: #155724;
    background-color: #d4edda;
    border: 1px solid #c3e6cb;
}

.error {
    color: #721c24;
    background-color: #f8d7da;
    border: 1px solid #f5c6cb;
}

/* Charity ID display */
.container p {
    font-weight: 600;
    margin-bottom: 18px;
    color: #2d6a4f;
}

/* Responsive */
@media (max-width: 480px) {
    .container {
        padding: 25px 20px;
        width: 90%;
    }
    h2 {
        font-size: 20px;
    }
    input[type="submit"] {
        font-size: 15px;
        padding: 10px;
    }
}
</style>

</head>
<body>
<div class="container">
    <h2>Add Campaign</h2>

    <%-- ✅ Display success/error messages --%>
    <%
        String msg = request.getParameter("msg");
        if ("success".equals(msg)) {
    %>
        <div class="message success">✅ Campaign added successfully!</div>
    <% } else if ("error".equals(msg)) { %>
        <div class="message error">❌ Failed to add campaign. Please try again.</div>
    <% } %>

    <form action="<%= request.getContextPath() %>/addCampaign" method="post">
        
        <% 
            Integer charityId = (Integer) session.getAttribute("charityId");
            if (charityId != null) { 
        %>
            <%-- ✅ Fixed charity ID for logged-in charity --%>
            <input type="hidden" name="charityId" value="<%= charityId %>">
            <p><strong>Charity ID:</strong> <%= charityId %></p>
        <% 
            } else { 
                CharityServiceInterface charityService = new CharityServiceImpl();
                List<Charity> charities = charityService.getAllCharities();
        %>
            <%-- ✅ Dropdown for admin (if no charity logged in) --%>
            <label for="charityId">Select Charity:</label>
            <select name="charityId" required>
                <% for (Charity c : charities) { %>
                    <option value="<%= c.getCharityId() %>">
                        <%= c.getCharityId() %> - <%= c.getName() %>

                    </option>
                <% } %>
            </select>
        <% } %>

        <label for="title">Title:</label>
        <input type="text" name="title" required>

        <label for="description">Description:</label>
        <input type="text" name="description" required>

        <label for="startDate">Start Date:</label>
<input type="date" name="startDate" id="startDate" required>

<label for="endDate">End Date:</label>
<input type="date" name="endDate" id="endDate" required>
        
        

        <input type="submit" value="Save Campaign">
    </form>
</div>
<script>
// ✅ Disable past dates
window.addEventListener('DOMContentLoaded', (event) => {
    const today = new Date().toISOString().split('T')[0]; // yyyy-mm-dd

    const startDateInput = document.getElementById('startDate');
    const endDateInput = document.getElementById('endDate');

    startDateInput.setAttribute('min', today);
    endDateInput.setAttribute('min', today);

    // Optional: auto-update end date min when start date changes
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
