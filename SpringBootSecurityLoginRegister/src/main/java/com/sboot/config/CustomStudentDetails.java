package com.sboot.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sboot.models.Student;

public class CustomStudentDetails implements UserDetails {
	
	private Student student;
	
	public CustomStudentDetails(Student student) {
		super();
		this.student = student;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority =  new SimpleGrantedAuthority(student.getRole());
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		return student.getPassword();
	}

	@Override
	public String getUsername() {
		return student.getStuname();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
