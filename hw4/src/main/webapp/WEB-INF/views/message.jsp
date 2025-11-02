<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Part 7 - Spring IoC Singleton</title>
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
        }
        h1 {
            color: #667eea;
            text-align: center;
            margin-bottom: 30px;
        }
        .message-box {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            border-left: 4px solid #667eea;
            margin: 20px 0;
        }
        .info {
            background: #e3f2fd;
            padding: 15px;
            border-radius: 6px;
            margin: 15px 0;
        }
        .highlight {
            color: #667eea;
            font-weight: bold;
        }
        button {
            width: 100%;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
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
            box-shadow: 0 5px 20px rgba(102, 126, 234, 0.4);
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🌱 Part 7: Spring IoC - Singleton Scope</h1>
        
        <div class="message-box">
            <h3>Message from Injected Utility:</h3>
            <p>${message}</p>
        </div>
        
        <div class="info">
            <p><strong>🔍 Bean Scope:</strong> Singleton</p>
            <p><strong>📊 Object Hash Code:</strong> <span class="highlight">${objectId}</span></p>
            <p><strong>💡 Note:</strong> Refresh this page multiple times and check the console. 
               The Object ID should be the SAME every time (singleton behavior).</p>
        </div>
        
        <button onclick="location.reload()">🔄 Refresh to Test Singleton</button>
    </div>
</body>
</html>
