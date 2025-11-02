<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Movie List - Spring MVC</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%);
            min-height: 100vh;
            padding: 20px;
        }
        .container {
            max-width: 1000px;
            margin: 0 auto;
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
        }
        h1 {
            color: #00b894;
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
            margin-bottom: 30px;
        }
        th {
            background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%);
            color: white;
            padding: 15px;
            text-align: left;
            font-weight: 600;
        }
        td {
            padding: 12px 15px;
            border-bottom: 1px solid #e0e0e0;
        }
        tr:hover {
            background: #f5f5f5;
        }
        .actions {
            display: flex;
            gap: 10px;
        }
        .btn {
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            font-weight: 600;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            transition: transform 0.2s;
        }
        .btn:hover {
            transform: translateY(-2px);
        }
        .btn-edit {
            background: #74b9ff;
            color: white;
        }
        .btn-delete {
            background: #ff7675;
            color: white;
        }
        .btn-back {
            background: #636e72;
            color: white;
            display: inline-block;
            margin-top: 20px;
            padding: 12px 24px;
        }
        .no-movies {
            text-align: center;
            padding: 40px;
            color: #888;
            font-size: 18px;
        }
        .search-info {
            background: #fff3cd;
            padding: 15px;
            border-radius: 6px;
            margin-bottom: 20px;
            color: #856404;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🎬 Movie Database</h1>
        
        <c:if test="${not empty keyword}">
            <div class="search-info">
                🔍 Search results for: <strong>"${keyword}"</strong>
            </div>
        </c:if>
        
        <c:if test="${empty keyword}">
            <p class="subtitle">Browse all movies in our collection</p>
        </c:if>
        
        <c:choose>
            <c:when test="${empty movies}">
                <div class="no-movies">
                    😕 No movies found
                    <c:if test="${not empty keyword}">
                        <br>Try a different search term
                    </c:if>
                </div>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Genre</th>
                            <th>Release Year</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="movie" items="${movies}">
                            <tr>
                                <td>${movie.id}</td>
                                <td>${movie.title}</td>
                                <td>${movie.genre}</td>
                                <td>${movie.releaseYear}</td>
                                <td>
                                    <div class="actions">
                                        <a href="editMovie?id=${movie.id}" class="btn btn-edit">Edit</a>
                                        <a href="deleteMovie?id=${movie.id}" 
                                           class="btn btn-delete"
                                           onclick="return confirm('Delete ${movie.title}?')">Delete</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
        
        <a href="movieHome" class="btn btn-back">⬅ Back to Home</a>
    </div>
</body>
</html>