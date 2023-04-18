package com.app.helper;

public class MessageRequest {

	String userInput;
	String prev_message;
	public MessageRequest() {
		
	}
	
	public String getUserInput() {
		return userInput;
	}

	public String getPrev_message() {
		return prev_message;
	}

	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}

	public void setPrev_message(String prev_message) {
		this.prev_message = prev_message;
	}

	@Override
	public String toString() {
		return "MessageRequest [userInput=" + userInput + ", prev_message=" + prev_message + "]";
	}
		
}
