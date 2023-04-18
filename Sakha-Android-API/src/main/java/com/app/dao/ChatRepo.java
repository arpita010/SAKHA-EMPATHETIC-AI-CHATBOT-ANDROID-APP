package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Chat;

public interface ChatRepo extends JpaRepository<Chat, Integer>{

	public List<Chat> findAllByUserId(int userID);
}
