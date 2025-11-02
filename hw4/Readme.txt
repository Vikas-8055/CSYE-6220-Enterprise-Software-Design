================================================================================
                                        ENTERPRISE SOFTWARE DESIGN - HOMEWORK 4
                                                  Spring Framework Assignment
================================================================================

STUDENT INFORMATION:
--------------------
Name: Vikas Meneni
NUID: 002309537
Course: Enterprise Software Design

================================================================================
                                                      INSTALLATION GUIDE
================================================================================

STEP 1: CREATE DATABASES
-------------------------
Open MySQL Command Line or MySQL Workbench and run:

        mysql -u root -p

Then execute the following SQL commands:

        -- Create moviedb database (for Part 4)
        DROP DATABASE IF EXISTS moviedb;
        CREATE DATABASE moviedb;
        USE moviedb;

        CREATE TABLE movies (
                id INT PRIMARY KEY AUTO_INCREMENT,
                title VARCHAR(255) NOT NULL,
                genre VARCHAR(100) NOT NULL,
                releaseYear INT NOT NULL
        );

        -- Insert sample data
        INSERT INTO movies (title, genre, releaseYear) VALUES
        ('The Matrix', 'Sci-Fi', 1999),
        ('Inception', 'Sci-Fi', 2010),
        ('Titanic', 'Romance', 1997),
        ('The Godfather', 'Crime', 1972),
        ('The Dark Knight', 'Action', 2008);

        -- Create booksdb database (for Part 5)
        DROP DATABASE IF EXISTS booksdb;
        CREATE DATABASE booksdb;
        USE booksdb;

        CREATE TABLE books (
                isbn VARCHAR(12),
                title VARCHAR(60),
                authors VARCHAR(60),
                price FLOAT
        );

        -- Verify databases
        SHOW DATABASES;

STEP 2: CONFIGURE DATABASE CREDENTIALS
---------------------------------------
If your MySQL root password is NOT "admin@123":

Update the password in these 2 files:

1. src/main/java/com/movie/util/DatabaseConnection.java (Line 10)
      Change: private static final String PASSWORD = "admin@123";

2. src/main/java/com/book/util/BookDatabaseUtil.java (Line 10)
      Change: private static final String DB_PASSWORD = "admin@123";

Replace "admin@123" with YOUR MySQL root password in both files.

STEP 3: OPEN PROJECT IN NETBEANS
---------------------------------
1. Launch Apache NetBeans IDE
2. Go to: File → Open Project
3. Navigate to the hw4 folder
4. Click "Open Project"
5. Wait for NetBeans to load dependencies (Spring framework download may take time)

STEP 4: BUILD PROJECT
---------------------
1. Right-click on the "hw4" project in the Projects panel
2. Select "Clean and Build"
3. Wait for "BUILD SUCCESS" message in the Output window
4. First build may take longer as Maven downloads Spring dependencies

STEP 5: RUN APPLICATION
-----------------------
1. Right-click on the "hw4" project
2. Select "Run"
3. Browser will automatically open to: http://localhost:8080/hw4/
4. Landing page displays links to all assignment parts

================================================================================
                                                    TESTING THE APPLICATION
================================================================================

MAIN ENTRY POINT:
-----------------
http://localhost:8080/hw4/

This landing page provides links to all assignment parts.

PART 4 - MOVIE STORE (SPRING MVC)
----------------------------------
URL: http://localhost:8080/hw4/movieHome

Features:
    - Spring MVC architecture (converted from HW3 servlets)
    - SimpleUrlHandlerMapping for URL routing
    - Complete CRUD operations
    - MySQL database integration
    - ModelAndView for view resolution

Implementation Note:
    ControllerClassNameHandlerMapping was deprecated in Spring 3.x and removed 
    in Spring 5+ in favor of annotation-driven handler methods. This implementation 
    uses SimpleUrlHandlerMapping which provides equivalent URL-to-controller mapping 
    functionality and works with modern Spring 6.

Testing:
1. Navigate to: http://localhost:8080/hw4/movieHome
2. Test Browse: Click "Browse Movies"
      - Should display all movies from moviedb
      - Verify movies show in table format
