<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Movie - Spring MVC</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #fa709a 0%, #fee140 100%);
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
            color: #fa709a;
            text-align: center;
            margin-bottom: 30px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            font-weight: 600;
            color: #555;
            margin-bottom: 8px;
        }
        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 12px;
            border: 2px solid #e0e0e0;
            border-radius: 6px;
            font-size: 16px;
            box-sizing: border-box;
        }
        input:focus {
            outline: none;
            border-color: #fa709a;
        }
        input[readonly] {
            background: #f5f5f5;
            color: #888;
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
            background: linear-gradient(135deg, #fa709a 0%, #fee140 100%);
            color: white;
        }
        button[type="button"] {
            background: #e0e0e0;
            color: #555;
        }
        button:hover {
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>✏️ Edit Movie</h1>
        
        <form action="updateMovie" method="post">
            <input type="hidden" name="id" value="${movie.id}">
            
            <div class="form-group">
                <label for="id">Movie ID:</label>
                <input type="text" id="id" value="${movie.id}" readonly>
            </div>
            
            <div class="form-group">
                <label for="title">Movie Title:</label>
                <input type="text" id="title" name="title" 
                       value="${movie.title}" required>
            </div>
            
            <div class="form-group">
                <label for="genre">Genre:</label>
                <input type="text" id="genre" name="genre" 
                       value="${movie.genre}" required>
            </div>
            
            <div class="form-group">
                <label for="releaseYear">Release Year:</label>
                <input type="number" id="releaseYear" name="releaseYear" 
                       value="${movie.releaseYear}" 
                       min="1900" max="2025" required>
            </div>
            
            <div class="button-group">
                <button type="submit">Update Movie</button>
                <button type="button" onclick="window.location.href='browse'">
                    Cancel
                </button>
            </div>
        </form>
    </div>
</body>
</html>