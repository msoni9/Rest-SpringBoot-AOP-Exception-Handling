package com.candidjava.spring.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.candidjava.spring.bean.Department;
import com.candidjava.spring.exception.RecordNotFoundException;
import com.candidjava.spring.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Swagger2RestController", description = "REST APIs related to Student Entity!!!!")
@RestController
@RequestMapping(value = { "/department" })
public class DepartmentController {

	private static final Logger LOGGER = Logger.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService departmentService;

	@ApiOperation(value = "Get department in the System ", response = Iterable.class, tags = "getDepartment")
	@GetMapping(value = "/{deptId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Department> getDepartmentById(@PathVariable("deptId") long deptId) {
		LOGGER.info("Fetching User with id " + deptId);
		Department department = departmentService.getDepartmentfindById(deptId);
		if (department == null) {
			throw new RecordNotFoundException("Invalid Department id :" + deptId);
		}
		return new ResponseEntity<Department>(department, HttpStatus.OK);
	}

	@ApiOperation(value = "Create department in the System ", response = Iterable.class, tags = "createDepartment")
	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createDepartment(@RequestBody Department department, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Creating Department " + department.getDepartment());
		departmentService.createDepartment(department);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/department/{deptId}").buildAndExpand(department.getDeptId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get list of users in the System ", response = Iterable.class, tags = "getUsers")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping(value = "/get", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Department> getAllDepartment() {

		List<Department> department = departmentService.getDepartment();
		if (department == null) {
			throw new RecordNotFoundException("Departments are not available in database");
		}

		return department;

	}

}
