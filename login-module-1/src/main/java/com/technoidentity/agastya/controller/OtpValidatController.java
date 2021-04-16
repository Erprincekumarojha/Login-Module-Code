package com.technoidentity.agastya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.technoidentity.agastya.helper.JwtUtil1;
import com.technoidentity.agastya.model.JwtResponce1;
import com.technoidentity.agastya.model.TempOTP;
import com.technoidentity.agastya.model.User;
import com.technoidentity.agastya.model.UserDetailsResponse;
import com.technoidentity.agastya.service.CustomUserDetailService1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@CrossOrigin(origins="http://localhost:3000")
@RestController
@ResponseBody
@RequestMapping("/agastya")
public class OtpValidatController {

	
	//It is Required Don't Remove this 
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailService1 customUserDetailService;

	@Autowired
	private JwtUtil1 jwtutil;

	 @Operation(summary = "This Method is Use to validate the OTP If OTP is valid then send Response Token with USER Details")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Successfully OTP is validated ", content = { 
						@Content(mediaType = "application/json",schema =@Schema(implementation =JwtResponce1.class)) }),

				@ApiResponse(responseCode = "400", description = "Invalid OTP", content = {
						@Content(mediaType = "application/json",schema =@Schema(implementation =String.class))}) })
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody TempOTP otp ) throws Exception{
        ResponseEntity<?> responce = null;
		System.out.println("Hello Controller");
		System.out.println(otp.toString());
		//validate the OTP given by endUser
		try {
			//Fetch User From DB with OTP
			User userDetail=customUserDetailService.getUserDetails1(otp.getOtp());
			
			
			//Validate the OTP
			int  serverOTP=userDetail.getOtp();
			
			if(serverOTP==otp.getOtp()) {
			
				
				
			//Generate Token based on Mobile No
				 System.out.println("Hello token generater-1");
		         String token=this.jwtutil.generateToken(userDetail);
		         System.out.println("Hello token generater-2");
		         System.out.println(token);
		         
		         
		        //Make the Response Object
		         UserDetailsResponse user=new UserDetailsResponse();
		         user.setId(userDetail.getId());
		         user.setName(userDetail.getUsername());
		         user.setEmail(userDetail.getEmail());
		         user.setRole(userDetail.getRole());
		         user.setPhoneNo(userDetail.getPhoneNo());
		         user.setPhoneExt(userDetail.getPhoneExt());
		          
		          
				responce=ResponseEntity.ok(new JwtResponce1(token,user));
			}
			else {
				System.out.println("OTP is Not Valid");
				throw new Exception();
			}
			
		}
		catch(Exception e) {
			 e.printStackTrace();
			return new  ResponseEntity<String>("Invalid OTP",HttpStatus.BAD_REQUEST);
		}
		return responce;
	}
}
