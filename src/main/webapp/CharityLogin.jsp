<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Charity Login</title>
<style>
/* General Body */
body {
    font-family: 'Nunito Sans', Arial, sans-serif;
    background: #e6f2f0; /* soft greenish background */
    margin: 0;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

/* Form Container */
form {
    width: 350px;
    padding: 40px 30px;
    background: #ffffff;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.1);
    text-align: center;
}

/* Heading */
h2 {
    color: #2d6a4f; /* soft green */
    margin-bottom: 25px;
    font-size: 24px;
}

/* Input Fields */
input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 15px;
    margin: 10px 0;
    border: 1px solid #ccc;
    border-radius: 8px;
    font-size: 14px;
    transition: border-color 0.3s, box-shadow 0.3s;
}

input[type=text]:focus, input[type=password]:focus {
    outline: none;
    border-color: #52b788; /* light green */
    box-shadow: 0 0 5px rgba(82, 183, 136, 0.5);
}

/* Submit Button */
input[type=submit] {
    background-color: #52b788; /* light green */
    color: white;
    border: none;
    padding: 12px;
    width: 100%;
    border-radius: 8px;
    cursor: pointer;
    font-weight: 600;
    font-size: 16px;
    margin-top: 15px;
    transition: background-color 0.3s, transform 0.2s;
}

input[type=submit]:hover {
    background-color: #40916c; /* darker green on hover */
    transform: translateY(-2px);
}

/* Links */
a {
    display: block;
    margin-top: 12px;
    color: #52b788;
    text-decoration: none;
    font-size: 14px;
    transition: color 0.3s;
}

a:hover {
    color: #2d6a4f;
}

/* Responsive */
@media (max-width: 400px) {
    form {
        width: 90%;
        padding: 30px 20px;
    }
    h2 {
        font-size: 20px;
    }
}
</style>
</head>
<body>

<form action="loginCharity" method="post">
    <h2>Charity Login</h2>

    <input type="text" name="email" placeholder="Email" required>
    <input type="password" name="password" placeholder="Password" required>

    <input type="submit" value="Login">
    
    <a href="ForgotPasswordCharity.jsp">Forgot Password?</a>
    <a href="addCharity.jsp">New User? Register Here</a>
</form>

</body>
</html>
