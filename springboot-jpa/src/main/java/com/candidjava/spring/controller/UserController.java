package com.candidjava.spring.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.candidjava.spring.bean.User;
import com.candidjava.spring.bean.UserHolder;
import com.candidjava.spring.exception.RecordNotFoundException;
import com.candidjava.spring.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Swagger2RestController", description = "REST APIs related to Student Entity!!!!")
@RestController
@RequestMapping(value = { "/user" })
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * with getMapping we can use only produces for both json and xml , not
	 * consumes
	 */
	@ApiOperation(value = "Get user in the System ", response = Iterable.class, tags = "getUser")
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		LOGGER.info("Fetching User with id " + id);
		User user = userService.getUserfindById(id);
		if (user == null) {
			throw new RecordNotFoundException("Invalid User id :" + id);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/**
	 * 
	 * We can use produces with getMapping & we can use consumes with getMapping
	 * 
	 * consumes = { MediaType.APPLICATION_JSON_VALUE,
	 * MediaType.APPLICATION_XML_VALUE }
	 * 
	 * produces = { MediaType.APPLICATION_JSON_VALUE,
	 * MediaType.APPLICATION_XML_VALUE }
	 * 
	 */

	@ApiOperation(value = "Create user in the System ", response = Iterable.class, tags = "createUser")
	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Creating User " + user.getName());
		userService.createUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get list of users in the System ", response = Iterable.class, tags = "getUsers")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping(value = "/get", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public UserHolder getAllUser() {

		UserHolder userHolder = new UserHolder();
		List<User> user = userService.getUser();
		if (user == null) {
			throw new RecordNotFoundException("Users are not available in database");
		}
		userHolder.setUser(userService.getUser());

		return userHolder;

	}

	@ApiOperation(value = "Modify/Update user in the System ", response = Iterable.class, tags = "updateUser")
	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateUser(@RequestBody User currentUser) {
		LOGGER.info("sd");
		User user = userService.getUserfindById(currentUser.getId());
		if (user == null) {
			throw new RecordNotFoundException("Invalid Userr id :" + user.getId());
		}
		userService.update(currentUser, currentUser.getId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@ApiOperation(value = "Delete user in the System ", response = Iterable.class, tags = "deleteUser")
	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) throws RecordNotFoundException {
		User user = userService.getUserfindById(id);
		if (user == null) {
			throw new RecordNotFoundException("Invalid User id :" + user.getId());
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Modify/update user in the System ", response = Iterable.class, tags = "patchUser")
	@PatchMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<User> updateUserPartially(@PathVariable("id") long id, @RequestBody User currentUser) {
		User user = userService.getUserfindById(id);
		if (user == null) {
			throw new RecordNotFoundException("Invalid User id :" + user.getId());
		}
		User usr = userService.updatePartially(currentUser, id);
		return new ResponseEntity<User>(usr, HttpStatus.OK);
	}
}
