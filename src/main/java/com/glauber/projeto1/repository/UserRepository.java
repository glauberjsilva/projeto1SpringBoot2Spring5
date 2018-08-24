package com.glauber.projeto1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.glauber.projeto1.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String nome);
	User findByEmail(String email);
	User findByNameAndEmail(String name, String email);
	User findByNameIgnoreCase(String name);
    @Query("select u from User u where u.name like %?1%")
	User findByNameQualquerCoisa(String name);
    
	
}
