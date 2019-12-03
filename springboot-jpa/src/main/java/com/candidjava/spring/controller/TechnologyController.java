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

import com.candidjava.spring.bean.Technology;
import com.candidjava.spring.exception.RecordNotFoundException;
import com.candidjava.spring.service.TechnologyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Swagger2RestController", description = "REST APIs related to Technology Entity!!!!")
@RestController
@RequestMapping(value = { "/technology" })
public class TechnologyController {
	private static final Logger LOGGER = Logger.getLogger(TechnologyController.class);

	@Autowired
	private TechnologyService technologyService;

	@ApiOperation(value = "Get Technology of user in the System ", response = Iterable.class, tags = "getTechnology")
	@GetMapping(value = "/{techId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Technology> getTechnologyByTechId(@PathVariable("techId") long techId) {
		LOGGER.info("Fetching User's technology with id " + techId);
		Technology technology = technologyService.getTechnologyfindByTechId(techId);
		if (technology == null) {
			throw new RecordNotFoundException("Invalid Technology id :" + techId);
		}
		return new ResponseEntity<Technology>(technology, HttpStatus.OK);
	}

	@ApiOperation(value = "add technology of the user in the System ", response = Iterable.class, tags = "addTechnology")
	@PostMapping(value = "/add", headers = "Accept=application/json")
	public ResponseEntity<Void> addTechnology(@RequestBody Technology technology, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Adding Salary " + technology.getTechnology());
		technologyService.addTechnology(technology);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/technology/{techId}").buildAndExpand(technology.getTechId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get list of user's technology in the System ", response = Iterable.class, tags = "getTechnologies")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping(value = "/get", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Technology> getAllUsersTechnology() {

		List<Technology> TechnologyList = technologyService.getTechnology();
		if (TechnologyList == null) {
			throw new RecordNotFoundException("Salaries are not available in database");
		}

		return TechnologyList;

	}

	@ApiOperation(value = "Modify/Update user's technology in the System ", response = Iterable.class, tags = "updateTechnology")
	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateTechnology(@RequestBody Technology newTechnology) {
		LOGGER.info("sd");
		Technology technology = technologyService.getTechnologyfindByTechId(newTechnology.getTechId());
		if (technology == null) {
			throw new RecordNotFoundException("Invalid technology id :" + technology.getTechId());
		}
		technologyService.update(newTechnology, newTechnology.getTechId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@ApiOperation(value = "Delete user's technology in the System ", response = Iterable.class, tags = "deleteTechnology")
	@DeleteMapping(value = "/{techId}", headers = "Accept=application/json")
	public ResponseEntity<Technology> deleteTechnology(@PathVariable("techId") long techId)
			throws RecordNotFoundException {
		Technology technology = technologyService.getTechnologyfindByTechId(techId);
		if (technology == null) {
			throw new RecordNotFoundException("Invalid Technology id :" + technology.getTechId());
		}
		technologyService.deleteTechnologyByTechId(techId);
		return new ResponseEntity<Technology>(HttpStatus.NO_CONTENT);
	}

}
