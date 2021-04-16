package com.technoidentity.agastya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.technoidentity.agastya.model.User1;
import com.technoidentity.agastya.repositery.UserRepositery1;

@Service
public class CustomUserDetailService1 {

	
	@Autowired(required = true)
	  UserRepositery1 repository;
	
	public User1 getUserDetails(String phoneNo)  {
		
		
		   User1 user=repository.findByPhoneNo(phoneNo);
		 
		   System.out.println(user.toString());
		  
		   return user;
		}
	
	public User1 getUserDetails1(int otp) throws Exception  {
		User1 user=null;
	  try {
		  user=repository.findByOtp(otp);
		   
		
	}catch(Exception e) {
		
		throw new Exception();
	  
	}
	 return user;
	}
	
	
	

}
