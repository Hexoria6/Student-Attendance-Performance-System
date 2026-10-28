
# Student Attendance and Performance Management System

## Project Overview
The Student Attendance and Performance Management System is a web-based application developed using Java Servlets, JSP, and MySQL. It helps manage student details, record attendance, enter marks, and generate academic performance reports.

## Technologies Used
- Java
- Java Servlets
- JSP (JavaServer Pages)
- MySQL
- MySQL Workbench
- JDBC
- Apache Tomcat 11
- HTML and CSS
- Eclipse IDE

## Features
- **Student Registration:** Register students with their roll number, name, email, course, and semester.
- **View Students:** Display registered student records.
- **Attendance Management:** Record and manage student attendance.
- **Marks Management:** Enter subject-wise marks for students.
- **Performance Reports:** Calculate percentages and display grades.
- **Database Integration:** Store and retrieve data using MySQL.

## Database
Database name: `student_management`

Main tables:
- `Students`
- `Attendance`
- `Marks`

The project also includes a stored procedure for attendance calculation and a trigger to validate marks.

## Installation and Setup
1. Install JDK, Eclipse IDE, Apache Tomcat 11, and MySQL Server.
2. Create a MySQL database named `student_management`.
3. Create the required tables and database objects.
4. Add the MySQL Connector/J library to the project.
5. Configure the database connection in `DBConnection.java`.
6. Import the project into Eclipse.
7. Configure Tomcat 11 as the server.
8. Run the project on the server and open the application homepage.

## Learning Outcomes
- Learned Java Servlet and JSP development.
- Practiced JDBC connectivity with MySQL.
- Implemented database operations using SQL and PreparedStatement.
- Learned to manage student attendance and academic performance.
- Integrated a Java web application with a relational database.

## Author
Developed as an academic project.
