package com.example.backend;

import com.example.backend.Business.UserService;
import com.example.backend.dto.UserCreateDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude=SecurityAutoConfiguration.class)
public class BackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
	CommandLineRunner createInitialUsers(UserService userService){
		return (args)-> {
			UserCreateDto userCreateDto =new UserCreateDto();
			userCreateDto.setUserName("user1");
			userCreateDto.setFirstName("Cebrail");
			userCreateDto.setLastName("Kaya");
			userService.createUser(userCreateDto);

			UserCreateDto user =new UserCreateDto();
			user.setUserName("user2");
			user.setFirstName("Ahsen");
			user.setLastName("Kaya");
			userService.createUser(user);


		};
	}

}
