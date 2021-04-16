package com.technoidentity.agastya.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class JwtResponce {
	
	
	private String token;
    private UserDetailsResponse   user;
	
	
	

}
