<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Forgot Password</title></head>
<body>
<h2>Donor Forgot Password</h2>
<form action="DonorResetPassword.jsp" method="post">
    Enter your registered Email: <br>
    <input type="email" name="email" required><br><br>
    <input type="submit" value="Send Reset Link">
</form>
</body>
</html>
