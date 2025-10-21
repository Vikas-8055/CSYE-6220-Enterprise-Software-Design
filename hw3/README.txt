================================================================================
                            ENTERPRISE SOFTWARE DESIGN - HOMEWORK 3
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

        -- Create moviedb database
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

        -- Create booksdb database
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

2. src/main/java/com/mycompany/hw3/DatabaseUtil.java (Line 15)
      Change: private static final String DB_PASSWORD = "admin@123";

Replace "admin@123" with YOUR MySQL root password in both files.

STEP 3: OPEN PROJECT IN NETBEANS
---------------------------------
1. Launch Apache NetBeans IDE
2. Go to: File → Open Project
3. Navigate to the hw3 folder
4. Click "Open Project"
5. Wait for NetBeans to load dependencies

STEP 4: BUILD PROJECT
---------------------
1. Right-click on the "hw3" project in the Projects panel
2. Select "Clean and Build"
3. Wait for "BUILD SUCCESS" message in the Output window

STEP 5: RUN APPLICATION
-----------------------
1. Right-click on the "hw3" project
2. Select "Run"
3. Browser will automatically open to: http://localhost:8080/hw3/
4. If browser doesn't open, manually navigate to: http://localhost:8080/hw3/

================================================================================
                                TESTING THE APPLICATION
================================================================================

MAIN ENTRY POINT:
-----------------
http://localhost:8080/hw3/

This landing page provides links to all assignment parts.

PART 3 - JSTL TAGS DEMONSTRATION
---------------------------------
URL: http://localhost:8080/hw3/jstl-demo.jsp

Features:
    - Core Tags: c:set, c:if, c:forEach, c:choose, c:forTokens
    - Formatting Tags: fmt:formatNumber, fmt:formatDate, fmt:setLocale, fmt:parseNumber
    - Function Tags: fn:length, fn:toUpperCase, fn:toLowerCase, fn:substring, 
                                      fn:contains, fn:replace, fn:split
    - Expression Language (EL) examples
    - Combined example with student records

Testing:
1. Navigate to the URL above
2. Verify all sections display correctly:
      - Core Tags section with variables and loops
      - Formatting Tags section with dates and numbers
      - Functions section with string operations
      - Combined example table with student records
      - Expression Language examples

PART 6 - MOVIE STORE (CRUD APPLICATION)
----------------------------------------
URL: http://localhost:8080/hw3/movieHome.jsp

Features:
    - CREATE: Add new movies to database
    - READ: Browse all movies in a formatted table
    - UPDATE: Edit existing movie details
    - DELETE: Remove movies from database
    - SEARCH: Find movies by title or genre
    - MVC architecture using Servlets and JSP
    - MySQL integration with JDBC

Testing:
1. Start at: http://localhost:8080/hw3/movieHome.jsp
2. Test Browse: Click "Browse Movies" to see all movies
3. Test Add: Click "Add Movie to Database"
      - Enter Title: "Interstellar"
      - Enter Genre: "Sci-Fi"
      - Enter Year: 2014
      - Click "Add Movie"
      - Verify it appears in the list
4. Test Search: Click "Search Movies"
      - Search for "Sci-Fi"
      - Verify matching results appear
5. Test Edit: From movie list, click "Edit" on any movie
      - Modify the details
      - Click "Update Movie"
      - Verify changes are saved
6. Test Delete: Click "Delete" on a movie
      - Confirm deletion
      - Verify movie is removed

Database Verification:
      mysql -u root -p
      USE moviedb;
      SHOW TABLES;
      DESCRIBE movies;
      -- Should show: id (int), title (varchar(255)), genre (varchar(100)), releaseYear (int)
      SELECT * FROM movies;

PART 7 - BOOK ENTRY (PREPAREDSTATEMENT DEMO)
---------------------------------------------
URL: http://localhost:8080/hw3/askQuantity.jsp

Features:
    - User specifies number of books to add
    - Dynamically generates input form
    - Secure database insertion using PreparedStatement
    - Batch processing of multiple records
    - MVC architecture with servlets
    - SQL injection protection

