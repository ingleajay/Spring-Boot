package com.sboot.blog.exceptions;

import com.sboot.blog.payload.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// make your exception as global
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // handle specific
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage()
                ,webRequest.getDescription(false));
        return  new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ErrorDetails> handleBlogAPIException(BlogAPIException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage()
                ,webRequest.getDescription(false));
        return  new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // or global exceptions
    // whenever there is some exception like number format or index out of bound r any exception at
    // global
    // ex.http://localhost:8080/api/posts/"22" try to access this way

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage()
                ,webRequest.getDescription(false));
        return  new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String , String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
