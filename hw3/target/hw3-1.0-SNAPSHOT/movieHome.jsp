<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Movie Store - Part 6</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #ff6b6b 0%, #ee5a6f 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        .container {
            background: #ffe0e0;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
            max-width: 500px;
            width: 100%;
        }
        h1 {
            color: #c62828;
            text-align: center;
            margin-bottom: 10px;
            font-size: 32px;
        }
        .subtitle {
            text-align: center;
            color: #d32f2f;
            margin-bottom: 30px;
            font-style: italic;
        }
        .menu {
            background: white;
            padding: 20px;
            border-radius: 8px;
            margin: 20px 0;
        }
        label {
            display: block;
            font-weight: 600;
            color: #555;
            margin-bottom: 10px;
        }
        select {
            width: 100%;
            padding: 12px;
            border: 2px solid #e0e0e0;
            border-radius: 6px;
            font-size: 16px;
            margin-bottom: 20px;
        }
        button {
            width: 100%;
            background: linear-gradient(135deg, #ff6b6b 0%, #ee5a6f 100%);
            color: white;
            padding: 14px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: transform 0.2s;
        }
        button:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 20px rgba(255, 107, 107, 0.4);
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🎬 Welcome to our Movie Store</h1>
        <p class="subtitle">Please make your selection below</p>
        
        <div class="menu">
            <form action="movieController" method="get">
                <label for="action">Choose an action:</label>
                <select id="action" name="action" required>
                    <option value="">-- Select --</option>
                    <option value="browse">Browse Movies</option>
                    <option value="addForm">Add Movie to Database</option>
                    <option value="searchForm">Search Movies</option>
                </select>
                
                <button type="submit">Send</button>
            </form>
        </div>
    </div>
</body>
</html>