package com.bean.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.entities.Post;
import com.bean.repositories.PostRepository;

@Service
public class PostServiceImplementation implements PostService{
@Autowired
PostRepository repo;

@Override
public void createPost(Post post) {
	// TODO Auto-generated method stub
	repo.save(post);
}

@Override
public List<Post> getAllPost() {
	// TODO Auto-generated method stub
	return repo.findAll();
}

@Override
public Post getPost(Long id) {
	// TODO Auto-generated method stub
	return repo.findById(id).get();
}

@Override
public void updateLikes(Post post) {
	// TODO Auto-generated method stub
	repo.save(post);
}

@Override
public void updateComments(Post post) {
	// TODO Auto-generated method stub
	repo.save(post);
}
}
