<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Part 8 - @Autowired Singleton</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
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
        }
        h1 {
            color: #f5576c;
            text-align: center;
            margin-bottom: 30px;
        }
        .message-box {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            border-left: 4px solid #f5576c;
            margin: 20px 0;
        }
        .info {
            background: #fff3cd;
            padding: 15px;
            border-radius: 6px;
            margin: 15px 0;
        }
        .highlight {
            color: #f5576c;
            font-weight: bold;
        }
        button {
            width: 100%;
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
            color: white;
            padding: 12px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            margin-top: 20px;
        }
        button:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 20px rgba(245, 87, 108, 0.4);
        }
        .badge {
            display: inline-block;
            background: #f5576c;
            color: white;
            padding: 5px 12px;
            border-radius: 15px;
            font-size: 0.85em;
            font-weight: 600;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🌸 Part 8: Spring IoC - @Autowired</h1>
        
        <div class="message-box">
            <h3>Message from Injected Utility:</h3>
            <p>${message}</p>
        </div>
        
        <div class="info">
            <p><strong>🔧 Injection Type:</strong> <span class="badge">@Autowired</span></p>
            <p><strong>🔍 Bean Scope:</strong> Singleton</p>
            <p><strong>📊 Object Hash Code:</strong> <span class="highlight">${objectId}</span></p>
            <p><strong>💡 Difference from Part 7:</strong> Uses @Autowired annotation instead of XML setter injection. No setter method needed!</p>
            <p><strong>🎯 Notice:</strong> Object ID should be the SAME as Part 7 (same MessageUtility bean reused!)</p>
        </div>
        
        <button onclick="location.reload()">🔄 Refresh to Test Singleton</button>
        
        <p style="text-align: center; margin-top: 20px;">
            <a href="message" style="color: #667eea; text-decoration: none;">← Back to Part 7</a>
        </p>
    </div>
</body>
</html>