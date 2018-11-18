package com.gr8.bnb.helpers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;

public class MessageManager {
	private ConnectionFactory cf;
	private Queue queue;
	
	public MessageManager(ConnectionFactory cf, Queue queue) {
		this.cf = cf;
		this.queue = queue;
	}
	
	public void send(String from, String to, String messageContent) throws ServletException {
		try { 
			Connection connection = cf.createConnection();
			connection.start();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);  
			MessageProducer producer = session.createProducer(queue);
		
			TextMessage message = session.createTextMessage(messageContent);
			message.setStringProperty("senderEmail", from);
			message.setStringProperty("receiverEmail", to);
			message.setStringProperty("sendDate",new Date().toString());
			message.setBooleanProperty("isRead", false);
			
			producer.send(message);
			
			//connection.stop();
			producer.close();
			session.close();
			connection.close();
		
		} catch (JMSException e) {
			throw new ServletException(e.getLocalizedMessage()); 
		}
	}
	
	public ArrayList<TextMessage> receive(String to) throws ServletException {
		ArrayList<TextMessage> messages = new ArrayList<TextMessage>();
		
		String selector = "receiverEmail = '" + to + "'" ;
		try { 
			Connection connection = cf.createConnection(); 
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueBrowser browser = session.createBrowser(queue, selector);
			Enumeration messagesEnum = browser.getEnumeration();
		
			while (messagesEnum.hasMoreElements()) {
				Object objMsg = messagesEnum.nextElement();
				
				if (objMsg != null) {
					if (objMsg instanceof TextMessage) {
						TextMessage message = (TextMessage) objMsg;
						messages.add(message);
					}
				}
			} 

			//connection.stop(); 
			browser.close();
			session.close();
			connection.close();
		} catch (JMSException e){
			throw new ServletException(e.getLocalizedMessage());
		}
		
		return messages;
	}
}