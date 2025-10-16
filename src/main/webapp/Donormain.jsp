<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Donor Login</title>
<style>
/* Body: center content and gradient background */
body {
    font-family: 'Poppins', Arial, sans-serif;
    background: linear-gradient(135deg, #e3f2fd, #e8f5e9);
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}

/* Modal card */
.modal {
    background: white;
    padding: 40px 30px;
    border-radius: 16px;
    width: 360px;
    text-align: center;
    box-shadow: 0 8px 25px rgba(0,0,0,0.15);
    animation: fadeIn 0.8s ease;
}

@keyframes fadeIn {
    from { opacity: 0; transform: translateY(-20px); }
    to { opacity: 1; transform: translateY(0); }
}

/* Heading */
.modal h2 {
    margin-bottom: 25px;
    color: #1565c0;
    font-size: 24px;
    font-weight: 600;
}

/* Input fields */
input[type=email],
input[type=password] {
    width: 90%;
    padding: 12px;
    margin: 12px 0;
    border-radius: 8px;
    border: 1px solid #ccc;
    font-size: 14px;
    transition: border-color 0.3s, box-shadow 0.3s;
}

input[type=email]:focus,
input[type=password]:focus {
    border-color: #43a047;
    box-shadow: 0 0 8px rgba(67,160,71,0.2);
    outline: none;
}

/* Submit button */
button {
    width: 95%;
    padding: 12px;
    margin-top: 10px;
    border: none;
    border-radius: 8px;
    background-color: #43a047;
    color: white;
    font-size: 16px;
    font-weight: 500;
    cursor: pointer;
    transition: background-color 0.3s, transform 0.2s;
}

button:hover {
    background-color: #2e7d32;
    transform: scale(1.03);
}

/* Links */
.modal p {
    margin: 12px 0;
    font-size: 14px;
    color: #555;
}

.modal a {
    text-decoration: none;
    color: #1565c0;
    font-weight: 500;
    transition: color 0.3s, transform 0.2s;
}

.modal a:hover {
    color: #0d47a1;
    transform: scale(1.05);
}
</style>
</head>
<body>

<div class="modal">
    <h2>Donor Login</h2>
    <form action="loginDonor" method="post">
        <input type="email" name="email" placeholder="Email" required><br>
        <input type="password" name="password" placeholder="Password" required><br>
        <button type="submit">Login</button>
    </form>
    <p>New Donor? <a href="addDonor.jsp">Register Here</a></p>
    <p><a href="donorForgotPassword.jsp">Forgot Password?</a></p>
</div>

</body>
</html>
