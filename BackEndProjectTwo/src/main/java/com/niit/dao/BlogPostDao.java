package com.niit.dao;

import java.util.List;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;

public interface BlogPostDao {
void saveBlogPost(BlogPost blogPost);
List<BlogPost> getallblogs(boolean approved);
BlogPost getBlogById(int id);
void updateBlogPost(BlogPost blogPost);
void addComment(BlogComment blogComment);
List<BlogComment> getBlogComments(int blogId);
List<BlogComment> getBlogComments2(BlogPost blogPost);
List<BlogPost> getBlogs(String username);
void addCommentWall(int blogId,String comment,String user);
void updateBlog(BlogPost blogPost);
}
