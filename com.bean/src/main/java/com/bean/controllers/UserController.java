package com.bean.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bean.entities.Post;
import com.bean.entities.User;
import com.bean.services.PostService;
import com.bean.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserService service;
	@Autowired
	PostService postService;

	@PostMapping("/signUp")
	public String addUser(
			@RequestParam("username") String username,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("dob") String dob,
			@RequestParam("gender") String gender,
			@RequestParam("city") String city,
			@RequestParam("bio") String bio,
			@RequestParam("college") String college,
			@RequestParam("linkedIn") String linkedIn,
			@RequestParam("github") String github,
			@RequestParam("profilePhoto") MultipartFile profilePhoto) {
		User user=new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setDob(dob);
		user.setGender(gender);
		user.setCity(city);
		user.setBio(bio);
		user.setCollege(college);
		user.setLinkedIn(linkedIn);
		user.setGithub(github);
		try {
			user.setProfilePhoto(profilePhoto.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean userExists = service.userExists(username, email);
		if (userExists == false) {
			service.addUser(user);
			System.out.println("User Added to the Database");
		}
		return "index";
	}

	@PostMapping("/login")
	public String validateUser(@RequestParam String username, @RequestParam String password,Model model, HttpSession session) {
		boolean validUser = service.validateUser(username, password);
		if (validUser == true) {
			session.setAttribute("username",username);
			model.addAttribute("session",session);
			List<Post> allPosts=postService.getAllPost();
			model.addAttribute("allPosts",allPosts);
			return "home";
		} else {
			return "index";
		}
	}
	
	@PostMapping("/editProfile")
	public String editProfile(
			@RequestParam("dob") String dob,
			@RequestParam("gender") String gender,
			@RequestParam("city") String city,
			@RequestParam("bio") String bio,
			@RequestParam("college") String college,
			@RequestParam("linkedIn") String linkedIn,
			@RequestParam("github") String github,
			@RequestParam("profilePhoto") MultipartFile profilePhoto,
			HttpSession session,
			Model model)
	{
		
		String username=(String) session.getAttribute("username");
		System.out.println(username);
		User user= service.getUserByName(username);
		user.setDob(dob);
		user.setGender(gender);
		user.setCity(city);
		user.setBio(bio);
		user.setCollege(college);
		user.setLinkedIn(linkedIn);
		user.setGithub(github);
		try {
			user.setProfilePhoto(profilePhoto.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.updateUser(user);
		model.addAttribute("user",user);
		return "myProfile";
	}
}