3. Test Add: Click "Add New Movie"
      - Enter Title: "Interstellar"
      - Enter Genre: "Sci-Fi"
      - Enter Year: 2014
      - Click "Add Movie"
      - Verify movie appears in browse list
4. Test Search: Click "Search Movies"
      - Enter keyword: "Sci-Fi"
      - Click "Search"
      - Verify matching movies display
5. Test Edit: From browse list, click "Edit"
      - Modify movie details
      - Click "Update Movie"
      - Verify changes saved
6. Test Delete: From browse list, click "Delete"
      - Confirm deletion
      - Verify movie removed

Database Verification:
      mysql -u root -p
      USE moviedb;
      DESCRIBE movies;
      -- Should show: id (int), title (varchar(255)), genre (varchar(100)), releaseYear (int)
      SELECT * FROM movies;

PART 5 - BOOK ENTRY (SPRING MVC WITH PREPAREDSTATEMENT)
--------------------------------------------------------
URL: http://localhost:8080/hw4/askQuantity

Features:
    - BeanNameUrlHandlerMapping (bean names become URLs)
    - Dynamic form generation
    - PreparedStatement for secure SQL
    - Batch processing
    - Spring MVC request handling

Testing:
1. Navigate to: http://localhost:8080/hw4/askQuantity
2. Enter number of books: 3
3. Click "Generate Form"
4. Should see table with 3 input rows
5. Fill in book details:
      Book 1: ISBN: 978-0134685991, Title: "Effective Java", Authors: "Joshua Bloch", Price: 54.99
      Book 2: ISBN: 978-0596009205, Title: "Head First Java", Authors: "Kathy Sierra", Price: 49.99
      Book 3: ISBN: 978-0134494166, Title: "Clean Code", Authors: "Robert Martin", Price: 44.99
6. Click "Add Books"
7. Verify success message appears

Database Verification:
      mysql -u root -p
      USE booksdb;
      DESCRIBE books;
      -- Should show: isbn (varchar(12)), title (varchar(60)), authors (varchar(60)), price (float)
      SELECT * FROM books;

PART 6 - SHOPPING CART (ANNOTATED CONTROLLERS)
-----------------------------------------------
URL: http://localhost:8080/hw4/shop

Features:
    - @Controller annotation
    - @RequestMapping for URL mapping
    - @RequestParam for parameter binding
    - Session management for cart state
    - No database (session-based cart)

Testing:
1. Navigate to: http://localhost:8080/hw4/shop
2. Should see grid of 6 products (Laptop, Mouse, Keyboard, etc.)
3. Test Add: Click "Add to Cart" on Laptop
      - Should redirect to cart page
      - Verify Laptop appears in cart
4. Test Continue Shopping: Click "Continue Shopping"
      - Add more items (Mouse, Keyboard)
      - Verify multiple items in cart
5. Test Remove: Click "Remove" on an item
      - Confirm deletion
      - Verify item removed and total updated
6. Test Checkout: Click "Checkout"
      - Should see success page
      - Verify cart is cleared
7. Test Empty Cart: Click "View Cart"
      - Should show empty cart message

PART 7 - SPRING IOC SINGLETON (XML INJECTION)
----------------------------------------------
URL: http://localhost:8080/hw4/message

Features:
    - MessageUtility class with getMessage() method
    - Spring IoC container creates object (no 'new' operator)
    - XML-based setter injection
    - Singleton scope - one instance for entire application
    - Object ID printed to console

Testing:
1. Navigate to: http://localhost:8080/hw4/message
2. Note the Object Hash Code displayed (e.g., 1125793384)
3. Check NetBeans console output
      - Should see: "MessageUtility object created! Object ID: [number]"
      - Should see: "Spring injected MessageUtility into Controller"
4. Click "Refresh to Test Singleton" button 5 times
5. Verify Object Hash Code NEVER changes
6. Check console - should see same Object ID for all requests
7. Confirms singleton scope: one object reused across all requests

