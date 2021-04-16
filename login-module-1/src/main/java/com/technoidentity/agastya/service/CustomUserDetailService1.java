package com.technoidentity.agastya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.technoidentity.agastya.model.User;
import com.technoidentity.agastya.repositery.UserRepositery;

@Service
public class CustomUserDetailService1 {

	
	@Autowired(required = true)
	  UserRepositery repository;
	
	public User getUserDetails(String phoneNo)  {
		System.out.println("Hi DB");
		
		   User user=repository.findByPhoneNo(phoneNo);
		   System.out.println("Hi DB");
		   System.out.println(user.toString());
		  
		   return user;
		}
	
	public User getUserDetails1(int otp) throws Exception  {
		User user=null;
	  try {
		  user=repository.findByOtp(otp);
		   
		
	}catch(Exception e) {
		
		throw new Exception();
	  
	}
	 return user;
	}
	
	
	

}
