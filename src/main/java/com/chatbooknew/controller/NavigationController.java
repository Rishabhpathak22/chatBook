package com.chatbooknew.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.chatbooknew.entities.Post;
import com.chatbooknew.entities.Users;
import com.chatbooknew.services.PostService;
import com.chatbooknew.services.Userservice;

import jakarta.servlet.http.HttpSession;


@Controller
public class NavigationController {
	@Autowired
	PostService postService;
	@Autowired
	Userservice service;
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/opensignup")
	public String opensignup() {
		return "signup";
	}
	@GetMapping("/openCreatePost")
	public String openCreatePost() {
		return "createPost";
	}
	@GetMapping("/goHome")
	public String login(Model model)	{
		List<Post> allPosts = postService.fetchAllposts();
		model.addAttribute("allPosts", allPosts);
		return "home";
	}
	@GetMapping("/openMyProfile")
	public String openMyProfile(Model model, HttpSession session) {
		// Retrieve the username from the session
		String username = (String) session.getAttribute("username");

		// Check if username is null
		if (username == null) {
			// Handle the case where the user is not logged in
			return "redirect:/login"; // Redirect to the login page
		}

		// Retrieve the user object using the service
		Users user = service.getUser(username);

		// Check if the user was found
		if (user == null) {
			// Handle the case where the user was not found in the database
			model.addAttribute("errorMessage", "User not found.");
			return "error"; // Redirect to an error page or show an error message
		}

		// Add the user and their posts to the model
		model.addAttribute("user", user);

		// Safely get the posts; handle the case where user.getPosts() might return null
		List<Post> myPosts = user.getPosts();
		if (myPosts == null) {
			myPosts = new ArrayList<>(); // Initialize to an empty list if null
		}
		model.addAttribute("myPosts", myPosts);

		// Return the profile view
		return "myprofile";
	}

	@GetMapping("/openEditProfile")
	public String openEditProfile() {
		return "editProfile";
	}
	@GetMapping("/indexlogin")
	public String logintoindex(){
		return "login";
	}
	@GetMapping("/indexsignup")
	public String signtoindex(){
		return "signup";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

}
