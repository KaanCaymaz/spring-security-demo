package com.luv2code.springsecurity.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@ComponentScan("com.luv2code.springsecurity.demo")
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {


//		auth.inMemoryAuthentication()
//		.withUser("john").password("1234").roles("EMPLOYEE");
//		
//		auth.inMemoryAuthentication()
//		.withUser("mary").password("5678").roles("MANAGER");
//		
//		auth.inMemoryAuthentication()
//		.withUser("tom").password("91011").roles("ADMIN");
	
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("john").password("1234").roles("EMPLOYEE"))
			.withUser(users.username("mary").password("5678").roles("MANAGER"))
			.withUser(users.username("susan").password("91011").roles("ADMIN"));
	
	
	
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/showMyLoginPage")
			.loginProcessingUrl("/authenticateTheUser")
			.permitAll()
		.and()
		.logout().permitAll();
	}
	
	

}
