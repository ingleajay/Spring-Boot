package com.sboot.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// it will respond by spring with given https status code whenever exception occurs
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends  RuntimeException{

    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        // resourceName = Post , fieldName = id and fieldValue = 1 => Post not found with id : 1
        super(String.format("%s not found with %s : '%s' " , resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getFieldValue() {
        return fieldValue;
    }
}
