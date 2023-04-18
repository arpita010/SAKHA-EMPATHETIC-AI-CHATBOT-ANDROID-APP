package com.app.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Chat {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int chatId;
	@Column(columnDefinition = "longtext")
	private String userMessage;
	@Column(columnDefinition = "longtext")
	private String sakhaResponse;
	private LocalDateTime dateTime;
	private int userId;
	public int getChatId() {
		return chatId;
	}
	public String getUserMessage() {
		return userMessage;
	}
	public String getSakhaResponse() {
		return sakhaResponse;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	public void setSakhaResponse(String sakhaResponse) {
		this.sakhaResponse = sakhaResponse;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Chat [chatId=" + chatId + ", userMessage=" + userMessage + ", sakhaResponse=" + sakhaResponse
				+ ", dateTime=" + dateTime + ", userId=" + userId + "]";
	}
	
}
