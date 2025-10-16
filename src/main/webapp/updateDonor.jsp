<%@ page language="java" import="com.src.model.*, com.src.service.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String donorEmail = (String) session.getAttribute("donorEmail");
    if(donorEmail == null){
        response.sendRedirect("Donormain.jsp");
        return;
    }

    DonorServiceInterface dsi = new DonorServiceImpl();
    Donor donor = dsi.getDonorByEmail(donorEmail);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Donor Profile</title>
<style>
/* Body and background */
body {
    font-family: 'Poppins', Arial, sans-serif;
    background: linear-gradient(135deg, #c8e6c9, #bbdefb);
    margin: 0;
    padding: 0;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

/* Container box */
.container {
    background: #ffffff;
    padding: 40px 35px;
    border-radius: 15px;
    box-shadow: 0 10px 30px rgba(0,0,0,0.15);
    width: 400px;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.container:hover {
    transform: translateY(-5px);
    box-shadow: 0 15px 35px rgba(0,0,0,0.25);
}

/* Heading */
h2 {
    text-align: center;
    color: #2e7d32;
    margin-bottom: 30px;
    letter-spacing: 1px;
}

/* Form styles */
form {
    display: flex;
    flex-direction: column;
}

label {
    font-weight: 600;
    margin-bottom: 5px;
    color: #333;
}

input[type="text"], input[type="email"] {
    padding: 12px 15px;
    border: 1px solid #ccc;
    border-radius: 10px;
    margin-bottom: 20px;
    font-size: 15px;
    transition: all 0.3s ease;
}

input[type="text"]:focus, input[type="email"]:focus {
    outline: none;
    border-color: #43a047;
    box-shadow: 0 0 8px rgba(67,160,71,0.3);
}

/* Submit button */
input[type="submit"] {
    background-color: #43a047;
    color: white;
    font-size: 16px;
    padding: 12px;
    border: none;
    border-radius: 10px;
    cursor: pointer;
    font-weight: 500;
    transition: 0.3s ease;
}

input[type="submit"]:hover {
    background-color: #2e7d32;
    transform: translateY(-2px);
}

/* Responsive adjustments */
@media screen and (max-width: 480px) {
    .container {
        width: 90%;
        padding: 30px 20px;
    }
    input[type="text"], input[type="email"] {
        font-size: 14px;
        padding: 10px 12px;
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
    <h2>Update Profile</h2>
    <form action="updateDonor" method="post">
        <input type="hidden" name="id" value="<%= donor.getDonorId() %>">

        <label for="name">Name:</label>
        <input type="text" name="name" id="name" value="<%= donor.getDonorName() %>" required>

        <label for="email">Email:</label>
        <input type="email" name="email" id="email" value="<%= donor.getEmail() %>" required>

        <label for="mobile">Mobile:</label>
        <input type="text" name="mobile" id="mobile" value="<%= donor.getPhone() %>" required>

        <label for="address">Address:</label>
        <input type="text" name="address" id="address" value="<%= donor.getAddress() %>" required>

        <input type="submit" value="Update Profile">
    </form>
</div>

</body>
</html>
