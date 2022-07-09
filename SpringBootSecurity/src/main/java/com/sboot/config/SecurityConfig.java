package com.sboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// csrf token means cross site scripting forgery , from this attack our website will hack
		// so spring security has inbuild security for csrf 
		// we can also disable this csrf - csrf.disable()
		
		// Basic Authentication
		
		http
		.authorizeRequests()
		// if you have more url which want to be public add in antmatcher
		// this is for single url want to be public
		// .antMatchers("/home","/login").permitAll()
		//.antMatchers("/public/**").permitAll()
		
		// assign roles to urls
		.antMatchers("/public/**").hasRole("user")
		.antMatchers("/users/").hasRole("admin")
		.anyRequest()
		.authenticated()
		.and()
		// basic auth
		.httpBasic();
		//.formLogin();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("ajay").password(this.passwordEncoder().encode("ajay@123")).roles("user");
		auth.inMemoryAuthentication().withUser("vijay").password(this.passwordEncoder().encode("vijay@123")).roles("admin");
		auth.inMemoryAuthentication().withUser("sonal").password(this.passwordEncoder().encode("sonal@123")).roles("author");

	}
	
	// roles : normal : high level overview => read authority
	// roles : admin : read and write both authority
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		// there is no used of any encoder
		//return NoOpPasswordEncoder.getInstance();
		
		// if u want encoder password
		return new BCryptPasswordEncoder();
	}
}
