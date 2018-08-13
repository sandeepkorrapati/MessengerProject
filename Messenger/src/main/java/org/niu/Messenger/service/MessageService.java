package org.niu.Messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.niu.Messenger.database.DatabaseClass;
import org.niu.Messenger.exception.DataNotFoundException;
import org.niu.Messenger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public MessageService() {
		super();
		// Creating a sample messages in map. Not necessary. We can even send the
		// message through Postman using POST.
		messages.put(1L, new Message(1, "Hello! This is Sandeep", "Sandeep"));
		//messages.put(2L, new Message(2, "Hello! This is Alex", "Alex"));
	}

	public List<Message> getAllMessages() {
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if(list.isEmpty()) {
			throw new DataNotFoundException("No Messages Found");
		}
		return list;
	}
	
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messagesForYear = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for(Message message : messages.values()) {
			calendar.setTime(message.getCreated());
			if(calendar.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
		
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if (start + size > list.size())
			return new ArrayList<Message>();
		return list.subList(start,  start + size);
	}

	public Message getMessage(long id) {
		Message message = messages.get(id);
		if(message == null) {
			throw new DataNotFoundException("Message with ID "+ id +" Not found");
		}
		return message;
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
