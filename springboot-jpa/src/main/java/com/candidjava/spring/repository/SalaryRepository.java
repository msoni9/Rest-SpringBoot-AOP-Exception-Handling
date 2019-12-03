package com.candidjava.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.candidjava.spring.bean.Salary;

public interface SalaryRepository extends CrudRepository<Salary, Long> {

	Optional<Salary> findBySalId(long deptId);

}
