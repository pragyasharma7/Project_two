package com.niit.dao;

import com.niit.model.ProfilePicture;

public interface ProfilePictureDao {
	void save(ProfilePicture profilePicture);
	ProfilePicture getProfilePicture(String username);

}
