package com.gr8.bnb.helpers;


import java.util.Date;

import javax.servlet.ServletException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Message;
import model.User;

public class MessageManager {
	
	WebTarget messageWebtarget;
	
	public MessageManager(Client client) {
		this.messageWebtarget = client.target("http://localhost:8084/");
	}
	
	public boolean send(User from, User to, String messageContent) throws ServletException {
		/* Create new message. */
		Message message = new Message();
		message.setContent(messageContent);
		message.setSender(from);
		message.setReceiver(to);
		message.setSendDate(new Date());
		
		/* Send post to the REST API. */
		WebTarget messagePath = messageWebtarget.path("message");
		Builder builder = messagePath.request(MediaType.APPLICATION_JSON);
		Response res = builder.post(Entity.entity(message, MediaType.APPLICATION_JSON));
		
		/* If resource created return true. */
		if (res.getStatus() != 201) {
			return false;
		}
		return true;
	}
	
	public Message[] receive(User to) throws ServletException {
		/* Get the messages to */
		WebTarget messagePath = messageWebtarget.path("users")
				.path(to.getId().toString())
				.path("messages");
		
		Builder builder = messagePath.request(MediaType.APPLICATION_JSON);
		Response res = builder.get();
		
		/* If the response is not ok then return null. */
		if (res.getStatus() != 200) {
			return null;
		}
		
		/* Return messages towards user to. */
		Message[] messages = res.readEntity(Message[].class);
		return messages;
	}
}