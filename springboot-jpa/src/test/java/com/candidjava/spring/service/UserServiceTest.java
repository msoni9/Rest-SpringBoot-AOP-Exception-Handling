package com.candidjava.spring.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.candidjava.spring.bean.User;
import com.candidjava.spring.repository.UserRepository;

/**
 * 
 * @author mayank.soni -
 * 
 *         with we are using @RunWith(SpringRunner.class) then we can do
 * @injectMocks UserService userService;
 * 
 *              else if we are using @RunWith(MockitoJUnitRunner.class) then
 *              must have to inject impl mock because it won't create object of
 *              the class
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	User mockUser;

	@Mock
	UserRepository mockUserDAO;

	@Test
	public void shouldReturnTheListOfAllUsers() {
		List<User> list = new ArrayList<>();
		User empOne = new User(1, "Dubai", "Amit");
		User empTwo = new User(2, "Pakistan", "rajnath");
		User empThree = new User(3, "Bangladesh", "modi");

		list.add(empOne);
		list.add(empTwo);
		list.add(empThree);

		when(mockUserDAO.findAll()).thenReturn(list);

		// test
		List<User> empList = userServiceImpl.getUser();

		assertEquals(3, empList.size());
		verify(mockUserDAO, Mockito.times(1)).findAll();
	}

	@Test
	public void shouldCreateUser() {
		User empOne = new User(1, "Switzerland", "shankar");
		when(mockUserDAO.save(empOne)).thenReturn(empOne);

		userServiceImpl.createUser(empOne);

		verify(mockUserDAO, Mockito.times(1)).save(empOne);
	}

	@Test
	public void shouldReturnUserByIdIfPresent() {

		User empOne = new User(1, "Netherland", "Nikhil");
		Optional<User> employee = Optional.of(empOne);

		when(mockUserDAO.findById(1)).thenReturn(employee);

		userServiceImpl.getUserfindById(1);
		verify(mockUserDAO).findById(1);

	}

	@Test
	public void shouldUpdateTheUserWithId() {
		User empOne = new User(2, "hello", "Sneha");
		Optional<User> employee = Optional.of(empOne);

		when(mockUserDAO.findById(2)).thenReturn(employee);
		when(mockUserDAO.save(empOne)).thenReturn(empOne);

		User result = userServiceImpl.update(empOne, 2);
		Assert.assertNotNull(result);

		verify(mockUserDAO).findById(2);
		verify(mockUserDAO).save(empOne);
	}

	@Test
	public void shouldDeleteUserWithId() {
		User empOne = new User(2, "hello", "Sneha");
		Optional<User> employee = Optional.of(empOne);

		when(mockUserDAO.findById(2)).thenReturn(employee);
		doNothing().when(mockUserDAO).delete((long) 2);

		userServiceImpl.deleteUserById(2);

		verify(mockUserDAO).findById(2);
		verify(mockUserDAO).delete((long) 2);
	}

	@Test
	public void shouldPatiallyUpdateUserWithId() {
		User empOne = new User(2, "Namaskar", "Ekta");
		Optional<User> employee = Optional.of(empOne);

		when(mockUserDAO.findById(2)).thenReturn(employee);
		when(mockUserDAO.save(empOne)).thenReturn(empOne);

		User updatePartially = userServiceImpl.updatePartially(empOne, 2);
		Assert.assertNotNull(updatePartially);

		verify(mockUserDAO).findById(2);
		verify(mockUserDAO).save(empOne);
	}

}
