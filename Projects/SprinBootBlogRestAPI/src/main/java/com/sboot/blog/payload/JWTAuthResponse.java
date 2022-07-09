package com.sboot.blog.payload;

public class JWTAuthResponse {

    private String accessToken;
    private String tokentype="Bearer";

    public JWTAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokentype() {
        return tokentype;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokentype(String tokentype) {
        this.tokentype = tokentype;
    }
}