Testing:
1. Navigate to: http://localhost:8080/hw3/askQuantity.jsp
2. Enter number of books: 3
3. Click "Generate Form"
4. Fill in details for 3 books:
      Book 1: ISBN: 978-0134685991, Title: "Effective Java", Authors: "Joshua Bloch", Price: 54.99
      Book 2: ISBN: 978-0596009205, Title: "Head First Java", Authors: "Kathy Sierra", Price: 49.99
      Book 3: ISBN: 978-0134494166, Title: "Clean Code", Authors: "Robert Martin", Price: 44.99
5. Click "Add Books"
6. Verify success message appears

Database Verification:
      mysql -u root -p
      USE booksdb;
      SHOW TABLES;
      DESCRIBE books;
      -- Should show: isbn (varchar(12)), title (varchar(60)), authors (varchar(60)), price (float)
      SELECT * FROM books;

PART 9 - CUSTOM TAG (CSV DATA DISPLAY)
---------------------------------------
URL: http://localhost:8080/hw3/displayParking.jsp

Features:
    - Custom Tag Handler extending SimpleTagSupport
    - CsvJdbc integration for CSV file reading
    - Tag Library Descriptor (TLD) configuration
    - Dynamic HTML table generation in doTag() method
    - Displays 1,912 parking facility records
    - 12 columns of data displayed

Testing:
1. Navigate to: http://localhost:8080/hw3/displayParking.jsp
2. Verify table displays with:
      - 1,912 parking facility records
      - 12 columns: Facility Type, License Number, Entity Name, etc.
      - Properly formatted table with headers
      - Data displayed in rows

================================================================================
                                   TECHNOLOGY STACK
================================================================================

Backend:
--------
- Java 11
- Jakarta EE 10
- Servlets (jakarta.servlet.http.HttpServlet)
- JSP (JavaServer Pages)
- JDBC (Java Database Connectivity)
- PreparedStatement (for secure SQL)

Frontend:
---------
- HTML5
- CSS3 (with gradients and modern styling)
- JSTL 3.0 (Core, Formatting, Functions)
- Expression Language (EL)

Database:
---------
- MySQL 8.x
- Two databases: moviedb, booksdb

Build Tools:
------------
- Maven (dependency management)
- Apache Tomcat 10.1.x (application server)

Libraries:
----------
- Jakarta EE API 10.0.0
- JSTL API 3.0.0
- MySQL Connector/J 8.2.0
- CsvJdbc 1.0.41 (for CSV file reading)

================================================================================
                                   PROJECT STRUCTURE
================================================================================

hw3/
├── src/main/java/
│      ├── com.movie.controller/
│      │      └── MovieController.java                    (Servlet for movie CRUD operations)
│      ├── com.movie.model/
│      │      └── Movie.java                                        (Movie entity/bean)
│      ├── com.movie.dao/
│      │      └── MovieDAO.java                                  (Data Access Object for movies)
│      ├── com.movie.util/
│      │      └── DatabaseConnection.java              (Database utility for movies)
│      ├── com.csv.tags/
│      │      └── DisplayCsvTag.java                        (Custom tag handler)
│      └── com.mycompany.hw3/
│              ├── AddBooksServlet.java                    (Servlet for adding books)
│              ├── GenerateBookFormServlet.java    (Servlet for form generation)
│              └── DatabaseUtil.java                          (Database utility for books)
│
├── src/main/webapp/
│      ├── WEB-INF/
│      │      ├── tlds/
│      │      │      └── csvtags.tld                              (Tag Library Descriptor)
│      │      └── web.xml                                              (Deployment descriptor)
│      ├── data/
│      │      └── parking_facilities.csv                (CSV data file - 1,912 records)
│      ├── index.html                                                (Landing page)
│      ├── jstl-demo.jsp                                          (Part 3 - JSTL demonstration)
│      ├── movieHome.jsp                                          (Part 6 - Movie store home)
│      ├── movieList.jsp                                          (Part 6 - Movie list view)
│      ├── addMovie.jsp                                            (Part 6 - Add movie form)
│      ├── editMovie.jsp                                          (Part 6 - Edit movie form)
│      ├── searchMovie.jsp                                      (Part 6 - Search movie form)
│      ├── askQuantity.jsp                                      (Part 7 - Book quantity input)
│      ├── booksAdded.jsp                                        (Part 7 - Success confirmation)
│      └── displayParking.jsp                                (Part 9 - CSV display with custom tag)
│
└── pom.xml                                                                (Maven configuration)

================================================================================
