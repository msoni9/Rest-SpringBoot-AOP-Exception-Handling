package com.candidjava.spring.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Salaryinfo")
@XmlRootElement
public class Salary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long salId;

	@Column(name = "salary")
	private Double salary;

	public long getSalId() {
		return salId;
	}

	public void setSalId(long salId) {
		this.salId = salId;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Salary(long salId, Double salary) {
		super();
		this.salId = salId;
		this.salary = salary;
	}

	public Salary() {
	}

}
