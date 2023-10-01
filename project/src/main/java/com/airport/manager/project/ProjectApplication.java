package com.airport.manager.project;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public PasswordEncoder bcryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}