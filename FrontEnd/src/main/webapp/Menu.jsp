
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import ="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Financial Portfolio</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700&display=swap" rel="stylesheet">
    <style>
        body { 
            font-family: 'Open Sans', sans-serif; 
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
            color: #333;
        }
        .container { 
            max-width: 800px; 
            margin: 20px auto; 
            padding: 20px; 
            background-color: #fff; 
            border-radius: 8px; 
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .header {
            color:#4CAF50;
            text-align: center;
            margin-bottom: 40px;
        }
        .balance {
            font-size: 36px;
            font-weight: 600;
            text-align: center;
            margin-bottom: 40px;
        }
        .account-section {
            display: flex;
            justify-content: space-between;
            margin-bottom: 40px;
        }
        .account {
            flex: 1;
            text-align: center;
            padding: 20px;
            margin: 0 10px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
        }
        .account:hover {
            transform: translateY(-5px);
            cursor: pointer;
        }
        .account h3 {
            margin-bottom: 15px;
            font-weight: 600;
        }
        .account p {
            margin-bottom: 15px;
            color: #666;
        }
        .account img {
            width: 100%;
            height: auto;
            border-radius: 4px;
        }
        .footer {
            text-align: center;
            margin-top: 20px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #333366;
        }
        
            
        
    </style>
</head>
<body>
    <%
        String userName = (String) session.getAttribute("userName");
        Double balance = (Double) session.getAttribute("Balance");
        balance = balance != null ? balance : 0.0; // Ensure balance is not null
    %>
    <div class="container">
        <div class="header">
            <h1>Financial Portfolio Menu</h1>
            <div class="welcome-text">Welcome to your Profile, <%= userName %>!</div>
        </div>
        
        <div class="balance">10000</div>
        
        <div class="account-section">
            <div class="account" onclick="location.href='tfsaTrade';">
                <h3>TFSA Account</h3>
                <p>Tax-free savings</p>
                <img src="Images/Chart1.png" alt="TFSA Performance Chart">
                <div>Balance: $40,500.00</div>
            </div>
            <div class="account" onclick="location.href='cryptoTrade';">
                <h3>Crypto Account</h3>
                <p>Your digital assets</p>
                 <img src="Images/Chart2.png" alt="Crypto Performance Chart">
                <div>Balance: $18.52</div>
            </div>
        </div>

        <div class="footer">
            <a href="managePortfolio" class="btn">Manage Portfolio</a>
            <a href="login.html" class="btn">Logout</a>
        </div>
    </div>

    <script>
        // If you prefer not to use the 'onclick' attribute directly in the HTML,
        // you can assign click event listeners to your elements like this:
        document.addEventListener('DOMContentLoaded', function() {
            var tfsaAccount = document.querySelector('.account.tfsa');
            var cryptoAccount = document.querySelector('.account.crypto');
            
            if (tfsaAccount) {
                tfsaAccount.addEventListener('click', function() {
                    window.location.href = 'tfsaTrade';
                });
            }
            
            if (cryptoAccount) {
                cryptoAccount.addEventListener('click', function() {
                    window.location.href = 'cryptoTrade';
                });
            }
        });
    </script>
</body>
</html>
