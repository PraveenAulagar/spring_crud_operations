# Employee Management System (Spring JDBC CRUD Application)

A streamlined, console-based backend application designed to handle core Employee Data Operations. This project is built using the **Spring Framework (Spring JDBC)** and a **MySQL** database. 

It serves as an excellent portfolio piece demonstrating key enterprise Java skills: decoupled architecture, object-relational row mapping, dependency injection using Java-based configurations (`@Configuration` and `@Bean`), and clean terminal interfaces.

---
📁 Project Directory & Complete Source Code
Below is the structured layout of the project along with the complete source code converted into clean Markdown syntax:

```text
src/main/java
 └── com.pa
      ├── beans
      │    └── Employee.java             # Plain Old Java Object (POJO) / Data Model
      ├── mappers
      │    └── EmployeeRowMapper.java    # Maps database records to Java Objects
      ├── resources
      │    └── SpringConfigFile.java     # Java-Based Spring Configuration & Beans
      └── Spring_crud_operations
           └── App.java                  # Application Main Entry Point & Core Logic
```
## 🚀 System Walkthrough: What the Project Does

The core purpose of this application is to manage employee database records safely and efficiently through an interactive Command Line Interface (CLI). When executed, the system presents a continuous menu loop allowing an administrator to perform full data operations:

```text
Press 1 to save employee details          --> [CREATE]  Adds a new employee row to MySQL
Press 2 to update employee details        --> [UPDATE]  Modifies existing records (Granular choices)
Press 3 to delete employee details        --> [DELETE]  Removes an employee record securely via ID
Press 4 to fetch all employee details     --> [READ ALL] Retrieves and lists every record in the table
Press 5 to fetch single employee details  --> [READ ONE] Finds and displays one specific record by ID
Press 6 to exit the application           --> Closes database contexts and stops execution cleanly
```

### Case 2 Switch

When an administrator selects **Option 2** from the primary application menu, the system branches into a dedicated sub-menu. Instead of performing a heavy, full-row replacement that risks overwriting unmodified data, the system utilizes a specialized nested switch block to target individual columns.

Below is the structured walkthrough of how each sub-choice interacts with the MySQL database via Spring's `JdbcTemplate`:

```text
Main Menu (Press 2) ──> Prompt for Choice ──> Sub-Switch Tree:
                                                ├── Choice 1: Modifies 'emp_name' only
                                                ├── Choice 2: Modifies 'emp_role' only
                                                ├── Choice 3: Modifies 'emp_email' only
                                                ├── Choice 4: Modifies 'emp_salary' only
                                                └── Choice 5: Modifies all properties at once
```

### 📦 Project Dependencies Registry

| # | Dependency Name / Artifact | Group ID | Artifact ID | Version | Scope | Purpose / Role |
| :---: | :--- | :--- | :--- | :---: | :---: | :--- |
| **1** | **Spring Context** | `org.springframework` | `spring-context` | `7.0.5` | `compile` | Core Spring container lifecycle management, supporting `@Configuration` and `@Bean` definitions. |
| **2** | **Spring JDBC** | `org.springframework` | `spring-jdbc` | `7.0.5` | `compile` | Provides data access abstraction, supplying `JdbcTemplate` and `RowMapper` to eliminate boilerplate connection/statement code. |
| **3** | **MySQL Connector** | `com.mysql` | `mysql-connector-j` | `8.3.0` | `compile` | Official JDBC driver implementation enabling the Java runtime environment to communicate with the local MySQL server. |
| **4** | **JUnit** | `junit` | `junit` | `3.8.1` | `test` | Java unit testing library framework utilized for isolating and verifying repository methods. |

🗄️ Database Architecture Schema
The application maps data structures onto an explicit MySQL engine schema setup:
```text
-- Create database system instance 
CREATE DATABASE Spring_jdbc_crud88;
USE Spring_jdbc_crud88;

-- Create target data table structures
CREATE TABLE employee (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(100) NOT NULL,
    emp_role VARCHAR(50),
    emp_email VARCHAR(100),
    emp_salary DOUBLE
);

```
🛠️ Architectural Breakdown: How It Works Under the Hood
The application relies on modular segregation of responsibilities. It is divided into 4 specific functional layers:

1. The Model
File: Employee.java

Role: Acts as a Plain Old Java Object (POJO) representing an Employee entity in the application's memory layer. Encapsulates properties (id, name, email, role, salary) behind private scope bounds with access managed exclusively via standard Getters and Setters.

2. The Mapper
File: EmployeeRowMapper.java

Role: Implements Spring's foundational RowMapper<Employee> interface. Relational databases speak in tables and rows, while Java speaks in object graphs. This file translates raw database cursor tables (ResultSet) directly into compiled Employee objects automatically, saving line-by-line parsing code.

```text
package com.pa.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.pa.beans.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee emp = new Employee();
		emp.setId(rs.getInt("emp_id"));
		emp.setName(rs.getString("emp_name"));
		emp.setRole(rs.getString("emp_role"));
		emp.setEmail(rs.getString("emp_email"));
		emp.setSalary(rs.getDouble("emp_salary"));
		return emp;
	}
}
```

3.Configuration File
File: SpringConfigFile.java

Role: Contains modern, annotation-driven Spring configuration logic utilizing @Configuration and @Bean.

It instantiates a DriverManagerDataSource to anchor connection parameters (Database URL, Credentials, Driver Classes).

It passes that source to create a managed JdbcTemplate bean. This eliminates boilerplate database code—Spring handles opening connections, statement preparation, exception translation, and session cleanups automatically.

```text
package com.pa.resources;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringConfigFile {
	
	final String driverClass="com.mysql.cj.jdbc.Driver";
	final String url="jdbc:mysql://localhost:3306/Spring_jdbc_crud88";
	final String username="root";
	final String password="root";
	
	@Bean
	public DriverManagerDataSource mydatDataSource()
	{
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate myJdbcTemplate()
	{
		JdbcTemplate jdbcTemplate= new JdbcTemplate();
		jdbcTemplate.setDataSource(mydatDataSource());
		return jdbcTemplate;
	}
}
```

4. Interactive Controller
File: App.java

Role: The main entry point. It boots up the Spring application context environment, loads database templates, opens an optimized standard input reader (Scanner), handles the user workflow switch tree, and executes transactional updates.
### Application started with table data before inserting new employee
![image alt](https://github.com/PraveenAulagar/spring_crud_operations/blob/286c859d16080d96ac84d933d9cf87e9fd874f64/Spring_crud_operations/screenshots/application.png)
### Inserting new Employee
![image alt](https://github.com/PraveenAulagar/spring_crud_operations/blob/b121bbe56e533e926410ac93cfd9bb30679cfbb3/Spring_crud_operations/screenshots/inserting_new_employee_details.png)
### Updating employee name using employee id. Employee name praveen changed to pravin
![image alt](https://github.com/PraveenAulagar/spring_crud_operations/blob/2e230a0d3b74162222c54b65ec3db1a0ff46edd8/Spring_crud_operations/screenshots/updating_employee_name.png)
