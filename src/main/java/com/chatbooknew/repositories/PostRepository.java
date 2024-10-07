package com.chatbooknew.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatbooknew.entities.Post;

public interface PostRepository  extends JpaRepository<Post, Long>{

}
