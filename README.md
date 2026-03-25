## SPRING CRUD OPERATIONS JDBC
In this project we create, read , update ,delete employee details.</br>
in a maven project using jdbcTemplate and switch cases.</br>

## Steps
1. create a maven projecct.</br>
2. add dependencies.</br>
3. create a class Employee in com.pa.beans package.</br>
4. create a configuration class annotate it with @configuration</br>
   and configure DriverManagerDataSource and jdbctemplate methods.</br>
5. create EmployeeRowMapper class implementing RowMapper<T> under com.pa.rowmapper package.</br>
    this helps in fetching the data.
6. create a ApplicationContext object using AnnotationConfigApplicationContext(SpringConfigFile.class) in App class.
7. create JdbcTemplate jdbcTemplate=context.getBean(JdbcTemplate.class) in app class</br>
   this object gives crud methods(excuteUpdate, query, queryForObject..).</br>
8. By creating Sql queries we perform crud operations.

## To run the program
Right click on the project select run as java application

## dependencies used
Spring context, spring jdbc api, mysql connector

## prerequisites
ecllipse IDE</br>
Scanner object and Switch cases</br>
Spring annotations
