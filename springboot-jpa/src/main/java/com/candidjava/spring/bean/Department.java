package com.candidjava.spring.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Departmentinfo")
@XmlRootElement
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long deptId;

	@Column(name = "department")
	private String department;

	@Column(name = "departmentLocation")
	private String departmentLocation;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<User> users;

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartmentLocation() {
		return departmentLocation;
	}

	public void setDepartmentLocation(String departmentLocation) {
		this.departmentLocation = departmentLocation;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Department() {
	}

	public Department(long deptId, String department, String departmentLocation, List<User> users) {
		super();
		this.deptId = deptId;
		this.department = department;
		this.departmentLocation = departmentLocation;
		this.users = users;
	}

}
