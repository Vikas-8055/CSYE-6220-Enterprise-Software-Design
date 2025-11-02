<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Part 9 - Request Scope</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%);
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
            color: #00b894;
            text-align: center;
            margin-bottom: 30px;
        }
        .message-box {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            border-left: 4px solid #00b894;
            margin: 20px 0;
        }
        .info {
            background: #d4edda;
            padding: 15px;
            border-radius: 6px;
            margin: 15px 0;
            border-left: 4px solid #28a745;
        }
        .highlight {
            color: #00b894;
            font-weight: bold;
        }
        button {
            width: 100%;
            background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%);
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
            box-shadow: 0 5px 20px rgba(67, 233, 123, 0.4);
        }
        .badge {
            display: inline-block;
            background: #00b894;
            color: white;
            padding: 5px 12px;
            border-radius: 15px;
            font-size: 0.85em;
            font-weight: 600;
        }
        .warning {
            background: #fff3cd;
            padding: 15px;
            border-radius: 6px;
            margin: 15px 0;
            border-left: 4px solid #ffc107;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🌿 Part 9: Spring IoC - Request Scope</h1>
        
        <div class="message-box">
            <h3>Message from Injected Utility:</h3>
            <p>${message}</p>
        </div>
        
        <div class="info">
            <p><strong>🔧 Injection Type:</strong> <span class="badge">@Autowired</span></p>
            <p><strong>🔍 Bean Scope:</strong> <span class="badge">Request</span></p>
            <p><strong>📊 Object Hash Code:</strong> <span class="highlight">${objectId}</span></p>
        </div>
        
        <div class="warning">
            <p><strong>⚠️ DIFFERENCE FROM PARTS 7 & 8:</strong></p>
            <p>🔄 <strong>Request Scope</strong> creates a NEW object for EACH request!</p>
            <p>📊 Refresh this page multiple times and watch the Object ID <strong>CHANGE</strong> each time.</p>
            <p>🎯 This is the OPPOSITE of singleton behavior!</p>
        </div>
        
        <button onclick="location.reload()">🔄 Refresh to See DIFFERENT Object ID</button>
        
        <p style="text-align: center; margin-top: 20px;">
            <a href="messageAutowired" style="color: #f5576c; text-decoration: none;">← Back to Part 8</a> |
            <a href="message" style="color: #667eea; text-decoration: none;">Back to Part 7</a>
        </p>
    </div>
</body>
</html>
