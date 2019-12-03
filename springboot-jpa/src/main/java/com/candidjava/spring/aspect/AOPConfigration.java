package com.candidjava.spring.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.candidjava.spring.service.DepartmentService;
import com.candidjava.spring.service.DepartmentServiceImpl;
import com.candidjava.spring.service.SalaryService;
import com.candidjava.spring.service.SalaryServiceImpl;
import com.candidjava.spring.service.TechnologyService;
import com.candidjava.spring.service.TechnologyServiceImpl;
import com.candidjava.spring.service.UserService;
import com.candidjava.spring.service.UserServiceImpl;

@Configuration
@EnableAspectJAutoProxy
public class AOPConfigration {

	@Bean
	public LogService logService() {
		return new LogService();
	}

	@Bean(name = "userService")
	public UserService userService() {
		return new UserServiceImpl();
	}

	@Bean(name = "departmentService")
	public DepartmentService departmentService() {
		return new DepartmentServiceImpl();
	}

	@Bean(name = "salaryService")
	public SalaryService salaryService() {
		return new SalaryServiceImpl();
	}

	@Bean(name = "technologyService")
	public TechnologyService technologyService() {
		return new TechnologyServiceImpl();
	}

}
