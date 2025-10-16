<%@ page language="java" import="com.src.model.*, java.util.*" 
    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Get the donor list from the request attribute set by servlet
    List<Donor> donors = (List<Donor>) request.getAttribute("donor");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Donor</title>
</head>
<body>
<h2>Donor List</h2>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Contact</th>
        <th>Action</th>
    </tr>
    <% if (donors != null) {
           for (Donor d : donors) { %>
    <tr>
        <td><%= d.getDonorId() %></td>
        <td><%= d.getDonorName() %></td>
        <td><%= d.getEmail() %></td>
        <td><%= d.getPhone() %></td>
        <td>
            <form action="<%= request.getContextPath() %>/deleteDonor" method="post" 
                  onsubmit="return confirm('Are you sure you want to delete this donor?');">
                <input type="hidden" name="donorId" value="<%= d.getDonorId() %>">
                <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
    <%   } 
       } else { %>
    <tr><td colspan="5">No donors found.</td></tr>
    <% } %>
</table>
</body>
</html>
