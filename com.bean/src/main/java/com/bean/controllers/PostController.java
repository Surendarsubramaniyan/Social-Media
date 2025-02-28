package com.bean.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bean.entities.Post;
import com.bean.entities.User;
import com.bean.services.PostService;
import com.bean.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
@Autowired
PostService service;
@Autowired
UserService userService;
@PostMapping("/createPost")
public String createPost(@RequestParam("caption") String caption, 
							@RequestParam("photo") MultipartFile photo,Model model, HttpSession session)
{
	String username=(String)session.getAttribute("username");
	User user=userService.getUserByName(username);
	Post post=new Post();
	//for Tracking which user posted the Post
	post.setUser(user);
	post.setCaption(caption);
	
	try {
		post.setPhoto(photo.getBytes());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	service.createPost(post);
	//getting the list of posts posted by the current session user
	List<Post> posts = user.getPosts();
	//if user didn't posts not even one post just create an Empty Post array this way the List of Post object will be created at first
	if(posts == null) {
		posts = new ArrayList<Post>();
	}
	//adding the current post to the Posts ArrayList
	posts.add(post);
	//Updating user object's list of all the posts if one post is posted by the user we are updating his list of posts
	user.setPosts(posts);
	userService.updateUser(user);
	
	
	List<Post> allPosts=service.getAllPost();
	model.addAttribute("allPosts",allPosts);
	return "home";	
}
@GetMapping("/showPost")
public String getAllPost(Model model, HttpSession session)
{
	if (session.getAttribute("username")!=null) {
	List<Post> allPosts=service.getAllPost();
	model.addAttribute("allPosts",allPosts);
	return "home";}
	else
		return "index";
}
@PostMapping("/likePost")
public String likePost(@RequestParam Long id, Model model)
{
	Post post=service.getPost(id);
	post.setLikes(post.getLikes()+1);
	service.updateLikes(post);
	List<Post> allPosts=service.getAllPost();
	model.addAttribute("allPosts",allPosts);
		return "home";
}
@PostMapping("/addComment")
public String addComment(@RequestParam Long id, @RequestParam String comment,Model model)
{
	Post post=service.getPost(id);
	List<String> comments = post.getComments();
	if(comments == null) {
		comments = new ArrayList<String>();
	}
	comments.add(comment);
	post.setComments(comments);
	service.updateComments(post);
	
	List<Post> allPosts = service.getAllPost();
	model.addAttribute("allPosts", allPosts);
		return "home";
}

}
