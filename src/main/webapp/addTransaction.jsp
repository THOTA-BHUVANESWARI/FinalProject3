<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Transaction</title>
    <style>
        /* Reset & layout */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #f9f9f9, #e6f2ff);
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .transaction-container {
            background: #fff;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            width: 450px;
        }

        h2 {
            text-align: center;
            color: #1b4b91;
            margin-bottom: 25px;
            font-weight: 600;
        }

        .form-group {
            margin-bottom: 18px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: 500;
            color: #333;
        }

        input[type="text"],
        input[type="number"],
        input[type="file"],
        input[type="date"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 15px;
            transition: 0.3s;
        }

        input:focus {
            border-color: #1b4b91;
            outline: none;
            box-shadow: 0 0 4px rgba(27, 75, 145, 0.3);
        }

        .btn {
            display: block;
            width: 100%;
            padding: 12px;
            background-color: #1b4b91;
            color: #fff;
            font-size: 16px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: 0.3s;
        }

        .btn:hover {
            background-color: #153a6f;
        }

        .readonly {
            background: #f4f4f4;
        }

        .note {
            font-size: 13px;
            color: #777;
            margin-top: 8px;
        }
    </style>
</head>
<body>
    <div class="transaction-container">
        <h2>Record a Donation</h2>
        <form action="TransactionServlet" method="post" enctype="multipart/form-data">
            
            <input type="hidden" name="donorId" value="<%= request.getParameter("donorId") %>">
            <input type="hidden" name="campaignId" value="<%= request.getParameter("campaignId") %>">
            <input type="hidden" name="charityId" value="<%= request.getParameter("charityId") %>">

            <div class="form-group">
                <label>Donor ID</label>
                <input type="text" value="<%= request.getParameter("donorId") %>" readonly class="readonly">
            </div>

            <div class="form-group">
                <label>Charity ID</label>
                <input type="text" value="<%= request.getParameter("charityId") %>" readonly class="readonly">
            </div>

            <div class="form-group">
                <label>Campaign ID</label>
                <input type="text" value="<%= request.getParameter("campaignId") %>" readonly class="readonly">
            </div>

            <div class="form-group">
                <label>Amount (₹)</label>
                <input type="number" name="amount" step="0.01" required placeholder="Enter donation amount">
            </div>

            <div class="form-group">
                <label>PAN Number</label>
                <input type="text" name="panNumber" maxlength="10" required placeholder="Enter 10-character PAN">
            </div>

            <button type="submit" class="btn">Submit Transaction</button>
        </form>
    </div>
</body>
</html>
