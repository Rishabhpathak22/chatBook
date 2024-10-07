package com.chatbooknew.services;

import com.chatbooknew.entities.Users;

public interface Userservice {

	boolean userExists(String username, String email);

	void addUser(Users user);

	boolean validateUser(String username, String password);

	Users getUser(String username);

	void updateUser(Users user);

}
