package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dao.ChatDao;
import com.app.entities.Chat;

@Component
public class ChatService {
	
	@Autowired
	ChatDao chatDao;
	public Chat saveChat(Chat chat)
	{
		return chatDao.saveChat(chat);
	}
	public List<Chat> getAllChatsByUserID(int userId)
	{
		return chatDao.getAllChatsByUserID(userId);
	}
	public Chat findChatById(int chatId)
	{
		return chatDao.findChatById(chatId);
	}
	public void deleteChat(int chatId)
	{
		chatDao.deleteChat(chatId);
	}
}
