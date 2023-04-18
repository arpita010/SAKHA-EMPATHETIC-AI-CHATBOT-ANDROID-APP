package com.app.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Answer;
import com.app.entities.Chat;
import com.app.entities.Message;
import com.app.helper.MLModelCaller;
import com.app.helper.MessageRequest;
import com.app.helper.MessageResponse;
import com.app.services.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class ChatController {

	@Autowired 
	ChatService chatService;
	private String prev_message;
	// message {userId,message}
	@PostMapping(path="/getMessage",consumes="*")
	public ResponseEntity<MessageResponse> receiveMessage(@RequestBody Message message)
	{
		ObjectMapper objectMapper=new ObjectMapper();
		MessageRequest messageRequest=new MessageRequest();
		messageRequest.setUserInput(message.getMessage());
		messageRequest.setPrev_message(prev_message);
		MLModelCaller modelCaller=new MLModelCaller();
		String sakhaResponse="";
		try {
			sakhaResponse = modelCaller.sendMessageToModel(objectMapper.writeValueAsString(messageRequest));
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		Answer answer=new Answer();
		try {
			answer=objectMapper.readValue(sakhaResponse,Answer.class);
		} catch (JsonProcessingException e) {
			answer.setAnswer("We are sorry for inconvenience. Due to technical issue,this message is not visible...");
			e.printStackTrace();
		}
		MessageResponse messageResponse=new MessageResponse();
		messageResponse.setMessage(answer.getAnswer());
		messageResponse.setSent_by(2);
		Chat chat=new Chat();
		chat.setDateTime(LocalDateTime.now());
		chat.setUserId(message.getUserId());
		chat.setUserMessage(message.getMessage());
		chat.setSakhaResponse(answer.getAnswer());
		chatService.saveChat(chat);
		prev_message=message.getMessage();
		return ResponseEntity.status(HttpStatus.OK).body(messageResponse);
	}
}
