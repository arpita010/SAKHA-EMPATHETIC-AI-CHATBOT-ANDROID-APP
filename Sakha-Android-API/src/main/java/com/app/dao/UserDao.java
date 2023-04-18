package com.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.entities.User;

@Component
public class UserDao {
	@Autowired
	UserRepo userRepo;
	public void saveUser(User user)
	{
		userRepo.save(user);
	}
	public void deleteUser(User user)
	{
		userRepo.delete(user);
	}
	public User getUserById(int userId)
	{
		User getUser=userRepo.findById(userId).get();
		return getUser;
	}
	public User getUserByEmailAndPassword(String userEmail,String password)
	{
		return userRepo.findByUserEmailAndPassword(userEmail, password);
	}
	
	public boolean checkEmailExist(String userEmail)
	{
		User user=userRepo.findByUserEmail(userEmail);
		if(user==null)return false;
		return true;
	}
	
}
