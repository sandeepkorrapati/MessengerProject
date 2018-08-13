package org.niu.Messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.niu.Messenger.model.Profile;
import org.niu.Messenger.service.ProfileService;

@Path("/profiles")
public class ProfileResource {

	ProfileService profileService = new ProfileService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Profile> getProfiles() {
		return profileService.getAllProfiles();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName) {
		return profileService.getProfile(profileName);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProfile (Profile profile, @Context UriInfo uriInfo) {
		Profile newProfile = profileService.addProfile(profile);
		String newName = String.valueOf(newProfile.getProfileName());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newName).build();
		return Response.created(uri)
				.entity(newProfile)
				.build();
		
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{profileName}")
	public Profile updateProfile (@PathParam("profileName") String profileName, Profile profile) {
		profile.setProfileName(profileName);
		return profileService.updateProfile(profile);
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName) {
		profileService.removeProfile(profileName);
	}
}
