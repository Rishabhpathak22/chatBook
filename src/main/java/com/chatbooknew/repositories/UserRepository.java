package com.chatbooknew.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatbooknew.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long>{

	Users findByUsername(String username);

	Users findByEmail(String email);

}
