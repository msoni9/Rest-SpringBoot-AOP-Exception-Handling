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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.candidjava.spring.bean.Salary;
import com.candidjava.spring.exception.RecordNotFoundException;
import com.candidjava.spring.service.SalaryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Swagger2RestController", description = "REST APIs related to Salary Entity!!!!")
@RestController
@RequestMapping(value = { "/salary" })
public class SalaryController {

	private static final Logger LOGGER = Logger.getLogger(SalaryController.class);

	@Autowired
	private SalaryService salaryService;

	@ApiOperation(value = "Get salary of user in the System ", response = Iterable.class, tags = "getsalary")
	@GetMapping(value = "/{salId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Salary> getSalaryBySalId(@PathVariable("salId") long salId) {
		LOGGER.info("Fetching User with id " + salId);
		Salary salary = salaryService.getSalaryfindBySalId(salId);
		if (salary == null) {
			throw new RecordNotFoundException("Invalid Salary id :" + salId);
		}
		return new ResponseEntity<Salary>(salary, HttpStatus.OK);
	}

	@ApiOperation(value = "add salary of the user in the System ", response = Iterable.class, tags = "addSalary")
	@PostMapping(value = "/add", headers = "Accept=application/json")
	public ResponseEntity<Void> addSalary(@RequestBody Salary salary, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Adding Salary " + salary.getSalary());
		salaryService.addSalary(salary);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/salary/{salId}").buildAndExpand(salary.getSalId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get list of users in the System ", response = Iterable.class, tags = "getUsers")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping(value = "/get", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Salary> getAllUsersSalary() {

		List<Salary> salaryList = salaryService.getSalary();
		if (salaryList == null) {
			throw new RecordNotFoundException("Salaries are not available in database");
		}

		return salaryList;

	}

	@ApiOperation(value = "Modify/Update user's salary in the System ", response = Iterable.class, tags = "updateSalary")
	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateSalary(@RequestBody Salary newSalary) {
		LOGGER.info("sd");
		Salary salary = salaryService.getSalaryfindBySalId(newSalary.getSalId());
		if (salary == null) {
			throw new RecordNotFoundException("Invalid salary id :" + salary.getSalId());
		}
		salaryService.update(newSalary, newSalary.getSalId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@ApiOperation(value = "Delete user's salary in the System ", response = Iterable.class, tags = "deleteSalary")
	@DeleteMapping(value = "/{salId}", headers = "Accept=application/json")
	public ResponseEntity<Salary> deleteSalary(@PathVariable("salId") long salId) throws RecordNotFoundException {
		Salary salary = salaryService.getSalaryfindBySalId(salId);
		if (salary == null) {
			throw new RecordNotFoundException("Invalid Salary id :" + salary.getSalId());
		}
		salaryService.deleteSalaryBySalId(salId);
		return new ResponseEntity<Salary>(HttpStatus.NO_CONTENT);
	}

}
