package com.sboot.blog.config;

import com.sboot.blog.security.CustomUserDetailsService;
import com.sboot.blog.service.JWTAuthenticationEntryPoint;
import com.sboot.blog.service.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // this is used to enable web related security
@EnableGlobalMethodSecurity(prePostEnabled = true) // whenever you want method level security then used this
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v2/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                // swagger api docs in json
                .antMatchers("/v2/api-docs/**").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .anyRequest()
                .authenticated();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//                .and()
//                .httpBasic();
    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//       UserDetails user1 =  User.builder().username("ajay").password(passwordEncoder().encode("password")).roles("USER").build();
//        UserDetails user2 =  User.builder().username("vijay").password(passwordEncoder().encode("password1")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user1,user2);
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    // this is important for login and register for users
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
