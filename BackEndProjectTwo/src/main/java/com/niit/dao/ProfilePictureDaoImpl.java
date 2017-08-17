package com.niit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.ProfilePicture;

@Repository
public class ProfilePictureDaoImpl implements ProfilePictureDao{
	@Autowired
	private SessionFactory sessionFactory;

	public void save(ProfilePicture profilePicture) {
		Session session=sessionFactory.openSession();
		session.saveOrUpdate(profilePicture);
		session.flush();
		session.close();
		
	}

	public ProfilePicture getProfilePicture(String username) {
		Session session=sessionFactory.openSession();
		ProfilePicture profilePic=(ProfilePicture) session.get(ProfilePicture.class, username);
		session.close();
		return profilePic;
	}

}
