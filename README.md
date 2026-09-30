# Employee Management System

A desktop-based Employee Management System built using **Java Swing, JDBC, and MySQL**. The application provides a simple graphical interface to manage employee records using complete CRUD operations.

## 🚀 Features

- Add new employees
- View all employees
- Update employee details
- Delete employee records
- Store employee data permanently in MySQL
- Load employee records from MySQL
- Input validation
- Java Swing graphical interface
- JDBC database connectivity
- PreparedStatement for secure SQL operations

## 🛠️ Technologies Used

- Java 21
- Java Swing
- JDBC
- MySQL
- IntelliJ IDEA
- Git & GitHub

🏗️ Application Architecture

Java Swing UI
      ↓
EmployeeFrame
      ↓
EmployeeDAO
      ↓
JDBC
      ↓
MySQL Database

📌 Main Components
Employee.java
Model class representing an employee. It contains employee ID, name, department, and salary. The class uses encapsulation with private fields, constructors, getters, and setters.
EmployeeFrame.java
Main Java Swing GUI that provides employee input fields, Add, Update, Delete, and Clear buttons, along with a table displaying employee records.
EmployeeDAO.java
Data Access Object responsible for communicating with MySQL. It handles adding, fetching, updating, and deleting employee records.
DatabaseConnection.java
Establishes the JDBC connection between the Java application and MySQL. Database credentials are handled using environment variables instead of being hard-coded.
🗄️ Database
Database: employee_management
Table: employees
Column	Data Type	Description
id	INT	Employee ID / Primary Key
name	VARCHAR(100)	Employee Name
department	VARCHAR(100)	Employee Department
salary	DOUBLE	Employee Salary


🗃️ Database Setup
CREATE DATABASE IF NOT EXISTS employee_management;

USE employee_management;

CREATE TABLE IF NOT EXISTS employees (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    salary DOUBLE NOT NULL
);

▶️ How to Run
1. Clone the Repository
git clone https://github.com/Ishita-13/EmployeeManagementSystem.git

2. Open the Project
Open the project in IntelliJ IDEA and configure JDK 21.
3. Configure MySQL
Make sure MySQL Server is running and create the employee_management database and employees table using the SQL commands above.
4. Configure Database Password
The MySQL password is not stored directly in the source code. Set the required MYSQL_PASSWORD environment variable used by DatabaseConnection.java.
5. Add MySQL Connector/J
The project uses MySQL Connector/J for JDBC database connectivity.
6. Run the Application
Run:
EmployeeFrame.java

The Employee Management System GUI will open.
💡 Concepts Demonstrated
- Object-Oriented Programming
- Encapsulation
- Classes and Objects
- Constructors
- Inheritance
- Java Swing
- Event Handling
- JDBC
- MySQL
- SQL
- CRUD Operations
- PreparedStatement
- DAO Pattern
- Exception Handling
- Collections
- Git & GitHub
🔄 CRUD Operations
Create → Add Employee
Read   → View Employees
Update → Modify Employee
Delete → Remove Employee

🔐 Security
Database credentials are not hard-coded in the Java source code. The application uses environment variables for sensitive database information to prevent credentials from being exposed in the GitHub repository.
🎯 Future Improvements
- Employee search functionality
- Search by department
- Sorting and filtering
- Login and authentication
- Improved UI design
- Employee profile management
- Export employee records to CSV/PDF
👩‍💻 Author
Ishita Pandey
B.Tech – Artificial Intelligence & Data Science
LNCT Bhopal
