package com.technoidentity.agastya.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technoidentity.agastya.model.JwtResponce;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/agastya")
public class Home {

	
	 @Operation(summary = "This method is use for Testing. with the token you are able to access API method or NOT")
		@ApiResponses(value = {
				
				@ApiResponse(responseCode = "200", description = "We are able to access the method", content = {
						@Content(mediaType = "application/json",schema =@Schema(implementation =String.class))}) })
	
	@GetMapping("/welcome")
	public ResponseEntity<String> Welocme() {
		
		return new ResponseEntity<String>("Welcome to My DashBoard",HttpStatus.OK);
	}
	

}
