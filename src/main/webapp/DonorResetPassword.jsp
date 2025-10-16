<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Reset Password</title></head>
<body>
<h2>Reset Your Password</h2>

<% String error = request.getParameter("error"); 
   if (error != null) { %>
   <p style="color:red;"><%= error %></p>
<% } %>

<% String msg = request.getParameter("msg"); 
   if (msg != null) { %>
   <p style="color:green;"><%= msg %></p>
<% } %>

<form action="DonorResetPasswordServlet" method="post">
    Email: <input type="email" name="email" required><br><br>
    New Password: <input type="password" name="newPassword" required><br><br>
    Confirm Password: <input type="password" name="confirmPassword" required><br><br>
    <input type="submit" value="Reset Password">
</form>
</body>
</html>
