package com.bean.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.entities.User;
import com.bean.services.UserService;

@Controller
public class UserController {
	@Autowired
	UserService service;

	@PostMapping("/signUp")
	public String addUser(@ModelAttribute User user) {
		String username = user.getUsername();
		String email = user.getEmail();
		boolean userExists = service.userExists(username, email);
		if (userExists == false) {
			service.addUser(user);
			System.out.println("User Added to the Database");
		}
		return "index";
	}

	@PostMapping("/login")
	public String validateUser(@RequestParam String username, @RequestParam String password) {
		boolean validUser = service.validateUser(username, password);
		if (validUser == true) {
			return "home";
		} else {
			return "index";
		}
	}
}
