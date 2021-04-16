package com.technoidentity.agastya.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.technoidentity.agastya.helper.JwtUtil;
import com.technoidentity.agastya.helper.JwtUtil1;
import com.technoidentity.agastya.model.User1;
import com.technoidentity.agastya.service.CustomUserDetailService;
import com.technoidentity.agastya.service.CustomUserDetailService1;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailService1 customUserDetailService1;

	@Autowired
	private CustomUserDetailService CustomUserDetailService;

	@Autowired
	private JwtUtil1 jwtUtil1;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println("first filter");
		String requestTokenHeader=request.getHeader("Authorization");
		
        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) 
        {
			String username=null;
			String jwtToken=null;
			String phoneNo = null;
			
			jwtToken=requestTokenHeader.substring(7);
  
			 System.out.println("token validate-1");
			 
			 String role=this.jwtUtil.extractRole(jwtToken);
			 System.out.println(role);

			if("doctor".equalsIgnoreCase(role)||"nurse".equalsIgnoreCase(role)||"admin".equalsIgnoreCase(role)) 
			{

				 System.out.println("Username password-1");
				username=this.jwtUtil.extractUsername(jwtToken);	

				UserDetails userDetails=this.CustomUserDetailService.loadUserByUsername(username);
             
				//validate the token
				
				if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());	
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

				}
			}	
			else {
					
					 System.out.println("Phone Number OTP");
					phoneNo = this.jwtUtil1.extractPhoneNo(jwtToken);
					User1 userDetails1=this.customUserDetailService1.getUserDetails(phoneNo); //problem


					System.out.println("7---------------------"); 
					
					if(phoneNo!=null && SecurityContextHolder.getContext().getAuthentication()==null) {

						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new
								UsernamePasswordAuthenticationToken(userDetails1,
										null,userDetails1.getAuthorities());
						usernamePasswordAuthenticationToken.setDetails(new
								WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(
								usernamePasswordAuthenticationToken);
                           System.out.println("good filter");


				 }
				}

			



		}
		
      System.out.println("Filter complete");

		filterChain.doFilter(request, response);
		}
}
