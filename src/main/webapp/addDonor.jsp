<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Donor Register & Login</title>
<style>
    body { 
        font-family: "Segoe UI", Arial, sans-serif; 
        background: linear-gradient(135deg, #e3f2fd, #bbdefb);
        margin: 0; padding: 0;
        display: flex; flex-direction: column; align-items: center; justify-content: center;
        height: 100vh;
    }
    h2 { color: #1565c0; margin-bottom: 10px; }
    form {
        width: 350px; background: #fff; padding: 25px 30px; border-radius: 12px;
        box-shadow: 0 4px 15px rgba(0,0,0,0.15); margin-top: 20px;
    }
    label { font-weight: bold; color: #333; }
    input[type=text], input[type=email], input[type=password] {
        width: 100%; padding: 10px; margin: 8px 0; border: 1px solid #ccc;
        border-radius: 8px; font-size: 14px;
    }
    input[type=submit] {
        background-color: #1976d2; color: white; border: none;
        padding: 10px; width: 100%; border-radius: 8px; cursor: pointer; font-size: 16px;
    }
    input[type=submit]:hover { background-color: #0d47a1; }
    .error { color: red; text-align: center; }
    .success { color: green; text-align: center; }
</style>

<script>
function validateRegistration() {
    const name = document.forms["registerForm"]["name"].value.trim();
    const email = document.forms["registerForm"]["email"].value.trim();
    const phone = document.forms["registerForm"]["phone"].value.trim();
    const address = document.forms["registerForm"]["address"].value.trim();
    const password = document.forms["registerForm"]["password"].value;
    const confirmPassword = document.forms["registerForm"]["confirmpassword"].value;

    const nameRegex = /^[A-Za-z\s]+$/;
    if (!nameRegex.test(name)) { alert("Name should contain only letters."); return false; }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) { alert("Enter valid email."); return false; }

    const phoneRegex = /^[0-9]{10}$/;
    if (!phoneRegex.test(phone)) { alert("Phone must be 10 digits."); return false; }

    const passwordRegex = /^(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
    if (!passwordRegex.test(password)) { alert("Password must be at least 8 chars with 1 special character."); return false; }

    if (password !== confirmPassword) { alert("Passwords do not match."); return false; }

    return true;
}
</script>
</head>

<body>

<%
    String msg = request.getParameter("msg");
    String status = request.getParameter("status");
    if (msg != null) {
%>
    <p class="<%= status %>"><%= msg %></p>
<%
    }
%>

<!-- Registration Form -->
<% if (msg == null || !msg.equals("Registration successful! Please login below.")) { %>
<h2>Donor Registration</h2>
<form name="registerForm" action="<%= request.getContextPath() %>/addDonor" method="post" onsubmit="return validateRegistration()">
    <label>Name:</label>
    <input type="text" name="name" required><br>

    <label>Email:</label>
    <input type="email" name="email" required><br>

    <label>Phone:</label>
    <input type="text" name="phone" required><br>

    <label>Address:</label>
    <input type="text" name="address" required><br>

    <label>Password:</label>
    <input type="password" name="password" required><br>

    <label>Confirm Password:</label>
    <input type="password" name="confirmpassword" required><br>

    <input type="submit" value="Register">
</form>
<% } else { %>

<!-- Login Form -->
<h2>Login to Continue</h2>
<form action="<%= request.getContextPath() %>/donorLogin" method="post">
    <label>Email:</label>
    <input type="email" name="email" required><br>

    <label>Password:</label>
    <input type="password" name="password" required><br>

    <input type="submit" value="Login">
</form>
<% } %>

</body>
</html>
