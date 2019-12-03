package com.candidjava.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.candidjava.spring.bean.User;
import com.candidjava.spring.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public void createUser(User user) {

		userRepository.save(user);

	}

	public List<User> getUser() {

		List<User> employeeList = (List<User>) userRepository.findAll();

		if (employeeList.size() > 0) {
			return employeeList;
		} else {
			return new ArrayList<User>();
		}
	}

	public User getUserfindById(long id) {

		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	public User update(User user, long l) {

		Optional<User> employee = userRepository.findById(l);

		if (employee.isPresent()) {
			User newUser = employee.get();
			newUser.setCountry(user.getCountry());
			newUser.setName(user.getName());
			return userRepository.save(newUser);
		}
		return null;

	}

	public void deleteUserById(long id) {

		Optional<User> employee = userRepository.findById(id);

		if (employee.isPresent()) {
			userRepository.delete(id);
		}
	}

	public User updatePartially(User user, long id) {
		Optional<User> employee = userRepository.findById(id);

		if (employee.isPresent()) {
			user.setCountry(user.getCountry());
			return userRepository.save(user);
		}
		return user;
	}

}
