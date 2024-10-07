package com.chatbooknew.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chatbooknew.entities.Post;
import com.chatbooknew.entities.Users;
import com.chatbooknew.services.PostService;
import com.chatbooknew.services.Userservice;

import jakarta.servlet.http.HttpSession;

@Controller
public class Usercontroller {
	@Autowired
	Userservice service;
	@Autowired
	PostService postservice;
	@PostMapping("/signUp")
	public String addUser(@ModelAttribute Users user) {
		//user exists
		String username=user.getUsername();
		String email=user.getEmail();
		boolean status =service.userExists(username,email);
		if(status == false) {
			service.addUser(user);
		}
		return "index";
	}
	@PostMapping("/login")
	public String login(@RequestParam String username,@RequestParam String password
			,Model model,HttpSession session) {
		boolean status =service.validateUser(username,password);
		if(status == true) {
			List<Post> allPosts=postservice.fetchAllposts();
			session.setAttribute("username",username);
			model.addAttribute("allPosts",allPosts);
			model.addAttribute("session",session);
			return "home";
		}
		else {
			return "index";
		}

	}
	@PostMapping("/updateProfile")
	public String updateProfile(@RequestParam String dob,@RequestParam String bio,
			@RequestParam String gender,@RequestParam String city,
			@RequestParam String college,@RequestParam String linkedIn,
			@RequestParam String gitHub,@RequestParam MultipartFile profilePic
			,HttpSession session,
			Model model) {
		
		String username=(String)session.getAttribute("username");
		//fetch user object using username
		Users user=service.getUser(username);
		//update and save object
		user.setDob(dob);
		user.setGender(gender);
		user.setCity(city);
		user.setCollege(college);
		user.setLinkedIn(linkedIn);
		user.setGitHub(gitHub);
		user.setBio(bio);
		try {
    		user.setProfilePic(profilePic.getBytes());
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
		service.updateUser(user);
		model.addAttribute("user", user);
		return "myProfile";
	}
	 @GetMapping("/openUserProfile")
	    public String openUserProfile(@RequestParam String username, Model model) {
	        // Logic to retrieve user profile details by username
	        Users user = service.getUser(username);
	        
	        if (user == null) {
	            // Handle the case where the user is not found
	            model.addAttribute("error", "User not found");
	            return "error"; // Redirect to an error page or handle it as needed
	        }
	        
	        model.addAttribute("user", user);
	        return "myprofile"; // Return the name of the view for the user profile
	    }
}

