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
		DriverManagerDataSource dataSource= new  DriverManagerDataSource();
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
