package com.bean.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.entities.User;
import com.bean.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	UserRepository repo;

	@Override
	public boolean userExists(String username, String email) {
		if (repo.findByUsername(username) != null || repo.findByEmail(email) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void addUser(User user) {
		repo.save(user);
	}

	@Override
	public boolean validateUser(String username, String password) {
		if (repo.findByUsername(username) != null) {
			if (repo.findByUsername(username).getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

}
