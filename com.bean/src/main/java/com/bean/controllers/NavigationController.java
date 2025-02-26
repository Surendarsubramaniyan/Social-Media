package com.bean.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

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
	public String openCreatePostPage()
	{
		return "createPost";
	}
}
