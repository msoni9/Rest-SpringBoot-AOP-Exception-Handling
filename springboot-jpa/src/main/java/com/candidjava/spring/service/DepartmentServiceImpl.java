package com.candidjava.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.candidjava.spring.bean.Department;
import com.candidjava.spring.repository.DepartmentRepository;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public void createDepartment(Department department) {
		departmentRepository.save(department);
	}

	@Override
	public List<Department> getDepartment() {
		List<Department> departmentList = (List<Department>) departmentRepository.findAll();

		if (departmentList.size() > 0) {
			return departmentList;
		} else {
			return new ArrayList<Department>();
		}
	}

	@Override
	public Department getDepartmentfindById(long depId) {
		Optional<Department> department = departmentRepository.findByDeptId(depId);

		if (department.isPresent()) {
			return department.get();
		}
		return null;

	}

}
