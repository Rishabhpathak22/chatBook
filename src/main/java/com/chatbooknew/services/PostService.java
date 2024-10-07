package com.chatbooknew.services;

import java.util.List;

import com.chatbooknew.entities.Post;

public interface PostService {

	void createPost(Post post);
	List<Post> getAllPosts();
	List<Post> fetchAllposts();
	Post getPost(Long id);
	void updatePost(Post post);

}
