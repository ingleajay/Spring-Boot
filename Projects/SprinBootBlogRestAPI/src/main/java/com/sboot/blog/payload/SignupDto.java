package com.sboot.blog.payload;

import com.sboot.blog.entity.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignupDto {

    @NotEmpty
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @NotEmpty
    @Size(max=6, message = "Username should have at least 6 characters")
    private String username;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 8, message = "Password should have 8 characters")
    private String password;

    @NotEmpty
    private String role;
}
