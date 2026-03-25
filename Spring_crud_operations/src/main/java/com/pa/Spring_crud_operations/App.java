package com.pa.Spring_crud_operations;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pa.beans.Employee;
import com.pa.mappers.EmployeeRowMapper;
import com.pa.resources.SpringConfigFile;

public class App 
{
    public static void main( String[] args ) {
    	
    	ApplicationContext context= new AnnotationConfigApplicationContext(SpringConfigFile.class);
    	JdbcTemplate jdbcTemplate=context.getBean(JdbcTemplate.class);
    	
    	Scanner scanner= new Scanner(System.in);
    	
    	boolean condition =true;
    	do {
    		
    		System.out.println("Press 1 to save employee details");
    		System.out.println("Press 2 to update employee details");
    		System.out.println("Press 3 to delete employee details");
    		System.out.println("Press 4 to fetch all employee details");
    		System.out.println("Press 5 to fetch single employee details");
    		System.out.println("Press 6 to exit the application");
    		
    		int choice= scanner.nextInt();
    		
    		switch (choice) {
			case 1:
			{
				final String insert_sql="insert into employee values(?,?,?,?,?)";
				System.out.println("enter the id");
				int id = scanner.nextInt();
				System.out.println("enter employee name");
				String name=scanner.next();
				System.out.println("enter employee role");
				String role=scanner.next();
				System.out.println("enter employee email");
				String email=scanner.next();
				System.out.println("enter employee salary");
				double salary=scanner.nextDouble();
				
				int count=jdbcTemplate.update(insert_sql,id,name,role,email,salary);
				
				if(count>0)
					System.out.println("employee details saved successfully");
				else
					System.out.println("employee details not saved due to some error");
				break;
			}
			
			case 2:
			{	System.out.println("enter 1 to update emplpoyee name");
				System.out.println("enter 2 to update emplpoyee role");
				System.out.println("enter 3 to update emplpoyee email");
				System.out.println("enter 4 to update emplpoyee salary");
				System.out.println("enter 5 to update all details on an employee");
				System.out.println("enter choice for updating");
				int updatechoice=scanner.nextInt();
				//boolean updatedChoice=true;
					switch (updatechoice) {
					case 1:
						{
							final String updateName_sql="update employee set emp_name=? where emp_id=?";
							System.out.println("enter the id");
							int id=scanner.nextInt();
							System.out.println("enter the updated name");
							String updatedName=scanner.next();
							
							int count=jdbcTemplate.update(updateName_sql,updatedName,id);
							System.out.println((count > 0) ? "employee name updated successfully" : "employee name not updated id not found");
							continue;
						}
					case 2:
						{
		
							final String updateName_sql="update employee set emp_role=? where emp_id=?";
							System.out.println("enter the id");
							int id=scanner.nextInt();
							System.out.println("enter the updated role");
							String updatedrole=scanner.next();
							
							int count=jdbcTemplate.update(updateName_sql,updatedrole,id);
							System.out.println((count > 0) ? "employee role updated successfully" : "employee role not updated id not found");
							continue;
							
						}
					case 3:
						{
							final String updateName_sql="update employee set emp_email=? where emp_id=?";
							System.out.println("enter the id");
							int id=scanner.nextInt();
							System.out.println("enter the updated email");
							String updatedEmail=scanner.next();
							
							int count=jdbcTemplate.update(updateName_sql,updatedEmail,id);
							System.out.println((count > 0) ? "employee email updated successfully" : "employee email not updated id not found");
							continue;
							
						}
					case 4:
						{
							final String updateName_sql="update employee set emp_salary=? where emp_id=?";
							System.out.println("enter the id");
							int id=scanner.nextInt();
							System.out.println("enter the updated salary");
							double updatedsalary=scanner.nextDouble();
							
							int count=jdbcTemplate.update(updateName_sql,updatedsalary,id);
							System.out.println((count > 0) ? "employee salary updated successfully" : "employee salary not updated id not found");
		
							continue;
						}
					case 5:
						{
							final String update_sql="update employee set emp_name=?,emp_role=?,emp_email=?,emp_salary=? where emp_id=?";
							System.out.println("enter the id");
							int id=scanner.nextInt();
							System.out.println("enter the updated name");
							String updatedName=scanner.next();
							System.out.println("enter the updated role");
							String updatedrole=scanner.next();
							System.out.println("enter the updated email");
							String updatedEmail=scanner.next();
							System.out.println("enter the updated salary");
							double updatedsalary=scanner.nextDouble();
							
							int count=jdbcTemplate.update(update_sql,updatedName,updatedrole,updatedEmail,updatedsalary,id);
							System.out.println((count > 0) ? "employee updated successfully" : "employee updated id not found");
		
							continue;
						}
					default:
						{
							System.out.println("enter the valid updating choice");
							continue;
						}
				  }
				}
			case 3:
				{
					String delete_sql="delete from employee where emp_id=?";
					System.out.println("enter the id");
					int id=scanner.nextInt();
					
					int count=jdbcTemplate.update(delete_sql,id);
					System.out.println((count > 0) ? "employee deleted successfully" : "employee not deleted id not found");
					break;
				}
			case 4:
				{
					final String selectAll_query="select * from employee";
					List<Employee> employees=jdbcTemplate.query(selectAll_query, new EmployeeRowMapper());
					for( Employee emp:employees)
					{
						System.out.println("Employee ID : "+emp.getId());
						System.out.println("Employee Name : "+emp.getName());
						System.out.println("Employee Role : "+emp.getRole());
						System.out.println("Employee emial : "+emp.getEmail());
						System.out.println("Employee salary : "+emp.getSalary());
						System.out.println("===========================================");
					}
					break;
				}	
			case 5:
				{
					final String select_sql="select * from employee where emp_id=?";
					System.out.println("enter the id");
					int id=scanner.nextInt();
					Employee emp=jdbcTemplate.queryForObject(select_sql, new EmployeeRowMapper(),id);
					System.out.println("Employee ID : "+emp.getId());
					System.out.println("Employee Name : "+emp.getName());
					System.out.println("Employee Role : "+emp.getRole());
					System.out.println("Employee emial : "+emp.getEmail());
					System.out.println("Employee salary : "+emp.getSalary());
					
					break;
				}
			case 6:
				{
					condition=false;
					System.out.println("thankyou visit again");
					break;
				}
			default:
				{
					System.out.println("please enter the valid choice");
					break;
				}
			}
		} while (condition);
    }
   
}
