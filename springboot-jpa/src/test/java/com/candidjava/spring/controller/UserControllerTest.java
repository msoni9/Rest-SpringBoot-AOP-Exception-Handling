package com.candidjava.spring.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.candidjava.spring.bean.User;
import com.candidjava.spring.controlleradvice.UserServiceExceptionAdvice;
import com.candidjava.spring.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService service;

	@InjectMocks
	private UserController userController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).setControllerAdvice(new UserServiceExceptionAdvice())
				.build();
	}

	Gson gson = new Gson();

	@Test
	public void shouldGetAllUserWithGetAPIinJson() throws Exception {
		List<User> list = new ArrayList<>();
		User empOne = new User(1, "Dubai", "Amit");
		User empTwo = new User(2, "Pakistan", "rajnath");
		User empThree = new User(3, "Bangladesh", "modi");

		list.add(empOne);
		list.add(empTwo);
		list.add(empThree);

		when(service.getUser()).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/get").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		// System.out.println(result.getResponse().getContentAsString());

		verify(service, Mockito.times(2)).getUser();
	}

	@Test
	public void shouldGetAllUserWithGetAPIinXML() throws Exception {
		List<User> list = new ArrayList<>();
		User empOne = new User(1, "China", "Tom");
		User empTwo = new User(2, "Japan", "Din");
		User empThree = new User(3, "Nepa", "Harry");

		list.add(empOne);
		list.add(empTwo);
		list.add(empThree);
		when(service.getUser()).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/get").accept(MediaType.APPLICATION_XML);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		// System.out.println(result.getResponse().getContentAsString());

		verify(service, Mockito.times(2)).getUser();
	}

	@Test
	public void shouldCreateUserWithPostAPI() throws Exception {
		User empOne = new User(1, "Prague", "Tom");
		/**
		 * we can use gson api to convert object into json object - String
		 * postBody = gson.toJson(empOne);
		 */
		ObjectMapper objeMapper = new ObjectMapper();
		String postBody = objeMapper.writeValueAsString(empOne);

		doNothing().when(service).createUser(empOne);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/create").content(postBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());

		verify(service).createUser(empOne);
	}

	@Test
	public void shouldUpdateUserWithPutAPI() throws Exception {
		User empOne = new User(1, "China", "Tom");
		String putBody = gson.toJson(empOne);

		when(service.getUserfindById(1)).thenReturn(empOne);
		when(service.update(empOne, 1)).thenReturn(empOne);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/user/update").content(putBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());

		verify(service).getUserfindById(1);
		verify(service).update(empOne, 1);
	}

	@Test
	public void shouldDeleteUserWithDeleteAPI() throws Exception {
		User empOne = new User(1, "North Korea", "Rakesh");

		when(service.getUserfindById(1)).thenReturn(empOne);
		doNothing().when(service).deleteUserById(1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());

		verify(service).getUserfindById(1);
		verify(service).deleteUserById(1);
	}

	@Test
	public void shouldPartiallyUpdateUserWithPatchAPI() throws Exception {
		User empOne = new User(1, "Thiland", "Sorav");
		String patchBody = gson.toJson(empOne);

		when(service.getUserfindById(1)).thenReturn(empOne);
		when(service.updatePartially(empOne, 1)).thenReturn(empOne);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/user/1").content(patchBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());

		verify(service).getUserfindById(1);
		verify(service).updatePartially(empOne, 1);
	}

	@Test
	public void shouldReturnUserWithIDUsingGetAPI() throws Exception {
		User empOne = new User(5, "Tokiyo", "Pranali");

		when(service.getUserfindById(5)).thenReturn(empOne);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/5").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());

		verify(service).getUserfindById(5);
	}

	@Test
	public void shouldReturnExceptionWithIDUsingGetAPI() throws Exception {
		User empOne = new User(5, "Tokiyo", "Pranali");

		when(service.getUserfindById(5)).thenReturn(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/5").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());

	}

	@Test
	public void shouldReturnExceptionUsingGetAPI() throws Exception {
		List<User> list = new ArrayList<>();
		User empOne = new User(5, "Tokiyo", "Pranali");
		User empTwo = new User(5, "Tokiyo", "Pranali");

		list.add(empOne);
		list.add(empTwo);

		when(service.getUser()).thenReturn(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/get").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());

	}

	@Test
	public void shouldReturnExceptionwithPutAPI() throws Exception {
		User empOne = new User(1, "China", "Tom");
		String putBody = gson.toJson(empOne);

		when(service.getUserfindById(1)).thenReturn(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/user/update").content(putBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());

	}

	@Test
	public void shouldReturnExceptionwithDeleteAPI() throws Exception {
		User empOne = new User(1, "North Korea", "Rakesh");

		when(service.getUserfindById(1)).thenReturn(null);
		doNothing().when(service).deleteUserById(1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());

	}

	@Test
	public void shouldReturnExceptionWhenPartiallyUpdatingUserWithPatchAPI() throws Exception {
		User empOne = new User(7, "Westindies", "Baghira");
		String patchBody = gson.toJson(empOne);

		when(service.getUserfindById(1)).thenReturn(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/user/1").content(patchBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());

	}

}
