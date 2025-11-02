<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Movie Store - Part 4</title>
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
        .menu a {
            display: block;
            padding: 15px;
            margin: 10px 0;
            background: linear-gradient(135deg, #ff6b6b 0%, #ee5a6f 100%);
            color: white;
            text-decoration: none;
            border-radius: 6px;
            text-align: center;
            font-weight: 600;
            transition: transform 0.2s;
        }
        .menu a:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 20px rgba(255, 107, 107, 0.4);
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🎬 Movie Store - Spring MVC</h1>
        <p class="subtitle">Part 4: ControllerClassNameHandlerMapping</p>
        
        <div class="menu">
            <a href="browse">Browse Movies</a>
            <a href="addMovieForm">Add New Movie</a>
            <a href="search">Search Movies</a>
        </div>
    </div>
</body>
</html>