package com.technoidentity.agastya;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.technoidentity.agastya.model.User;
import com.technoidentity.agastya.service.CustomUserDetailService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@TestMethodOrder(OrderAnnotation.class)
class CaretakerLoginServiceApplicationTests {

	@Autowired
	CustomUserDetailService customUserDetailService;

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	@Order(1)
	public void smsSubmitTest() throws Exception {

		MvcResult result = mvc.perform(post("/agastya/sendOTP").header("Content-Type", "application/json")
				// .contentType(MediaType.APPLICATION_JSON)
				.content("{\"to\":\"7415520049\"}")).andReturn();
		MockHttpServletResponse resp = result.getResponse();
		assertNotNull(resp.getContentAsString());
		assertEquals(HttpStatus.OK.value(), resp.getStatus());

	}

	@Test
	@Order(2)
	public void smsSubmitTest2() throws Exception {

		MvcResult result = mvc.perform(post("/agastya/sendOTP").header("Content-Type", "application/json")
				// .contentType(MediaType.APPLICATION_JSON)
				.content("{\"to\":\"1234567890\"}")).andReturn();
		MockHttpServletResponse resp = result.getResponse();
		assertNotNull(resp.getContentAsString());
		assertEquals(HttpStatus.NOT_FOUND.value(), resp.getStatus());

	}

	@Test
	@Order(3)
	public void generateTokenTest() throws Exception {

		User user = customUserDetailService.getUserDetails("7415520049");
		int otp = user.getOtp();

		MvcResult result = mvc.perform(post("/agastya/login").header("Content-Type", "application/json")
				 .contentType(MediaType.APPLICATION_JSON)
				.content("{\"otp\":otp}")).andReturn();
		MockHttpServletResponse resp = result.getResponse();
		assertNotNull(resp.getContentAsString());
		//assertEquals(HttpStatus.OK.value(), resp.getStatus());

	}

	@Test
	@Order(4)
	public void generateTokenTest2() throws Exception {

		MvcResult result = mvc.perform(post("/agastya/login").header("Content-Type", "application/json")
				// .contentType(MediaType.APPLICATION_JSON)
				.content("{\"otp\":753508}")).andReturn();
		MockHttpServletResponse resp = result.getResponse();
		assertNotNull(resp.getContentAsString());
		assertEquals(HttpStatus.BAD_REQUEST.value(), resp.getStatus());

	}

	@Test
	@Order(5)
	public void mainSpringBootTest() {

		CaretakerLoginServiceApplication a = new CaretakerLoginServiceApplication();
		String s[] = { "prince", "kumar" };
		a.main(s);

	}

}
