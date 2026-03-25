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