Console Output Expected:
      ✅ MessageUtility object created! Object ID: 1125793384    (created once only)
      🔧 Spring injected MessageUtility into Controller
      📊 Controller using MessageUtility with Object ID: 1125793384
      📊 Controller using MessageUtility with Object ID: 1125793384
      (same ID repeats for each request)

PART 8 - SPRING IOC SINGLETON (@AUTOWIRED)
-------------------------------------------
URL: http://localhost:8080/hw4/messageAutowired

Features:
    - Same as Part 7 but uses @Autowired annotation
    - No setter method required
    - Singleton scope maintained
    - Automatic dependency injection

Testing:
1. Navigate to: http://localhost:8080/hw4/messageAutowired
2. Note the Object Hash Code
3. CRITICAL: Should be the SAME as Part 7's Object ID
      - Both parts share the same MessageUtility singleton bean
4. Refresh multiple times
5. Verify Object ID never changes
6. Check console for "@Autowired" messages

Console Output Expected:
      📊 Part 8 (@Autowired) - MessageUtility Object ID: 1125793384
      (same ID as Part 7 - proves they share the same singleton bean)

PART 9 - SPRING IOC REQUEST SCOPE
----------------------------------
URL: http://localhost:8080/hw4/messageRequest

Features:
    - Uses @Autowired with REQUEST scope
    - New object created for EACH request
    - Scoped proxy for proper injection
    - Demonstrates opposite of singleton

Testing:
1. Navigate to: http://localhost:8080/hw4/messageRequest
2. Note the Object Hash Code (e.g., 193866123)
3. Click "Refresh to See Different Object ID"
4. Object Hash Code should CHANGE (e.g., 421456290)
5. Refresh 5 more times
6. Each refresh shows DIFFERENT Object ID
7. Check console - should see new object created each request

Console Output Expected:
      ✅ MessageUtility object created! Object ID: 193866123
      🔄 Part 9 (Request Scope) - MessageUtility Object ID: 193866123
      ✅ MessageUtility object created! Object ID: 421456290    (NEW object!)
      🔄 Part 9 (Request Scope) - MessageUtility Object ID: 421456290
      (different IDs for each request - proves request scope)

================================================================================
                                                      TECHNOLOGY STACK
================================================================================

Backend:
--------
- Java 11
- Spring Framework 6.0.11
- Spring MVC
- Spring IoC/DI Container
- Spring AOP (for scoped-proxy)
- Jakarta EE 10
- JSP & JSTL 3.0
- JDBC
- PreparedStatement

Frontend:
---------
- HTML5
- CSS3 (with gradients and modern styling)
- JSTL (Core and Formatting tags)
- Expression Language (EL)

Database:
---------
- MySQL 8.x
- Two databases: moviedb, booksdb

Build Tools:
------------
- Maven (dependency management)
- Apache Tomcat 10.1.x (application server)

Spring Components:
------------------
- DispatcherServlet (Front Controller)
- ViewResolver (InternalResourceViewResolver)
- BeanNameUrlHandlerMapping (Part 5)
- SimpleUrlHandlerMapping (Part 4)
- Annotation-driven MVC (Part 6)
- Component Scanning
- Static Resource Handling

Libraries:
----------
- Spring Web MVC 6.0.11
- Spring Context 6.0.11
- Spring AOP 6.0.11
- Jakarta EE API 10.0.0
- JSTL API 3.0.0
- MySQL Connector/J 8.2.0

================================================================================
                                                      PROJECT STRUCTURE
================================================================================

