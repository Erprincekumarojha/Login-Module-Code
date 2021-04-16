package com.technoidentity.agastya.model;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value="LoginUser")
@Component
public class User {
	
	@Id
	private long id;
	
	//Not Duplicate
	@Indexed(unique=true)
    private String username;
	private String password;
	
	//Not Duplicate
	@Indexed(unique=true)
	private String email;
	private String role;
	
	//Not Duplicate
	@Indexed(unique=true)
	private String phoneNo;
	private String phoneExt;
	private int otp;
	
	
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}
	

}
