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
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.filter.OncePerRequestFilter;

import com.technoidentity.agastya.helper.JwtUtil;
import com.technoidentity.agastya.model.User;
import com.technoidentity.agastya.service.CustomUserDetailService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println("first filter");
		// get JWT
		// check token start with Bearer or not
		// validate tokens

		String requestTokenHeader = request.getHeader("Authorization");
		String phoneNo = null;
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			jwtToken = requestTokenHeader.substring(7);

			try {

				phoneNo = this.jwtUtil.extractPhoneNo(jwtToken);
			
			} catch (Exception e) {
				e.printStackTrace();
				
			}

			
			 User userDetails=this.customUserDetailService.getUserDetails(phoneNo);
			  
			  if(phoneNo!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			  
			  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new
			  UsernamePasswordAuthenticationToken(userDetails,
			  null,userDetails.getAuthorities());
			  usernamePasswordAuthenticationToken.setDetails(new
			  WebAuthenticationDetailsSource().buildDetails(request));
			  SecurityContextHolder.getContext().setAuthentication(
			  usernamePasswordAuthenticationToken); } else {
			  System.out.println("token is not validate"); }
			

		}
		System.out.println("Filter complete");

		filterChain.doFilter(request, response);

	}

}
