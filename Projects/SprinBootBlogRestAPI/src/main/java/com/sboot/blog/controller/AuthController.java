package com.sboot.blog.controller;

import com.sboot.blog.entity.Role;
import com.sboot.blog.entity.User;
import com.sboot.blog.payload.JWTAuthResponse;
import com.sboot.blog.payload.LoginDto;
import com.sboot.blog.payload.SignupDto;
import com.sboot.blog.repository.RoleRepository;
import com.sboot.blog.repository.UserRepository;
import com.sboot.blog.service.JWTTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Api(value = "Auto controller exposes signin and signup REST API")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    // login api
    @ApiOperation(value = "REST API to login or signin user to blog app")
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticatedUser(@RequestBody LoginDto loginDto){

        // match your username and password of json with DTO
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()));

        // set your authentication details in security context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // get token from tokenprovider class
        String token = jwtTokenProvider.generateToken(authentication);
        //return  new ResponseEntity<>("User signed-in successfully !", HttpStatus.OK);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    // register api
    @ApiOperation(value = "REST API to register or signup user to blog app")
    @PostMapping("/signup")
    public  ResponseEntity<String>  registerUser(@Valid @RequestBody SignupDto signupDto){
        // add check for username exists data bases
        if(userRepository.existsByUsername(signupDto.getUsername())){
            return new ResponseEntity<>("Username is already taken ! ",HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in db
        if(userRepository.existsByEmail(signupDto.getEmail())){
            return new ResponseEntity<>("Email is already taken ! ",HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setUsername(signupDto.getUsername());
        user.setEmail(signupDto.getEmail());
        user.setName(signupDto.getName());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        Role role1 = roleRepository.findByName("ROLE_ADMIN").get();
        Role role2 = roleRepository.findByName("ROLE_USER").get();
        System.out.println(role1.getName() + " " + signupDto.getRole());
        if(role1.getName().equals(signupDto.getRole())){
            user.setRoles(Collections.singleton(role1));
        }
        if(role2.getName().equals(signupDto.getRole())){
            user.setRoles(Collections.singleton(role2));
        }
        userRepository.save(user);
        return new ResponseEntity<>("User register successfully !!",HttpStatus.OK);
    }
}
