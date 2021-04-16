package com.technoidentity.agastya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.technoidentity.agastya.model.User;
import com.technoidentity.agastya.repositery.UserRepositery;


@RestController
@ResponseBody
@RequestMapping("/agastya")
public class RegisterController {

	 @org.springframework.beans.factory.annotation.Autowired(required=true)
	  private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepositery repository;


	@RequestMapping(value="/register",method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user){
		System.out.println("Hi Regster Contreoller");
		try { 
			//for encode the password
             user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			
             repository.insert(user);
			return new ResponseEntity<String>("Registation Successful.?",HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
}
