package com.sboot.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="users",
uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
}
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;

    // we retrive users role as soon as we want user details so use eagar
    // if u want users on demand then use lazy
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // it will new table and it makes foregin key id with user_id as name
    @JoinTable
            (name="user_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
                    inverseJoinColumns =@JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Set<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "users" , cascade = CascadeType.ALL , orphanRemoval = true)
    private Set<Post> posts = new HashSet<>();

}
