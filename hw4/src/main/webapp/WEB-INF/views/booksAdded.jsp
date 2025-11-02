<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Books Added Successfully</title>
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
            max-width: 500px;
            width: 100%;
            text-align: center;
        }
        .success-icon {
            font-size: 80px;
            margin-bottom: 20px;
        }
        h1 {
            color: #4caf50;
            margin-bottom: 20px;
        }
        .message {
            background: #d4edda;
            padding: 20px;
            border-radius: 8px;
            margin: 20px 0;
            color: #155724;
            border-left: 4px solid #28a745;
        }
        button {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 14px 30px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            margin-top: 20px;
        }
        button:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 20px rgba(102, 126, 234, 0.4);
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="success-icon">✅</div>
        
        <h1>${booksAdded} books added successfully</h1>
        
        <div class="message">
            <p>Your books have been saved to the database using PreparedStatement.</p>
            <p style="margin-top: 10px;">All books were inserted into the booksdb database using secure PreparedStatement queries.</p>
        </div>
        
        <button onclick="window.location.href='askQuantity'">← Add More Books</button>
    </div>
</body>
</html>