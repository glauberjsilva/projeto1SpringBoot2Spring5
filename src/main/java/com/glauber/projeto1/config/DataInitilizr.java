package com.glauber.projeto1.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.glauber.projeto1.entity.User;
import com.glauber.projeto1.repository.UserRepository;

@Component
public class DataInitilizr implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UserRepository userRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		List<User> users = userRepository.findAll();

		if (users.isEmpty()) {
			createUser("Glauber", "gjsilva83@gmail.com");
			createUser("João", "joao@gmail.com");
			createUser("Maria", "maria@gmail.com");
		}

		Optional<User> userOptional = userRepository.findById(2L);
		if (userOptional.isPresent()) {
			System.out.println(userOptional.get().getName());
			System.out.println(userOptional.get().getEmail());
			System.out.println(userOptional.get());
			
			userOptional.get().setName("João Maria da Silva");
			userRepository.save(userOptional.get());	
		}
		
		
		User user = userRepository.findByEmail("joao@gmail.com");
		System.out.println(user.getEmail());
		
		user = userRepository.findByNameQualquerCoisa("G");
		System.out.println(user.getName());
		
		user = userRepository.findByNameAndEmail("Maria", "maria@gmail.com");
		System.out.println(user.getName());
		
		user = userRepository.findByNameIgnoreCase("glauber");
		System.out.println(user.getName());
	
		
		System.out.println("Deletando " + user.getName());
		userRepository.deleteById(user.getId());
		
	}

	private void createUser(String name, String email) {
		User user = new User(name, email);
		userRepository.save(user);
	}

}
