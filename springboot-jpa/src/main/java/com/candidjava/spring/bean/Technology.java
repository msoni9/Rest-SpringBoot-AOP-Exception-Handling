package com.candidjava.spring.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Technologyinfo")
@XmlRootElement
public class Technology {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long techId;

	@Column(name = "technology")
	private String technology;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	public long getTechId() {
		return techId;
	}

	public void setTechId(long techId) {
		this.techId = techId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Technology(long techId, String technology, User user) {
		super();
		this.techId = techId;
		this.technology = technology;
		this.user = user;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public Technology() {
	}

}
