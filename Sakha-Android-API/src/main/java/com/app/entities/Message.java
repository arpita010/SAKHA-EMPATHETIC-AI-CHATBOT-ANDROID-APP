package com.app.entities;

public class Message {

	private int userId;
	private String message;
	@Override
	public String toString() {
		return "Message [userId=" + userId + ", message=" + message + "]";
	}
	public Message(int userId, String message) {
		super();
		this.userId = userId;
		this.message = message;
	}
	public int getUserId() {
		return userId;
	}
	public String getMessage() {
		return message;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
