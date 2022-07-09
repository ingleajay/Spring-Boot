package com.sboot.blog;

import com.sboot.blog.entity.Role;
import com.sboot.blog.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@EnableSwagger2
@SpringBootApplication
// implements CommandLineRunner
public class SprinBootBlogRestApiApplication{

	@Autowired
	private RoleRepository roleRepository;

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SprinBootBlogRestApiApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//
//		long rolecount = 0;
//		int count =2;
//        while(count != rolecount) {
//			Role adminRole = new Role();
//			adminRole.setName("ROLE_ADMIN");
//			roleRepository.save(adminRole);
//			Role userRole = new Role();
//			userRole.setName("ROLE_USER");
//			roleRepository.save(userRole);
//			rolecount = roleRepository.findAll().stream().count();
//		}
//	}
}
