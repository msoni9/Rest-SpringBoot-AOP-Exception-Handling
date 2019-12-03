package com.candidjava.spring.service;

import java.util.List;

import com.candidjava.spring.bean.Salary;

public interface SalaryService {

	public void addSalary(Salary salary);

	public List<Salary> getSalary();

	public Salary getSalaryfindBySalId(long salId);

	public Salary update(Salary salary, long l);

	public void deleteSalaryBySalId(long salId);

}
