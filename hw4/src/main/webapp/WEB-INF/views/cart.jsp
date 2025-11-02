<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Cart - Part 6</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
        }
        h1 {
            color: #667eea;
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
            padding: 12px;
            text-align: left;
        }
        td {
            padding: 12px;
            border-bottom: 1px solid #e0e0e0;
        }
        .empty-cart {
            text-align: center;
            padding: 40px;
            color: #888;
            font-size: 18px;
        }
        .total-row {
            background: #f8f9fa;
            font-weight: bold;
            font-size: 18px;
        }
        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 6px;
            font-weight: 600;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
        }
        .btn-remove {
            background: #ff7675;
            color: white;
            font-size: 14px;
        }
        .btn-remove:hover {
            background: #d63031;
        }
        .btn-continue {
            background: #636e72;
            color: white;
            margin-right: 10px;
        }
        .btn-checkout {
            background: linear-gradient(135deg, #00b894 0%, #00cec9 100%);
            color: white;
        }
        .button-group {
            text-align: center;
            margin-top: 30px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🛒 Your Shopping Cart</h1>
        
        <c:choose>
            <c:when test="${empty cartItems}">
                <div class="empty-cart">
                    😕 Your cart is empty
                    <br><br>
                    <a href="shop" class="btn btn-continue">← Continue Shopping</a>
                </div>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>Product</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Subtotal</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${cartItems}">
                            <tr>
                                <td>
                                    <strong>${item.product.name}</strong><br>
                                    <small style="color: #888;">${item.product.category}</small>
                                </td>
                                <td>$<fmt:formatNumber value="${item.product.price}" pattern="#,##0.00" /></td>
                                <td>${item.quantity}</td>
                                <td>$<fmt:formatNumber value="${item.subtotal}" pattern="#,##0.00" /></td>
                                <td>
                                    <a href="removeFromCart?productId=${item.product.id}" 
                                       class="btn btn-remove"
                                       onclick="return confirm('Remove ${item.product.name}?')">Remove</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr class="total-row">
                            <td colspan="3" style="text-align: right;">Total:</td>
                            <td colspan="2">$<fmt:formatNumber value="${total}" pattern="#,##0.00" /></td>
                        </tr>
                    </tbody>
                </table>
                
                <div class="button-group">
                    <a href="shop" class="btn btn-continue">← Continue Shopping</a>
                    <a href="checkout" class="btn btn-checkout">✓ Checkout</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>