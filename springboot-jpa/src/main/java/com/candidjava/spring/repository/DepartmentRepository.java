package com.candidjava.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.candidjava.spring.bean.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

	/**
	 * 
	 * It's important to note that after 'findBy' you have to write exactly same
	 * variable name as you have declared in pojo.. else you'll get error.
	 * 
	 * Note - you can also create findByLocation, because you've declared
	 * variable in pojo as the same 'location'. If you don't make the name same
	 * then you'll get an error
	 * 
	 */
	Optional<Department> findByDeptId(long deptId);

}
