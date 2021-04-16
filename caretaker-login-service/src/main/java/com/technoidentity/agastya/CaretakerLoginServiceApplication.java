package com.technoidentity.agastya;



import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.technoidentity.agastya.model.User;
import com.technoidentity.agastya.repositery.UserRepositery;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info=@Info(title = "Login API for Patient Caretaker ",version = "1.0.0",description = "This API for patient caretaker to validate User"))
@SpringBootApplication
public class CaretakerLoginServiceApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepositery repositery;
	
	public static void main(String[] args) {
		SpringApplication.run(CaretakerLoginServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		      
		      // repositery.insert(new User(1,"prince","pk@gmail.com","caretaker","7415520049","+91",45));
		      // repositery.insert(new User(2,"silu","sk@gmail.com","caretaker","9131169055","+91",45));
				
				
				
				List<User> user=repositery.findAll();
				
				Iterator<User> itr=user.iterator();
				while(itr.hasNext()) {
					System.out.println(itr.next());
				}
				
			}
	
		
	
	
		
	


}
