package com.sboot.blog.security;

import com.sboot.blog.entity.Role;
import com.sboot.blog.entity.User;
import com.sboot.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow(()->new UsernameNotFoundException("User not found with user name or email:" + usernameOrEmail));
        return new CustomUserDetail(user);
    }

// other approaches - if u don't want create Custom User detail file
//    @Override
//    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
//        User user = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
//                .orElseThrow(()->new UsernameNotFoundException("User not found with user name or email:" + usernameOrEmail));
//        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),getAuthorities(user.getRoles()));
//    }
//
//    public Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }

}
