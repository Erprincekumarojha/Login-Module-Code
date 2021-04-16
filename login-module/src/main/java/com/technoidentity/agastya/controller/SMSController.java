package com.technoidentity.agastya.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.technoidentity.agastya.model.SmsPojo;
import com.technoidentity.agastya.model.User;
import com.technoidentity.agastya.model.User1;
import com.technoidentity.agastya.repositery.UserRepositery;
import com.technoidentity.agastya.repositery.UserRepositery1;
import com.technoidentity.agastya.service.CustomUserDetailService;
import com.technoidentity.agastya.service.CustomUserDetailService1;
import com.technoidentity.agastya.service.SmsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/agastya")
public class SMSController {

	@Autowired
	SmsService service;

	@Autowired
	UserRepositery1 repository;

	@Autowired
	CustomUserDetailService1 customUserDetailService;

	@Autowired
	private SimpMessagingTemplate webSocket;

	private final String TOPIC_DESTINATION = "/lesson/sms";

	
	
	 @Operation(summary = "This Method is Use to validate the Mobile No. If Mobile No is available In DB then Send OTP 6-digit ")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Successfully OTP Send Given Mobile No ", content = { 
						@Content(mediaType = "application/json",schema =@Schema(implementation =Boolean.class)) }),

				@ApiResponse(responseCode = "404", description = "Invalid Mobile No. It is not available in DB", content = {
						@Content(mediaType = "application/json",schema =@Schema(implementation =Boolean.class))}) })
	
	
	
	// You can send SMS in verified Number
	@RequestMapping(value = "/sendOTP", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> smsSubmit(@RequestBody SmsPojo sms) {
		try {
			System.out.println(sms.getTo());
			// verify the Number is Available in DB or NOT
			User1 user = customUserDetailService.getUserDetails(sms.getTo());

			if (user != null) {
				System.out.println("hello");
				// If Number is available then send OTP in Mobile Number

				int min = 100000;
				int max = 999999;
				int number = (int) (Math.random() * (max - min + 1) + min);

				// save the Generated OTP in your Static variable
				
				user.setOtp(number);
				repository.save(user);
				

				String msg = "Your OTP - " + number
						+ " please verify this OTP in your Application by Er Prince kumar Technoidentity.com";
                String phoneNo=user.getPhoneExt()+sms.getTo();
				service.send(phoneNo, msg);

				

			} 

		} catch (Exception e) {

			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}

		webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": SMS has been sent!: " + sms.getTo());

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	

	@RequestMapping(value = "/smscallback", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void smsCallback(@RequestBody MultiValueMap<String, String> map) {
		service.receive(map);
		webSocket.convertAndSend(TOPIC_DESTINATION,
				getTimeStamp() + ": Twilio has made a callback request! Here are the contents: " + map.toString());
	}

	private String getTimeStamp() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	}

}
