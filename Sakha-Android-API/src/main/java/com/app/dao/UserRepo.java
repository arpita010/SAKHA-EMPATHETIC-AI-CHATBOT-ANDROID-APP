package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	public User findByUserEmailAndPassword(String userEmail,String password);
	public User findByUserEmail(String userEmail);
}
