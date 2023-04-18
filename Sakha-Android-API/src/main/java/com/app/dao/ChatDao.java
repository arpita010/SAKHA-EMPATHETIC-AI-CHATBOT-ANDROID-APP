package com.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.entities.Chat;

@Component
public class ChatDao {

	@Autowired
	ChatRepo chatRepo;
	public Chat saveChat(Chat chat)
	{
		return chatRepo.save(chat);
	}
	public List<Chat> getAllChatsByUserID(int userId)
	{
		List<Chat> chats=chatRepo.findAllByUserId(userId);
		return chats;
	}
	public Chat findChatById(int chatId)
	{
		return chatRepo.findById(chatId).get();
	}
	public void deleteChat(int chatId)
	{
		chatRepo.deleteById(chatId);
	}
	
}
