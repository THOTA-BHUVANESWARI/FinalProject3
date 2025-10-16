<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Donation & Charity Management - Home</title>
<style>
/* General Body */
body {
    font-family: 'Nunito Sans', Arial, sans-serif;
    background: #e6f2f0; /* soft greenish background */
    margin: 0;
    padding: 0;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

/* Container */
.container {
    width: 400px;
    background: #ffffff;
    padding: 40px 30px;
    text-align: center;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.1);
}

/* Heading */
.container h1 {
    font-size: 24px;
    color: #2d6a4f; /* soft green */
    margin-bottom: 30px;
}

/* Links / Buttons */
a {
    display: block;
    margin: 15px 0;
    padding: 12px;
    background-color: #52b788; /* light green */
    color: white;
    text-decoration: none;
    border-radius: 8px;
    font-weight: 600;
    transition: all 0.3s ease;
}

a:hover {
    background-color: #40916c; /* darker green on hover */
    transform: translateY(-2px);
}

/* Responsive */
@media (max-width: 450px) {
    .container {
        width: 90%;
        padding: 30px 20px;
    }
    .container h1 {
        font-size: 20px;
    }
}
</style>
</head>
<body>

<div class="container">
    <h1>Welcome to Donation & Charity Management</h1>
    <a href="Donormain.jsp">Donor Login</a>
    <a href="CharityLogin.jsp">Charity Login</a>
</div>
</body>
</html>
