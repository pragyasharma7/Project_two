package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.ProfilePictureDao;
import com.niit.model.Error;
import com.niit.model.ProfilePicture;
import com.niit.model.Users;

@Controller
public class ProfilePictureController {
	@Autowired
	private ProfilePictureDao profilePictureDao;

	@RequestMapping(value="/uploaddp",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePic(@RequestParam CommonsMultipartFile image,HttpSession session)
	{
		Users user=(Users) session.getAttribute("user");
		if(user==null)
		{
			Error error=new Error(1,"Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		ProfilePicture profilePic=new ProfilePicture();
		profilePic.setUsername(user.getUsername());
		profilePic.setImage(image.getBytes());
		profilePictureDao.save(profilePic); 
		return new ResponseEntity<Users>(user,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="getimage/{username}",method=RequestMethod.GET)
	public @ResponseBody byte[] getProfilePic(@PathVariable String username,HttpSession session)
	{
		Users user=(Users) session.getAttribute("user");
		if(user==null)
		{
			return null;
		}
		else
		{
			ProfilePicture profilePic=profilePictureDao.getProfilePicture(username);
			if(profilePic==null)
			{
				return null;
			}
			else
			{
				return profilePic.getImage();
			}
		}
	}
}
