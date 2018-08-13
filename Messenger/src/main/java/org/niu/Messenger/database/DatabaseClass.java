package org.niu.Messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.niu.Messenger.model.Message;
import org.niu.Messenger.model.Profile;

public class DatabaseClass {
	
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();

	
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}

	public static Map<Long, Message> getMessages() {
		return messages;
	}
}
