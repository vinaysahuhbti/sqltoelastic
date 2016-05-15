package com.vs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class S2MApplication {

	@Autowired
	DataSource datasource;
	
	@Bean
	public JdbcTemplate getJdBCTemplate(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(datasource);
		return jdbcTemplate;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(S2MApplication.class, args);
	}
}
