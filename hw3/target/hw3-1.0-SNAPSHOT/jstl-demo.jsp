<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSTL Tags Demonstration - Part 3</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            padding: 20px;
            margin: 0;
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
            border-bottom: 3px solid #667eea;
            padding-bottom: 15px;
            margin-bottom: 30px;
        }
        .section {
            margin: 30px 0;
            padding: 20px;
            background: #f8f9fa;
            border-radius: 8px;
            border-left: 4px solid #667eea;
        }
        .section h2 {
            color: #667eea;
            margin-bottom: 15px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 15px 0;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            font-weight: 600;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .highlight {
            background-color: #fff3cd;
            padding: 10px;
            border-radius: 4px;
            margin: 10px 0;
        }
        .badge {
            display: inline-block;
            padding: 5px 10px;
            background: #667eea;
            color: white;
            border-radius: 4px;
            font-size: 12px;
            margin: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🏷️ JSTL Tags Demonstration - Part 3</h1>
        <p style="text-align: center; color: #666; margin-bottom: 30px;">
            Demonstrating at least 3 tags from each JSTL tag library group
        </p>

        <!-- ============================================ -->
        <!-- SECTION 1: CORE TAGS (c:) -->
        <!-- ============================================ -->
        <div class="section">
            <h2>1️⃣ Core Tags (c: prefix)</h2>
            
            <!-- c:set - Set a variable -->
            <c:set var="studentName" value="Vikas Meneni" />
            <c:set var="nuid" value="002309537" />
            <c:set var="course" value="Enterprise Software Design" />
            
            <!-- c:out - Output a value -->
            <p><strong>Tag 1 - c:set and c:out:</strong></p>
            <p>Student: <c:out value="${studentName}" /></p>
            <p>NUID: <c:out value="${nuid}" /></p>
            <p>Course: <c:out value="${course}" /></p>
            
            <!-- c:if - Conditional -->
            <p><strong>Tag 2 - c:if:</strong></p>
            <c:set var="score" value="95" />
            <c:if test="${score >= 90}">
                <div class="highlight">
                    ✅ Excellent! Score ${score} is an A grade!
                </div>
            </c:if>
            
            <!-- c:forEach - Loop -->
            <p><strong>Tag 3 - c:forEach:</strong></p>
            <c:set var="technologies" value="JSP,JSTL,Servlets,JDBC,MVC" />
            <p>Technologies covered:</p>
            <c:forEach items="${fn:split(technologies, ',')}" var="tech" varStatus="status">
                <span class="badge">${status.count}. ${tech}</span>
            </c:forEach>
            
            <!-- c:choose, c:when, c:otherwise - Multiple conditions -->
            <p><strong>Tag 4 - c:choose:</strong></p>
            <c:set var="grade" value="A" />
            <c:choose>
                <c:when test="${grade == 'A'}">
                    <p style="color: green;">🌟 Outstanding performance!</p>
                </c:when>
                <c:when test="${grade == 'B'}">
                    <p style="color: blue;">Good job!</p>
                </c:when>
                <c:otherwise>
                    <p style="color: orange;">Keep trying!</p>
                </c:otherwise>
            </c:choose>
            
            <!-- c:forTokens - Loop with delimiter -->
            <p><strong>Tag 5 - c:forTokens:</strong></p>
            <c:set var="colors" value="Red-Green-Blue-Yellow" />
            <p>Colors: 
            <c:forTokens items="${colors}" delims="-" var="color">
                <span style="padding: 3px 8px; margin: 2px; background: #e0e0e0; border-radius: 3px;">${color}</span>
            </c:forTokens>
            </p>
        </div>

        <!-- ============================================ -->
        <!-- SECTION 2: FORMATTING TAGS (fmt:) -->
        <!-- ============================================ -->
        <div class="section">
            <h2>2️⃣ Formatting Tags (fmt: prefix)</h2>
            
            <!-- Set current date -->
            <jsp:useBean id="now" class="java.util.Date" />
            
            <!-- fmt:formatDate - Format dates -->
            <p><strong>Tag 1 - fmt:formatDate:</strong></p>
            <p>Full Date: <fmt:formatDate value="${now}" pattern="EEEE, MMMM dd, yyyy" /></p>
            <p>Short Date: <fmt:formatDate value="${now}" pattern="MM/dd/yyyy" /></p>
            <p>Time: <fmt:formatDate value="${now}" pattern="hh:mm:ss a" /></p>
            
            <!-- fmt:formatNumber - Format numbers -->
            <p><strong>Tag 2 - fmt:formatNumber:</strong></p>
            <c:set var="price" value="1299.99" />
            <c:set var="discount" value="0.15" />
            <p>Price: <fmt:formatNumber value="${price}" type="currency" /></p>
            <p>Discount: <fmt:formatNumber value="${discount}" type="percent" /></p>
            <p>Savings: <fmt:formatNumber value="${price * discount}" type="currency" /></p>
            
            <!-- fmt:setLocale - Set locale -->
            <p><strong>Tag 3 - fmt:setLocale:</strong></p>
            <fmt:setLocale value="en_US" />
            <p>Locale set to: en_US</p>
            <p>Currency format: <fmt:formatNumber value="9999.99" type="currency" /></p>
            
            <!-- fmt:parseNumber - Parse number from string -->
            <p><strong>Tag 4 - fmt:parseNumber:</strong></p>
            <c:set var="priceString" value="1,234.56" />
            <fmt:parseNumber var="parsedPrice" value="${priceString}" />
            <p>String "${priceString}" parsed as: ${parsedPrice}</p>
        </div>

        <!-- ============================================ -->
        <!-- SECTION 3: FUNCTIONS (fn:) -->
        <!-- ============================================ -->
        <div class="section">
            <h2>3️⃣ Functions (fn: prefix)</h2>
            
            <c:set var="message" value="JavaServer Pages Standard Tag Library" />
            
            <!-- fn:length - Get length -->
            <p><strong>Function 1 - fn:length:</strong></p>
            <p>Message: "${message}"</p>
            <p>Length: ${fn:length(message)} characters</p>
            
            <!-- fn:toUpperCase - Convert to uppercase -->
            <p><strong>Function 2 - fn:toUpperCase:</strong></p>
            <p>Uppercase: ${fn:toUpperCase(message)}</p>
            
            <!-- fn:toLowerCase - Convert to lowercase -->
            <p><strong>Function 3 - fn:toLowerCase:</strong></p>
            <p>Lowercase: ${fn:toLowerCase(message)}</p>
            
            <!-- fn:substring - Get substring -->
            <p><strong>Function 4 - fn:substring:</strong></p>
            <p>First 10 chars: "${fn:substring(message, 0, 10)}"</p>
            
            <!-- fn:contains - Check if contains -->
            <p><strong>Function 5 - fn:contains:</strong></p>
            <c:if test="${fn:contains(message, 'Tag')}">
                <p>✅ The message contains the word "Tag"</p>
            </c:if>
            
            <!-- fn:replace - Replace text -->
            <p><strong>Function 6 - fn:replace:</strong></p>
            <p>Modified: ${fn:replace(message, "Library", "Collection")}</p>
        </div>

        <!-- ============================================ -->
        <!-- SECTION 4: DEMONSTRATION TABLE -->
        <!-- ============================================ -->
        <div class="section">
            <h2>4️⃣ Combined Example - Student Records</h2>
            
            <!-- Create a list of students using c:set -->
            <c:set var="student1" value="John,Doe,Computer Science,3.8" />
            <c:set var="student2" value="Jane,Smith,Business,3.9" />
            <c:set var="student3" value="Bob,Johnson,Engineering,3.7" />
            
            <table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Major</th>
                        <th>GPA</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Loop through students -->
                    <c:forEach items="${student1},${student2},${student3}" var="studentData" varStatus="loop">
                        <c:set var="fields" value="${fn:split(studentData, ',')}" />
                        <tr>
                            <td>${loop.count}</td>
                            <td>${fields[0]}</td>
                            <td>${fields[1]}</td>
                            <td>${fn:toUpperCase(fields[2])}</td>
                            <td><fmt:formatNumber value="${fields[3]}" pattern="0.00" /></td>
                            <td>
                                <!-- Conditional formatting based on GPA -->
                                <c:choose>
                                    <c:when test="${fields[3] >= 3.8}">
                                        <span style="color: green; font-weight: bold;">Dean's List</span>
                                    </c:when>
                                    <c:when test="${fields[3] >= 3.5}">
                                        <span style="color: blue;">Honor Roll</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color: gray;">Good Standing</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- ============================================ -->
        <!-- SECTION 5: SUMMARY OF TAGS USED -->
        <!-- ============================================ -->
        <div class="section">
            <h2>📊 Summary of JSTL Tags Used</h2>
            
            <h3>Core Tags (c:) - 6 tags used:</h3>
            <ul>
                <li><code>&lt;c:set&gt;</code> - Set variables</li>
                <li><code>&lt;c:out&gt;</code> - Output values</li>
                <li><code>&lt;c:if&gt;</code> - Conditional logic</li>
                <li><code>&lt;c:forEach&gt;</code> - Loop through collections</li>
                <li><code>&lt;c:choose&gt;</code> - Multiple conditions</li>
                <li><code>&lt;c:when&gt; / &lt;c:otherwise&gt;</code> - Condition branches</li>
                <li><code>&lt;c:forTokens&gt;</code> - Loop with delimiter</li>
            </ul>
            
            <h3>Formatting Tags (fmt:) - 4 tags used:</h3>
            <ul>
                <li><code>&lt;fmt:formatDate&gt;</code> - Format dates</li>
                <li><code>&lt;fmt:formatNumber&gt;</code> - Format numbers and currency</li>
                <li><code>&lt;fmt:setLocale&gt;</code> - Set locale</li>
                <li><code>&lt;fmt:parseNumber&gt;</code> - Parse number from string</li>
            </ul>
            
            <h3>Functions (fn:) - 7 functions used:</h3>
            <ul>
                <li><code>\${fn:length()}</code> - Get string/collection length</li>
                <li><code>\${fn:toUpperCase()}</code> - Convert to uppercase</li>
                <li><code>\${fn:toLowerCase()}</code> - Convert to lowercase</li>
                <li><code>\${fn:substring()}</code> - Extract substring</li>
                <li><code>\${fn:contains()}</code> - Check if contains</li>
                <li><code>\${fn:replace()}</code> - Replace text</li>
                <li><code>\${fn:split()}</code> - Split string into array</li>
            </ul>
            
            <p style="margin-top: 10px; padding: 15px; background: #e3f2fd; border-left: 4px solid #2196f3; border-radius: 4px;">
                <strong>ℹ️ Note:</strong> XML and SQL tags can be added if needed, but are optional 
                for this demonstration since they require external XML files or database connections.
            </p>
        </div>
        
        <!-- ============================================ -->
        <!-- SECTION 6: EXPRESSION LANGUAGE DEMO -->
        <!-- ============================================ -->
        <div class="section">
            <h2>💡 Expression Language (EL) Examples</h2>
            
            <p><strong>Arithmetic Operations:</strong></p>
            <p>\${10 + 5} = ${10 + 5}</p>
            <p>\${20 * 3} = ${20 * 3}</p>
            <p>\${100 / 4} = ${100 / 4}</p>
            <p>\${17 mod 5} = ${17 mod 5}</p>
            
            <p><strong>Comparison Operations:</strong></p>
            <p>\${5 > 3} = ${5 > 3}</p>
            <p>\${10 == 10} = ${10 == 10}</p>
            <p>\${7 != 5} = ${7 != 5}</p>
            
            <p><strong>Logical Operations:</strong></p>
            <p>\${true && false} = ${true && false}</p>
            <p>\${true || false} = ${true || false}</p>
            <p>\${!false} = ${!false}</p>
            
            <p><strong>Conditional (Ternary) Operator:</strong></p>
            <c:set var="temperature" value="75" />
            <p>Temperature: ${temperature}°F - ${temperature > 70 ? "Warm ☀️" : "Cool ❄️"}</p>
            
            <p><strong>Empty Operator:</strong></p>
            <c:set var="emptyString" value="" />
            <p>\${empty emptyString} = ${empty emptyString}</p>
        </div>

    </div>
</body>
</html>

