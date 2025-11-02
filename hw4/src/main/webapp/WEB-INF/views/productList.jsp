<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart - Part 6</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
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
            color: #f5576c;
            text-align: center;
            margin-bottom: 10px;
        }
        .subtitle {
            text-align: center;
            color: #666;
            margin-bottom: 30px;
        }
        .badge {
            background: #f5576c;
            color: white;
            padding: 5px 12px;
            border-radius: 15px;
            font-size: 0.85em;
            font-weight: 600;
        }
        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
            margin: 30px 0;
        }
        .product-card {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            transition: transform 0.3s;
        }
        .product-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 20px rgba(245, 87, 108, 0.2);
        }
        .product-name {
            color: #2c3e50;
            font-size: 18px;
            font-weight: 600;
            margin-bottom: 10px;
        }
        .product-category {
            color: #888;
            font-size: 14px;
            margin-bottom: 10px;
        }
        .product-price {
            color: #f5576c;
            font-size: 24px;
            font-weight: bold;
            margin: 15px 0;
        }
        .btn {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 6px;
            font-size: 14px;
            font-weight: 600;
            cursor: pointer;
            transition: transform 0.2s;
        }
        .btn-add {
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
            color: white;
        }
        .btn-add:hover {
            transform: translateY(-2px);
        }
        .btn-cart {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 15px 30px;
            border-radius: 25px;
            text-decoration: none;
            display: inline-block;
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🛒 Shopping Cart - Spring MVC</h1>
        <p class="subtitle">Part 6: Annotated Controllers with @Controller and @RequestMapping</p>
        
        <div style="text-align: center; margin-bottom: 20px;">
            <a href="viewCart" class="btn-cart">🛒 View Cart</a>
        </div>
        
        <h2 style="color: #2c3e50; margin-bottom: 20px;">Available Products</h2>
        
        <div class="product-grid">
            <c:forEach var="product" items="${products}">
                <div class="product-card">
                    <div class="product-name">${product.name}</div>
                    <div class="product-category">📦 ${product.category}</div>
                    <div class="product-price">
                        $<fmt:formatNumber value="${product.price}" pattern="#,##0.00" />
                    </div>
                    <form action="addToCart" method="get" style="margin-top: 15px;">
                        <input type="hidden" name="productId" value="${product.id}">
                        <input type="hidden" name="quantity" value="1">
                        <button type="submit" class="btn btn-add">➕ Add to Cart</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>