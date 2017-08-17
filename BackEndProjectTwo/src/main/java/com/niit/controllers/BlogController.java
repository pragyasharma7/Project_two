package com.niit.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.BlogPostDao;
import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.Users;
import com.niit.model.Error;

@RestController
public class BlogController {
	@Autowired
	private BlogPostDao blogPostDao;

	@RequestMapping(value = "/saveblogpost", method = RequestMethod.POST)
	public ResponseEntity<?> saveBlog(@RequestBody BlogPost blogPost, HttpSession session) {
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			Error error = new Error(1, "Unauthorized");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		try {
			blogPost.setPostedOn(new Date());
			blogPost.setCreatedBy(user);
			blogPostDao.saveBlogPost(blogPost);
			return new ResponseEntity<Void>(HttpStatus.OK);

		} catch (Exception e) {
			Error error = new Error(2, "Could not insert Blog details");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/listofblogs/{approved}",method=RequestMethod.GET)
	public ResponseEntity<?> getAllBlogs(@PathVariable boolean approved, HttpSession session)
	{
		Users user=(Users) session.getAttribute("user");
		if(user==null)
		{
			Error error=new Error(3,"Unauthorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<BlogPost> blogs=blogPostDao.getallblogs(approved);
		return new ResponseEntity<List<BlogPost>>(blogs,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getblogpost/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogPost(@PathVariable int id,HttpSession session)
	{
		Users user=(Users) session.getAttribute("user");
		if(user==null)
		{
			Error error=new Error(4,"Unauthorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		BlogPost blogPost=blogPostDao.getBlogById(id);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK); 
	}
	
	@RequestMapping(value="/updateblog",method=RequestMethod.PUT)
	public ResponseEntity<?> updateBlogPost(@RequestBody BlogPost blogPost,HttpSession session)
	{
		Users user=(Users) session.getAttribute("user");
		if(user==null)
		{
			Error error=new Error(5,"Unauthorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		blogPostDao.updateBlogPost(blogPost);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/addblogcomment", method=RequestMethod.POST)
	public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session)
	{
		Users user=(Users) session.getAttribute("user");
		if(user==null)
		{
			Error error=new Error(6,"Unauthorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		try{
		blogComment.setCommentedBy(user);
		blogComment.setCommentedOn(new Date());
		blogPostDao.addComment(blogComment);
		return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(Exception e)
		{
			Error error=new Error(7,"Unable to post comment");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
	}
	
	@RequestMapping(value="/getblogcomments/{blogId}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogComments(@PathVariable int blogId,HttpSession session)
	{
		Users user=(Users) session.getAttribute("user");
		if(user==null)
		{
			Error error=new Error(8,"Unauthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		try{
			List<BlogComment> blogComments=blogPostDao.getBlogComments(blogId);
			return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
		}catch(Exception e)
		{
			Error error=new Error(9,"Unable to insert comment");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/wall/{username}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogs(@PathVariable String username)
	{
		try{
			List<BlogPost> blogs=blogPostDao.getBlogs(username);
		return new ResponseEntity<List<BlogPost>>(blogs,HttpStatus.OK);
		}catch(Exception e){
			Error error=new Error(10,"Unable to fetch Wall");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value="/addcommentwall",method=RequestMethod.PUT)
	public ResponseEntity<?> addCommentWall(@RequestBody BlogPost blogPost,HttpSession session)
	{
		Users user=(Users) session.getAttribute("user");
		if(user==null)
		{
			Error error=new Error(9,"Unauthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		blogPostDao.updateBlog(blogPost); 
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	

}
