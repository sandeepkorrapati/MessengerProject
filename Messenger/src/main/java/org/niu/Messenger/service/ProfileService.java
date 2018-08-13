package org.niu.Messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.niu.Messenger.database.DatabaseClass;
import org.niu.Messenger.exception.DataNotFoundException;
import org.niu.Messenger.model.Message;
import org.niu.Messenger.model.Profile;

public class ProfileService {
	
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	
	
	public ProfileService() {
		super();
		//profiles.put("Sandeep", new Profile(1L, "Sandeep", "Sandeep", "Korrapati"));
	}

	public List<Profile> getAllProfiles() {
		ArrayList<Profile> profile = new ArrayList<Profile>(profiles.values());
		if(profile.isEmpty()) {
			throw new DataNotFoundException("No Profiles Found");
		}
		return profile;
	}
	
	public Profile getProfile (String profileName) {
		 Profile profile = profiles.get(profileName);
		 if(profile == null) {
			 throw new DataNotFoundException("No Profile Found with the name " + profileName);
		 }
		 return profile;
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
		
	}
	
	public Profile updateProfile(Profile profile) {
		if(profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
