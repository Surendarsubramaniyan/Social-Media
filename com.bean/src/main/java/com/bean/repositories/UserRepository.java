package com.bean.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bean.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);

	public User findByEmail(String email);

}