hw4/
├── src/main/java/
│      ├── com.spring.controller/
│      │      ├── MessageController.java                            (Part 7 - XML injection)
│      │      ├── MessageControllerAutowired.java          (Part 8 - @Autowired)
│      │      ├── MessageControllerRequest.java              (Part 9 - Request scope)
│      │      ├── RootController.java                                  (Landing page redirect)
│      │      ├── MovieHomeController.java                        (Part 4)
│      │      ├── BrowseController.java                              (Part 4)
│      │      ├── AddMovieFormController.java                  (Part 4)
│      │      ├── AddMovieController.java                          (Part 4)
│      │      ├── SearchController.java                              (Part 4)
│      │      ├── SearchMovieController.java                    (Part 4)
│      │      ├── EditMovieController.java                        (Part 4)
│      │      ├── UpdateMovieController.java                    (Part 4)
│      │      ├── DeleteMovieController.java                    (Part 4)
│      │      ├── AskQuantityController.java                    (Part 5)
│      │      ├── GenerateBookFormController.java          (Part 5)
│      │      ├── AddBooksController.java                          (Part 5)
│      │      └── ShoppingCartController.java                  (Part 6 - Annotated)
│      │
│      ├── com.spring.util/
│      │      └── MessageUtility.java                                  (Parts 7, 8, 9)
│      │
│      ├── com.movie.model/
│      │      └── Movie.java                                                    (Part 4)
│      ├── com.movie.dao/
│      │      └── MovieDAO.java                                              (Part 4)
│      ├── com.movie.util/
│      │      └── DatabaseConnection.java                          (Part 4)
│      │
│      ├── com.book.model/
│      │      └── Book.java                                                      (Part 5)
│      ├── com.book.dao/
│      │      └── BookDAO.java                                                (Part 5)
│      ├── com.book.util/
│      │      └── BookDatabaseUtil.java                              (Part 5)
│      │
│      └── com.cart.model/
│              ├── Product.java                                                (Part 6)
│              └── CartItem.java                                              (Part 6)
│
├── src/main/webapp/
│      ├── WEB-INF/
│      │      ├── views/
│      │      │      ├── message.jsp                                          (Part 7)
│      │      │      ├── messageAutowired.jsp                        (Part 8)
│      │      │      ├── messageRequest.jsp                            (Part 9)
│      │      │      ├── movieHome.jsp                                      (Part 4)
│      │      │      ├── movieList.jsp                                      (Part 4)
│      │      │      ├── addMovie.jsp                                        (Part 4)
│      │      │      ├── editMovie.jsp                                      (Part 4)
│      │      │      ├── searchMovie.jsp                                  (Part 4)
│      │      │      ├── askQuantity.jsp                                  (Part 5)
│      │      │      ├── bookForm.jsp                                        (Part 5)
│      │      │      ├── booksAdded.jsp                                    (Part 5)
│      │      │      ├── productList.jsp                                  (Part 6)
│      │      │      ├── cart.jsp                                                (Part 6)
│      │      │      └── checkout.jsp                                        (Part 6)
│      │      ├── applicationContext.xml                            (Spring configuration)
│      │      └── web.xml                                                          (Web deployment descriptor)
│      │
│      └── index.html                                                            (Landing page)
│
└── pom.xml                                                                          (Maven configuration)

================================================================================
                        PART 4 - CONTROLLERCLASSNAMEHANDLERMAPPING EXPLANATION
================================================================================

ASSIGNMENT REQUIREMENT:
-----------------------
"Redo HW3-Part 6 using Spring MVC – Map all the controllers using 
ControllerClassNameHandlerMapping"

IMPLEMENTATION CHALLENGE:
-------------------------
ControllerClassNameHandlerMapping was:
- Deprecated in Spring 3.x (2009)
- Removed completely in Spring 5.0 (2017)
- Does NOT exist in Spring 6.0.11 (current version)
- Throws ClassNotFoundException if attempted

REASON FOR DEPRECATION:
-----------------------
According to Spring Framework documentation:
"Deprecated in favor of annotation-driven handler methods"

Why annotations are better:
- Type-safe URL mappings
- Better IDE support and refactoring capabilities
- More flexible routing options
- Cleaner, more maintainable code
- Industry standard approach
- Supports RESTful design patterns

IMPLEMENTATION DECISION:
------------------------
Used SimpleUrlHandlerMapping for Part 4:
- Provides equivalent URL-to-controller mapping
- Works with modern Spring 6
- Explicitly maps controller names to URLs
- Achieves same learning objectives
- Demonstrates understanding of handler mapping concepts

Example mapping:
        <bean id="movieHomeController" class="com.spring.controller.MovieHomeController" />
        
        <bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
                <property name="mappings">
                        <props>
                                <prop key="/movieHome">movieHomeController</prop>
                        </props>
                </property>
        </bean>

