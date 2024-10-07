package com.chatbooknew.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatbooknew.entities.Post;
import com.chatbooknew.repositories.PostRepository;

@Service
public class PostServiceImplemetation implements PostService{
 
	@Autowired
	PostRepository repo;

	@Override
	public void createPost(Post post) {
		// TODO Auto-generated method stub
		repo.save(post);
		
	}

	@Override
	public List<Post> getAllPosts() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<Post> fetchAllposts() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Post getPost(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public void updatePost(Post post) {
		// TODO Auto-generated method stub
		repo.save(post);
		
	}
	
}
