package com.candidjava.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.candidjava.spring.bean.Salary;
import com.candidjava.spring.repository.SalaryRepository;

@Service
@Transactional
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	private SalaryRepository salaryRepository;

	@Override
	public void addSalary(Salary salary) {

		salaryRepository.save(salary);

	}

	@Override
	public List<Salary> getSalary() {
		List<Salary> salaryList = (List<Salary>) salaryRepository.findAll();

		if (salaryList.size() > 0) {
			return salaryList;
		} else {
			return new ArrayList<Salary>();
		}
	}

	@Override
	public Salary getSalaryfindBySalId(long salId) {
		Optional<Salary> salary = salaryRepository.findBySalId(salId);

		if (salary.isPresent()) {
			return salary.get();
		}
		return null;
	}

	@Override
	public Salary update(Salary salary, long l) {
		Optional<Salary> salaryFetch = salaryRepository.findBySalId(l);

		if (salaryFetch.isPresent()) {
			Salary newSalary = salaryFetch.get();
			newSalary.setSalary(salary.getSalary());
			return salaryRepository.save(newSalary);
		}
		return null;
	}

	@Override
	public void deleteSalaryBySalId(long salId) {

		Optional<Salary> salary = salaryRepository.findBySalId(salId);

		if (salary.isPresent()) {
			salaryRepository.delete(salId);
		}

	}

}
