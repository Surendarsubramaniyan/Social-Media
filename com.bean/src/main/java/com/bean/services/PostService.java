package com.bean.services;

import java.util.List;

import com.bean.entities.Post;

public interface PostService {

	void createPost(Post post);
	List<Post> getAllPost();
	Post getPost(Long id);
	void updateLikes(Post post);
	void updateComments(Post post);

}
