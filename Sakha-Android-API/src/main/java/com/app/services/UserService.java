package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dao.UserDao;
import com.app.entities.User;

@Component
public class UserService {
	
	@Autowired
	UserDao userDao;
	public User getUserById(int userID)
	{
		return userDao.getUserById(userID);
	}
	public User getUserByEmailAndPassword(String userEmail,String password)
	{
		return userDao.getUserByEmailAndPassword(userEmail, password);
	}
	public void deleteUser(User user)
	{
		userDao.deleteUser(user);
	}
	public void saveUser(User user)
	{
		userDao.saveUser(user);
	}
	
	public boolean checkEmailExist(String emailId)
	{
		return userDao.checkEmailExist(emailId);
	}
}
