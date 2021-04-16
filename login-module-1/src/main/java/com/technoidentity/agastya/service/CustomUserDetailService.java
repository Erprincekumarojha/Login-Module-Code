package com.technoidentity.agastya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.technoidentity.agastya.model.CustomUserDetails;
import com.technoidentity.agastya.model.User;
import com.technoidentity.agastya.repositery.UserRepositery;

@Service
public class CustomUserDetailService  implements UserDetailsService{

	
	@Autowired(required = true)
	private  UserRepositery repository;
	
	
	@Override
	public CustomUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

	
	 final   User user=this.repository.findByUsername(userName); 
		
		if(user==null) {
			throw  new UsernameNotFoundException("User Not Found.!");
		}
		else {
			return new CustomUserDetails(user);
		}
		
		//		
//	   if(userName.equals("pk")) {
//		   
//		 return new User("pk","pk12345", new ArrayList<>());
//		 
//		}else {
//			throw new UsernameNotFoundException("User Name Not found..?");
//		}
//		
//	}
	
	}
	

}
