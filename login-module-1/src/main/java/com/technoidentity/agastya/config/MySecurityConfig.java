package com.technoidentity.agastya.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.technoidentity.agastya.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@Order(1)
public class MySecurityConfig   extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	@Autowired
	private JwtAuthentationEnteryPoint enteryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		System.out.println("My security");
		http
		      .csrf()
		      .disable()
		      .cors()
		      .disable()
		      .authorizeRequests()
		      .antMatchers("/agastya/sendOTP").permitAll()   //generate OTP for caretaker
		      .antMatchers("/**").permitAll()
		      .antMatchers("/agastya/login").permitAll()         //for login caretaker
		      .antMatchers("/agastya/hospital/login").permitAll()  //for hospital provider
		      .anyRequest().authenticated()
		      .and()
		      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		      .and()
		      .exceptionHandling().authenticationEntryPoint(enteryPoint);
		
		      http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
	
	       System.out.println("My Security");
	}
	
	//over
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		System.out.println("Config part");
	  
	  auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
	}
	
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	 
	

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	
}
