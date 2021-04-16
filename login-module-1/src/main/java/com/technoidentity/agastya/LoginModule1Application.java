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

@SpringBootApplication
public class LoginModule1Application implements CommandLineRunner {
	
	@org.springframework.beans.factory.annotation.Autowired(required=true)
	  private BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	@Autowired
	private UserRepositery repositery;

	public static void main(String[] args) {
		SpringApplication.run(LoginModule1Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		//repositery.insert(new User(1,"prince",bCryptPasswordEncoder.encode("12345"),"pk@gmail.com","Doctor","7415520049","+91",12));
	    //repositery.insert(new User(2,"silu",bCryptPasswordEncoder.encode("123"),"silu@gmail.com","nurse","9131169055","+91",123));
		
	    //repositery.insert(new User(3,"kumar",bCryptPasswordEncoder.encode("1234"),"ku@gmail.com","caretaker","7415520049","+91",12));
	    //repositery.insert(new User(4,"sidharth",bCryptPasswordEncoder.encode("12"),"sid@gmail.com","Admin","9131169055","+91",123));
		repositery.insert(new User(5,"prince",bCryptPasswordEncoder.encode("12345"),"pk@gmail.com","Doctor","7415520049","+91",12));
		
		List<User> user=repositery.findAll();
		
		Iterator<User> itr=user.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		
	} 

}
