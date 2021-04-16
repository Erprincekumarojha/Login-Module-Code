package com.technoidentity.agastya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technoidentity.agastya.model.User;
import com.technoidentity.agastya.repositery.UserRepositery;

@Service
public class CustomUserDetailService {

	
	@Autowired(required = true)
	  UserRepositery repository;
	
	public User getUserDetails(String phoneNo) {
		
		   User user=repository.findByPhoneNo(phoneNo);
		 
		   System.out.println(user.toString());
		  
		   return user;
		}
	
	public User getUserDetails1(int otp) {
		
		   User user=repository.findByOtp(otp);
		    return user;
		}
	
	
	

}
