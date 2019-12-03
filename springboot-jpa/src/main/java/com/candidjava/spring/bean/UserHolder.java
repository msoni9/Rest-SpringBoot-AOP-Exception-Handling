package com.candidjava.spring.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * we can remove this class and add depency - jackson-data-formaater in pom.xml.
 * It will directly convert list, map or any object to xml. And we won't get any
 * marshalling exception
 */
@XmlRootElement
public class UserHolder {

	List<User> user;

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserHolder [user=" + user + "]";
	}
	
	

}
