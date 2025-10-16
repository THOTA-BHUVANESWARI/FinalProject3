<%@ page language="java" import="com.src.service.*, com.src.model.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    CharityServiceInterface csi = new CharityServiceImpl();
    Charity charity = csi.getCharityById(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Charity</title>
<style>
/* ===== Body & Background ===== */
body {
    font-family: 'Nunito Sans', Arial, sans-serif;
    background: linear-gradient(135deg, #d0f4de, #e6f2f0);
    margin: 0;
    padding: 0;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

/* ===== Form Container ===== */
.container {
    background: #ffffff;
    padding: 40px 35px;
    border-radius: 15px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.12);
    width: 420px;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.container:hover {
    transform: translateY(-5px);
    box-shadow: 0 15px 35px rgba(0,0,0,0.18);
}

/* ===== Heading ===== */
h2 {
    text-align: center;
    color: #2d6a4f;
    margin-bottom: 30px;
    font-size: 26px;
    letter-spacing: 1px;
}

/* ===== Labels & Inputs ===== */
label {
    font-weight: 600;
    margin-bottom: 5px;
    color: #2d6a4f;
}

input[type=text], input[type=number] {
    width: 100%;
    padding: 12px 15px;
    margin: 8px 0 18px 0;
    border: 1px solid #ccc;
    border-radius: 10px;
    font-size: 15px;
    transition: all 0.3s ease;
}

input[type=text]:focus, input[type=number]:focus {
    outline: none;
    border-color: #52b788;
    box-shadow: 0 0 8px rgba(82, 183, 136, 0.3);
    background-color: #fefefc;
}

/* ===== Readonly Fields ===== */
input[readonly] {
    background-color: #f5f5f5;
    color: #555;
    cursor: not-allowed;
}

/* ===== Submit Button ===== */
input[type=submit] {
    background-color: #52b788;
    color: white;
    font-size: 16px;
    padding: 12px;
    width: 100%;
    border: none;
    border-radius: 10px;
    cursor: pointer;
    font-weight: 600;
    transition: background-color 0.3s ease, transform 0.2s ease;
}

input[type=submit]:hover {
    background-color: #40916c;
    transform: translateY(-2px);
}

/* ===== Responsive ===== */
@media (max-width: 480px) {
    .container {
        width: 90%;
        padding: 30px 20px;
    }
    h2 {
        font-size: 22px;
    }
    input[type=submit] {
        font-size: 15px;
        padding: 10px;
    }
}
</style>
</head>

<body>
<div class="container">
	<h2>Update Charity</h2>
	<form action="updateCharity" method="post">
        <input type="hidden" name="charityId" value="<%= charity.getCharityId() %>">

        <label>Name:</label>
        <input type="text" name="name" value="<%= charity.getName() %>" required>

        <label>RegNO:</label>
        <input type="text" name="regno" value="<%= charity.getRegNo() %>" readonly>

        <label>Contact:</label>
        <input type="text" name="contact" value="<%= charity.getContact() %>" required>

        <label>Email:</label>
        <input type="text" name="email" value="<%= charity.getEmail() %>" required>

        <label>Location:</label>
        <input type="text" name="location" value="<%= charity.getLocation() %>" required>

        <input type="submit" value="Update Charity">
    </form>
</div>
</body>
</html>
