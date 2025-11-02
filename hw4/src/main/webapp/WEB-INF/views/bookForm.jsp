<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Enter Book Details - Part 5</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 40px 20px;
        }
        .container {
            max-width: 900px;
            margin: 0 auto;
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
        }
        h1 {
            color: #667eea;
            text-align: center;
            margin-bottom: 10px;
        }
        .subtitle {
            text-align: center;
            color: #666;
            margin-bottom: 30px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 12px;
            text-align: left;
        }
        td {
            padding: 10px;
        }
        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 8px;
            border: 2px solid #e0e0e0;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input:focus {
            outline: none;
            border-color: #667eea;
        }
        button {
            width: 100%;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 14px;
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
        <h1>📚 Enter Book Details</h1>
        <p class="subtitle">Adding ${numBooks} book(s) to the database</p>
        
        <form action="addBooks" method="post">
            <input type="hidden" name="numBooks" value="${numBooks}">
            
            <table>
                <thead>
                    <tr>
                        <th>ISBN</th>
                        <th>Book Title</th>
                        <th>Authors</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach begin="0" end="${numBooks - 1}" var="i">
                        <tr>
                            <td><input type="text" name="isbn_${i}" placeholder="e.g., 123-456-789" required></td>
                            <td><input type="text" name="title_${i}" placeholder="e.g., Java Servlets" required></td>
                            <td><input type="text" name="authors_${i}" placeholder="e.g., Yusuf Ozbek" required></td>
                            <td><input type="number" step="0.01" name="price_${i}" placeholder="49.95" required></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            <button type="submit">Add Books</button>
        </form>
    </div>
</body>
</html>