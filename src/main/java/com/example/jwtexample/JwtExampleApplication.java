package com.example.jwtexample;

import com.example.jwtexample.entity.User;
import com.example.jwtexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class JwtExampleApplication {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void initUsers(){
		List<User> users = Stream.of(
				new User(1,"siva","cGFzc3dvcmQ=","testemail@gmail.com"),
		        new User(2,"subbu","password","testemail@1gmail.com")

		).collect(Collectors.toList());

		userRepository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(JwtExampleApplication.class, args);
	}

}
