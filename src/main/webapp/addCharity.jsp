<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Charity Registration</title>

<style>
    body { 
        font-family: "Segoe UI", Arial, sans-serif; 
        background: linear-gradient(135deg, #d9f0e1, #a8e6cf);
        display: flex; flex-direction: column;
        align-items: center; justify-content: center;
        height: 100vh; margin: 0;
    }
    form {
        width: 400px; background: #fff; padding: 25px 30px;
        border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.15);
    }
    h2 { color: #2e7d32; text-align: center; margin-bottom: 20px; }
    label { font-weight: bold; color: #333; display: block; margin-top: 10px; }
    input[type=text], input[type=password], input[type=email] {
        width: 100%; padding: 10px; margin-top: 5px;
        border: 1px solid #ccc; border-radius: 8px; font-size: 14px;
    }
    input[type=submit] {
        background-color: #66bb6a; color: white;
        border: none; padding: 10px; width: 100%;
        border-radius: 8px; cursor: pointer; font-size: 16px;
        margin-top: 20px;
    }
    input[type=submit]:hover { background-color: #388e3c; }
    .error { color: red; font-size: 13px; display: block; margin-top: 4px; }
</style>

<script>
function validateCharityForm() {
    let valid = true;

    // clear old errors
    document.querySelectorAll(".error").forEach(e => e.textContent = "");

    const name = document.forms["charityForm"]["name"].value.trim();
    const regno = document.forms["charityForm"]["regno"].value.trim();
    const contact = document.forms["charityForm"]["contact"].value.trim();
    const email = document.forms["charityForm"]["email"].value.trim();
    const location = document.forms["charityForm"]["location"].value.trim();
    const password = document.forms["charityForm"]["password"].value;
    const confirmPassword = document.forms["charityForm"]["confirmPassword"].value;

    // Name
    if (!/^[A-Za-z\s]+$/.test(name)) {
        document.getElementById("nameError").textContent = "Name should contain only letters and spaces.";
        valid = false;
    }

    // Registration number - exactly 5 digits
    if (!/^[0-9]{5}$/.test(regno)) {
        document.getElementById("regError").textContent = "Registration number must be exactly 5 digits.";
        valid = false;
    }

    // Contact number - exactly 10 digits
    if (!/^[0-9]{10}$/.test(contact)) {
        document.getElementById("contactError").textContent = "Contact number must be exactly 10 digits.";
        valid = false;
    }

    // Email
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        document.getElementById("emailError").textContent = "Enter a valid email address.";
        valid = false;
    }

    // Location
    if (location === "") {
        document.getElementById("locationError").textContent = "Location cannot be empty.";
        valid = false;
    }

    // Password: at least 8 characters and 1 special character
    if (!/^(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/.test(password)) {
        document.getElementById("passwordError").textContent = 
            "Password must be 8+ characters and contain at least one special character.";
        valid = false;
    }

    // Password Match
    if (password !== confirmPassword) {
        document.getElementById("confirmError").textContent = "Passwords do not match.";
        valid = false;
    }

    return valid;
}
</script>
</head>

<body>

<h2>Charity Registration</h2>

<form name="charityForm" action="<%= request.getContextPath() %>/addCharity" 
      method="post" onsubmit="return validateCharityForm()">

    <label>Name:</label>
    <input type="text" name="name" required>
    <span class="error" id="nameError"></span>

    <label>Registration No:</label>
    <input type="text" name="regno" required>
    <span class="error" id="regError"></span>

    <label>Contact:</label>
    <input type="text" name="contact" required>
    <span class="error" id="contactError"></span>

    <label>Email:</label>
    <input type="email" name="email" required>
    <span class="error" id="emailError"></span>

    <label>Location:</label>
    <input type="text" name="location" required>
    <span class="error" id="locationError"></span>

    <label>Password:</label>
    <input type="password" name="password" required>
    <span class="error" id="passwordError"></span>

    <label>Confirm Password:</label>
    <input type="password" name="confirmPassword" required>
    <span class="error" id="confirmError"></span>

    <input type="submit" value="Register Charity">
</form>

</body>
</html>
