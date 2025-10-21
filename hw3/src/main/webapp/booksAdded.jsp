<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Books Added Successfully - Part 7</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        .container {
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
            max-width: 600px;
            width: 100%;
            text-align: center;
        }
        .success-box {
            background: linear-gradient(135deg, #51cf66 0%, #37b24d 100%);
            color: white;
            padding: 40px;
            border-radius: 8px;
            margin: 20px 0;
        }
        .success-box h1 {
            margin: 0 0 10px 0;
            font-size: 36px;
        }
        .success-box p {
            margin: 0;
            font-size: 18px;
        }
        .link {
            display: inline-block;
            margin-top: 20px;
            padding: 12px 30px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-weight: 600;
            transition: transform 0.2s;
        }
        .link:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 20px rgba(102, 126, 234, 0.4);
        }
        .icon {
            font-size: 64px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="icon">✅</div>
        
        <div class="success-box">
            <h1>${booksAdded} books added successfully</h1>
            <p>Your books have been saved to the database using PreparedStatement</p>
        </div>
        
        <p style="color: #666; margin: 20px 0;">
            All books were inserted into the booksdb database using secure PreparedStatement queries.
        </p>
        
        <a href="askQuantity.jsp" class="link">← Click Here to Go Back to Homepage</a>
    </div>
</body>
</html>
