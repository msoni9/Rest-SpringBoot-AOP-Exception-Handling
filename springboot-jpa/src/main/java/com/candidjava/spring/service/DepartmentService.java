package com.candidjava.spring.service;

import java.util.List;

import com.candidjava.spring.bean.Department;

public interface DepartmentService {

	public void createDepartment(Department department);

	public List<Department> getDepartment();

	public Department getDepartmentfindById(long id);

}
