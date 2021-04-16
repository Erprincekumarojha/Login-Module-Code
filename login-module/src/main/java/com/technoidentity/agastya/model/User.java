package com.technoidentity.agastya.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value="UserDetails")
@Component
public class User {
	
	@Id
	private long id;
    private String username;
	private String password;
	private String email;
	private String role;
	private String phoneNo;
	private String phoneExt;

}
