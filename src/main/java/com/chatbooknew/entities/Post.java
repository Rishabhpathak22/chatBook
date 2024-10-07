package com.chatbooknew.entities;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String caption;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] photo;
	public String getPhotoBase64() {
		if(photo==null) {
			return null;
		}
		return Base64.getEncoder().encodeToString(photo);

	}
	private int likes;
	private List<String> comments;
	@ManyToOne
	private Users user;
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Post(Long id, String caption, byte[] photo, int likes, List<String> comments, Users user) {
		super();
		this.id = id;
		this.caption = caption;
		this.photo = photo;
		this.likes = likes;
		this.comments = comments;
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", caption=" + caption + ", photo=" + Arrays.toString(photo) + ", likes=" + likes
				+ ", comments=" + comments + ", user=" + user + "]";
	}




}
