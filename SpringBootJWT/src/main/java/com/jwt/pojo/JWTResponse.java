package com.jwt.pojo;

public class JWTResponse {
	String token;

	public JWTResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "JWTResponse [token=" + token + "]";
	}
	
}
