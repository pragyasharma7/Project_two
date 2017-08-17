package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.FriendDao;
import com.niit.model.Users;
import com.niit.model.Error;
import com.niit.model.Friend;

@RestController
public class FriendController {
	@Autowired
	private FriendDao friendDao;

	@RequestMapping(value="/friendsuggestions",method=RequestMethod.GET)
	public ResponseEntity<?> getSuggestedUsers(HttpSession session)
	{
		Users user=(Users) session.getAttribute("user");
		List<Users> suggestedUsers=friendDao.listOfSuggestedUsers(user.getUsername());
		
		return new ResponseEntity<List<Users>>(suggestedUsers,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/addfriend/{toUsername}",method=RequestMethod.GET)
	public ResponseEntity<?> sendFriendRequest(@PathVariable String toUsername,HttpSession session)
	{
		Users user=(Users) session.getAttribute("user");
		if(user==null)
		{
			Error error=new Error(1,"Unauthorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		friendDao.friendRequest(user.getUsername(),toUsername);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
	public ResponseEntity<?> getPendingRequests(HttpSession session)
	{
		Users user=(Users) session.getAttribute("user");
		if(user==null)
		{
			Error error=new Error(2,"Unauthorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Friend> getRequests=friendDao.pendingRequests(user.getUsername());
		if(getRequests.isEmpty())
		{
			Error error=new Error(5,"You have no pending Requests");
			return new ResponseEntity<Error>(error,HttpStatus.OK);
		}
		return new ResponseEntity<List<Friend>>(getRequests,HttpStatus.OK); 
	}
	
	@RequestMapping(value="/updatependingrequest/{fromId}/{status}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateRequest(@PathVariable String fromId,@PathVariable char status, HttpSession session)
	{
		Users user=(Users) session.getAttribute("user");
		if(user==null)
		{
			Error error=new Error(3,"Unauthorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		friendDao.updatePendingRequest(fromId, user.getUsername(), status);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/viewfriends",method=RequestMethod.GET)
	public ResponseEntity<?> getFriendList(HttpSession session)
	{
		Users user=(Users) session.getAttribute("user");
		if(user==null)
		{
			Error error=new Error(4,"Unauthorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Friend> friendList=friendDao.getFriendList(user.getUsername()); 
		return new ResponseEntity<List<Friend>>(friendList,HttpStatus.OK);
	}
}
