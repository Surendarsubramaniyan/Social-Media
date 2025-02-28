package com.bean.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.entities.Post;
import com.bean.entities.User;
import com.bean.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavigationController {
	@Autowired
	UserService userService;

	@GetMapping("/")
	public String indexPage()
	{
		return "index";
	}
	@GetMapping("/openSignUpPage")
	public String openSignUpPage()
	{
		return "signUp";
	}
	@GetMapping("/openCreatePostPage")
	public String openCreatePostPage(HttpSession session)
	{
		if (session.getAttribute("username")!=null)
		return "createPost";
		else
			return "index";
	}
	
	
	@GetMapping("/openMyProfile")
	public String openMyProfile(Model model, HttpSession session)
	{
		if (session.getAttribute("username")!=null) {
			
		String username=(String) session.getAttribute("username");
		User user= userService.getUserByName(username);
		model.addAttribute("user",user);
		List<Post> myPosts=user.getPosts();
		model.addAttribute("myPosts",myPosts);
		
		return "myProfile";
		}
		else
			return "index";
	}

	@GetMapping("/openEditProfile")
	public String openEditProfile(HttpSession session)
	{
		if (session.getAttribute("username")!=null)
		return "editProfile";
		else
			return "index";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "index";
	}
	@PostMapping("/showProfile")
	public String showProfile(@RequestParam String username,Model model,HttpSession session)
	{if (session.getAttribute("username")!=null)
		{
		User user=userService.getUserByName(username);
		model.addAttribute("user",user);
		return "showProfile";
		}
	
		else
			return "index";
	}

}
