<%@ page import="java.util.*, com.src.model.Donor, com.src.service.DonorServiceImpl" %>
<%
    DonorServiceImpl donorService = new DonorServiceImpl();
    List<Donor> donors = donorService.getAllDonors();
%>

<!DOCTYPE html>
<html>
<head>
    <title>All Donors</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin: auto; }
        th, td { border: 1px solid black; padding: 8px; text-align: center; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
<h2 style="text-align:center;">All Donors</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Address</th>
    </tr>
    <%
        for(Donor d : donors) {
    %>
    <tr>
        <td><%= d.getDonorId() %></td>
        <td><%= d.getDonorName() %></td>
        <td><%= d.getEmail() %></td>
        <td><%= d.getPhone() %></td>
        <td><%= d.getAddress() %></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
