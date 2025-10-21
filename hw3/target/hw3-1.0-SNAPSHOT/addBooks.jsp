<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Enter Book Details - Part 7</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
        }
        h1 {
            color: #333;
            text-align: center;
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
            padding: 15px;
            text-align: left;
            font-weight: 600;
        }
        td {
            padding: 12px;
            border: 1px solid #e0e0e0;
        }
        tr:nth-child(even) {
            background-color: #f8f9fa;
        }
        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 8px;
            border: 2px solid #e0e0e0;
            border-radius: 4px;
            font-size: 14px;
        }
        input:focus {
            outline: none;
            border-color: #667eea;
        }
        button {
            width: 100%;
            background: linear-gradient(135deg, #51cf66 0%, #37b24d 100%);
            color: white;
            padding: 14px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            margin-top: 20px;
            transition: transform 0.2s;
        }
        button:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 20px rgba(81, 207, 102, 0.4);
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>📚 Enter Book Details</h1>
        <p style="text-align: center; color: #666; margin-bottom: 20px;">
            Adding ${sessionScope.numBooks} book(s) to the database
        </p>
        
        <form action="addBooks" method="post">
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
                    <!-- Generate form rows dynamically based on numBooks -->
                    <c:forEach begin="0" end="${sessionScope.numBooks - 1}" var="i">
                        <tr>
                            <td>
                                <input type="text" 
                                       name="isbn_${i}" 
                                       placeholder="e.g., 123-456-789" 
                                       required />
                            </td>
                            <td>
                                <input type="text" 
                                       name="title_${i}" 
                                       placeholder="e.g., Java Servlets" 
                                       required />
                            </td>
                            <td>
                                <input type="text" 
                                       name="authors_${i}" 
                                       placeholder="e.g., Yusuf Ozbek" 
                                       required />
                            </td>
                            <td>
                                <input type="number" 
                                       name="price_${i}" 
                                       step="0.01" 
                                       placeholder="49.95" 
                                       required />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            <button type="submit">Add Books</button>
        </form>
    </div>
</body>
</html>