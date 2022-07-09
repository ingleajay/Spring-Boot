package com.custom;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;


@Endpoint(id = "details")
@Component
public class CustomActutorEndPoint {
	
	@ReadOperation
    public String details() {
        return "My App Details";
    }

}
