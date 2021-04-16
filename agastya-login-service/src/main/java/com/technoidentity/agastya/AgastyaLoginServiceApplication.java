package com.technoidentity.agastya;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.technoidentity.agastya.model.User;
import com.technoidentity.agastya.repositery.UserRepositery;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(info=@Info(title = "Login Screen ",version = "1.0.0",description = "This API for Login user also Validate User"))
@SpringBootApplication
public class AgastyaLoginServiceApplication implements CommandLineRunner {

	 @org.springframework.beans.factory.annotation.Autowired(required=true)
	  private BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	@Autowired
	private UserRepositery repositery;
	
	public static void main(String[] args) {
		SpringApplication.run(AgastyaLoginServiceApplication.class, args);
		System.out.println("My project is started..?");
	}

	@Override
	public void run(String... args) throws Exception {
		//repositery.insert(new User(1,"prince",bCryptPasswordEncoder.encode("12345"),"pk@gmail.com","Doctor","9131169055","91"));
	   // repositery.insert(new User(2,"silu",bCryptPasswordEncoder.encode("123"),"silu@gmail.com","nurse","7405520049","1"));
		
		
		List<User> user=repositery.findAll();
		
		Iterator<User> itr=user.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		
	}

 

}
