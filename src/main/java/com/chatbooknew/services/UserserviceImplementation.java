package com.chatbooknew.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatbooknew.entities.Users;
import com.chatbooknew.repositories.UserRepository;

@Service
public class UserserviceImplementation implements Userservice {
    @Autowired
	UserRepository repo;

	public boolean userExists(String username, String email) {
		Users user1=repo.findByUsername(username);
		Users user2=repo.findByEmail(email);
		if(user1!=null || user2 !=null){
			return true;
		}
		return false;
	}

	@Override
	public void addUser(Users user) {
		repo.save(user);
		
	}

	@Override
	public boolean validateUser(String username, String password) {
		// TODO Auto-generated method stub
		String dbpass=repo.findByUsername(username).getPassword();
		if(password.equals(dbpass)) {
			return true;
		}
		return false;
	}

	@Override
	public Users getUser(String username) {
		// TODO Auto-generated method stub
		return repo.findByUsername(username);
	}

	@Override
	public void updateUser(Users user) {
		// TODO Auto-generated method stub
		repo.save(user);
		
	}

	
}
