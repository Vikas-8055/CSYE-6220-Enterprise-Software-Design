<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Movies - Spring MVC</title>
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
            max-width: 500px;
            width: 100%;
        }
        h1 {
            color: #f5576c;
            text-align: center;
            margin-bottom: 30px;
        }
        .search-box {
            margin-bottom: 20px;
        }
        label {
            display: block;
            font-weight: 600;
            color: #555;
            margin-bottom: 8px;
        }
        input[type="text"] {
            width: 100%;
            padding: 12px;
            border: 2px solid #e0e0e0;
            border-radius: 6px;
            font-size: 16px;
            box-sizing: border-box;
        }
        input:focus {
            outline: none;
            border-color: #f5576c;
        }
        .button-group {
            display: flex;
            gap: 10px;
            margin-top: 30px;
        }
        button {
            flex: 1;
            padding: 14px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: transform 0.2s;
        }
        button[type="submit"] {
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
            color: white;
        }
        button[type="button"] {
            background: #e0e0e0;
            color: #555;
        }
        button:hover {
            transform: translateY(-2px);
        }
        .hint {
            font-size: 14px;
            color: #888;
            margin-top: 10px;
            font-style: italic;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🔍 Search Movies</h1>
        
        <form action="searchMovie" method="get">
            
            <div class="search-box">
                <label for="keyword">Enter search keyword:</label>
                <input type="text" id="keyword" name="keyword" 
                       placeholder="Search by title or genre..." required>
                <p class="hint">💡 Tip: Search works for both movie titles and genres</p>
            </div>
            
            <div class="button-group">
                <button type="submit">Search</button>
                <button type="button" onclick="window.location.href='movieHome'">
                    Cancel
                </button>
            </div>
        </form>
    </div>
</body>
</html>