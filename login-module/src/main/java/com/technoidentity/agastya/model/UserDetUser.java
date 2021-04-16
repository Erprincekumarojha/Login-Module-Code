package com.technoidentity.agastya.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetUser {

	private long id;
    private String name;
    private String email;
    private String role;
    private String phoneNo;
    private String phoneExt;
    
}
