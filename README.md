# Enterprise Software Design - Course Projects

**Student:** Vikas Meneni  
**NUID:** 002309537  
**Course:** CSYE 6220 - Enterprise Software Design  
**Institution:** Northeastern University

---

## 📚 Project Overview

This repository contains four comprehensive web development assignments demonstrating enterprise Java technologies, from foundational servlets to advanced Spring Framework implementations.

---

## 🚀 Projects

### HW1 - Java Servlets Fundamentals
**Technologies:** Java Servlets, JSP, Apache Tomcat  
**Key Features:**
- HTTP request/response handling
- Form processing with multiple methods (getParameter, getParameterMap, getParameterNames)
- Request header introspection
- Servlet lifecycle methods

**Run:** Deploy to Tomcat webapps, access at `http://localhost:8080/hw1-servlets/`

---

### HW2 - Session Management & File Processing
**Technologies:** Servlets, JSP, Authentication, Apache POI, CsvJdbc  
**Key Features:**
- Declarative security (BASIC authentication)
- Programmatic security (custom login)
- CSV file reading with CsvJdbc
- Excel file processing with Apache POI
- Shopping cart with session management
- Dynamic form generation

**Security:**
- Parts 5-6: `student/password123` (BASIC auth)
- Part 7: `customer/shop123` (Programmatic)

**Run:** Maven project, deploy to Tomcat, access at `http://localhost:8080/hw2/`

---

### HW3 - Database Integration & Custom Tags
**Technologies:** Jakarta EE 10, JSTL, MySQL, JDBC, Custom Tags  
**Key Features:**
- **JSTL Tags:** Core, Formatting, Functions libraries
- **Movie Store:** Full CRUD with MySQL (Create, Read, Update, Delete, Search)
- **Book Entry:** PreparedStatement for SQL injection prevention
- **Custom Tag:** CSV data display with CsvJdbc (1,912 records)
- MVC architecture with servlets

**Databases:** `moviedb`, `booksdb`  
**Run:** Maven project, access at `http://localhost:8080/hw3/`

---

### HW4 - Spring Framework
**Technologies:** Spring Framework 6, Spring MVC, Spring IoC/DI, Annotations  
**Key Features:**
- **Spring IoC:** Dependency Injection with XML and annotations
- **Bean Scopes:** Singleton vs Request scope demonstrations
- **Movie Store:** Converted to Spring MVC with SimpleUrlHandlerMapping
- **Book Entry:** Spring MVC with BeanNameUrlHandlerMapping
- **Shopping Cart:** Annotated controllers (@Controller, @RequestMapping)
- Three handler mapping strategies
- ModelAndView pattern

**Handler Mappings:**
- Part 4: SimpleUrlHandlerMapping (modern replacement for deprecated ControllerClassNameHandlerMapping)
- Part 5: BeanNameUrlHandlerMapping
- Part 6: Annotation-driven (@RequestMapping)

**Run:** Maven project, access at `http://localhost:8080/hw4/`

---

## 🛠️ Tech Stack

| Technology | Version | Used In |
|------------|---------|---------|
| **Java** | 11 | All projects |
| **Jakarta EE** | 10 | HW3, HW4 |
| **Spring Framework** | 6.0.11 | HW4 |
| **MySQL** | 8.x | HW3, HW4 |
| **Apache Tomcat** | 10.1.x | All projects |
| **Maven** | 3.x | HW2, HW3, HW4 |
| **JSTL** | 3.0 | HW3, HW4 |

---

## 📂 Repository Structure
```
enterprise-software-design/
├── hw1-servlets/          # Servlet fundamentals
├── hw2/                   # Session management & file processing
├── hw3/                   # Database integration & custom tags
└── hw4/                   # Spring Framework
```

---

## 🎓 Key Learning Outcomes

- ✅ **Java EE/Jakarta EE:** Servlets, JSP, JSTL, Custom Tags
- ✅ **Spring Framework:** MVC, IoC/DI, Bean Scopes, Annotations
- ✅ **Database:** MySQL, JDBC, PreparedStatement, DAO pattern
- ✅ **Security:** BASIC authentication, Programmatic security
- ✅ **Design Patterns:** MVC, Front Controller, DAO, Singleton
- ✅ **Build Tools:** Maven dependency management
- ✅ **Session Management:** HTTP sessions, shopping cart
- ✅ **File Processing:** CSV, Excel parsing

---

## 🚀 Quick Start

### Prerequisites
- JDK 11+
- Apache Tomcat 10.1.x
- MySQL 8.x (for HW3 & HW4)
- Apache NetBeans IDE (recommended)
- Maven 3.x

### Running Projects

**HW1:**
```bash
# Copy to Tomcat webapps and start server
cp -r hw1-servlets $CATALINA_HOME/webapps/
$CATALINA_HOME/bin/startup.sh
```

**HW2, HW3, HW4:**
```bash
# Open in NetBeans
# Clean and Build
# Run
```

**Database Setup (HW3 & HW4):**
```sql
mysql -u root -p
# Run SQL scripts from respective README files
```

---

## 📝 Documentation

Each project includes detailed README.txt with:
- Installation instructions
- Database setup (where applicable)
- Testing procedures
- Technology details
- Project structure

---

## 🌟 Highlights

- **4 complete web applications** with different architectural approaches
- **Traditional servlets → Spring Framework** progression
- **Multiple authentication mechanisms** (BASIC, programmatic, custom)
- **3 database implementations** with different patterns
- **Custom tag development** with Tag Library Descriptor
- **Spring IoC/DI** with singleton and request scopes
- **Modern Spring annotations** vs legacy XML configuration

---

## 📧 Contact

**Vikas Meneni**  
Northeastern University  
Course: CSYE 6220 - Enterprise Software Design

---

*Built with ☕ and lots of debugging*
