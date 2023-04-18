package com.app.helper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class MLModelCaller {
	
	private static final String url="http://127.0.0.1:5000/predict";
	private HttpClient client;
	private HttpRequest request;
	public String sendMessageToModel(String requestJson)
	{
		URI uri=URI.create(url);
		request = HttpRequest.newBuilder(uri)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).
				POST(BodyPublishers.ofString(requestJson))
				.build();	
		client=HttpClient.newBuilder().build();
		try
		{
			String response=client.send(request,HttpResponse.BodyHandlers.ofString()).body();
			System.out.println(response);
			return response;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
		
	}

}
