package com.technoidentity.agastya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.technoidentity.agastya.helper.JwtUtil;
import com.technoidentity.agastya.model.CustomUserDetails;
import com.technoidentity.agastya.model.JwtRequest;
import com.technoidentity.agastya.model.JwtResponce;
import com.technoidentity.agastya.model.User;
import com.technoidentity.agastya.model.UserDetUser;
import com.technoidentity.agastya.service.CustomUserDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@ResponseBody
@RequestMapping("/agastya")
public class JwtController {

	
	 @org.springframework.beans.factory.annotation.Autowired(required=true)
	  private BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailService customuserdetailsservice;

	@Autowired
	private JwtUtil jwtutil;

	 @Operation(summary = "This Method is Use to validate the user from database and also provide the token")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Successfully User is validate from Database and send the token with User Details ", content = { 
						@Content(mediaType = "application/json",schema =@Schema(implementation =JwtResponce.class)) }),

				@ApiResponse(responseCode = "401", description = "Bad Credential User are requesting", content = {
						@Content(mediaType = "application/json",schema =@Schema(implementation =String.class))}) })
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest ) throws Exception{
        
		System.out.println("Hello Controller");
		System.out.println(jwtRequest.toString()); 
		try {
           System.out.println("Hi");
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));	
		 System.out.println("Hi");
		}
		
		catch(Exception e) {
			e.printStackTrace();
			 throw new  BadCredentialsException("Not Valid User");
		}

		//fine are go a head
		CustomUserDetails userDetails	=this.customuserdetailsservice.loadUserByUsername(jwtRequest.getUsername());
		System.out.println("Hello token generater-1");
         String token=this.jwtutil.generateToken(userDetails);
         System.out.println("Hello token generater-2");
         System.out.println(token);
         //prepard Responce
         User u=userDetails.getUser();
         UserDetUser user=new UserDetUser();
         user.setId(u.getId());
         user.setName(u.getUsername());
         user.setEmail(u.getEmail());
         user.setPhoneNo(u.getPhoneNo());
         user.setRole(u.getRole());
         user.setPhoneExt(u.getPhoneExt());
         
         
		return ResponseEntity.ok(new JwtResponce(token,user));
	}
}