Result: MovieHomeController mapped to /movieHome

LEARNING OBJECTIVES ACHIEVED:
------------------------------
1. Understanding why legacy Spring components were deprecated
2. Recognizing deprecated patterns in existing codebases
3. Applying modern alternatives
4. Demonstrating both XML (Parts 4-5) and annotation (Part 6) approaches
5. Understanding migration path from legacy to modern Spring

Professor's Guidance:
"For new Projects, Annotations are favored. For older apps, whatever 
is used, we may continue to work as it is."

This assignment successfully demonstrates:
- Legacy pattern understanding (Parts 4-5 with XML configuration)
- Modern pattern application (Part 6 with annotations)
- Migration knowledge for real-world scenarios

================================================================================
                                        SPRING CONFIGURATION APPROACHES USED
================================================================================

THREE DIFFERENT HANDLER MAPPING STRATEGIES:

PART 4 - SimpleUrlHandlerMapping:
----------------------------------
Configuration: Explicit URL-to-controller mapping in XML
Example:
        <prop key="/movieHome">movieHomeController</prop>

Result: Full control over URL patterns

PART 5 - BeanNameUrlHandlerMapping:
------------------------------------
Configuration: Bean name directly becomes URL
Example:
        <bean name="/askQuantity" class="com.spring.controller.AskQuantityController" />

Result: /askQuantity URL maps to AskQuantityController

PART 6 - Annotation-Driven (@Controller + @RequestMapping):
------------------------------------------------------------
Configuration: Annotations in controller code
Example:
        @Controller
        public class ShoppingCartController {
                @RequestMapping("/shop")
                public ModelAndView showProducts() { ... }
        }

Result: /shop URL maps to showProducts() method
Benefits: Modern approach, no XML mapping needed

================================================================================
                                                  SPRING BEAN SCOPES DEMONSTRATED
================================================================================

SINGLETON SCOPE (Parts 7 & 8):
-------------------------------
Definition: ONE instance created for entire application
Lifecycle: Created at container startup, reused for all requests
Use Case: Stateless services, shared utilities

Configuration:
        <bean id="messageUtility" 
                    class="com.spring.util.MessageUtility" 
                    scope="singleton" />

Behavior Observed:
        Request 1: Object ID 1125793384
        Request 2: Object ID 1125793384    ← SAME instance
        Request 3: Object ID 1125793384    ← SAME instance

Benefits:
- Memory efficient (one object)
- Fast (no object creation overhead)
- Shared state across application

REQUEST SCOPE (Part 9):
-----------------------
Definition: NEW instance created for EACH HTTP request
Lifecycle: Created per request, destroyed after response
Use Case: Request-specific data, isolated state

Configuration:
        <bean id="messageUtilityRequest" 
                    class="com.spring.util.MessageUtility" 
                    scope="request">
                <aop:scoped-proxy />
        </bean>

Note: Requires <aop:scoped-proxy /> for proper injection

Behavior Observed:
        Request 1: Object ID 193866123
        Request 2: Object ID 421456290      ← DIFFERENT instance
        Request 3: Object ID 960899765      ← DIFFERENT instance

Benefits:
- Request isolation
- Thread-safe
- No shared state concerns

================================================================================
                                                      DEPENDENCY INJECTION METHODS
================================================================================

PART 7 - XML Setter Injection:
-------------------------------
Controller:
        private MessageUtility messageUtility;
        
        public void setMessageUtility(MessageUtility util) {
                this.messageUtility = util;
        }

Configuration:
        <bean name="/message" class="com.spring.controller.MessageController">
                <property name="messageUtility" ref="messageUtility" />
        </bean>

PART 8 & 9 - @Autowired Annotation:
------------------------------------
Controller:
        @Autowired
        private MessageUtility messageUtility;
        
        // No setter method needed!

Configuration:
        <context:annotation-config />
        <bean name="/messageAutowired" class="..." />
        <!-- No <property> injection needed -->

Benefits of @Autowired:
- Less boilerplate code
- Automatic injection
- No setter methods
- Modern approach

================================================================================
