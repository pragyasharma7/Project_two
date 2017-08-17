/**
 * 
 */
app.factory("BlogService",function($http){
	var blogService={}
	blogService.saveBlogPost=function(blogPost){
		return $http.post("http://localhost:8079/BackEndProjectTwo/saveblogpost",blogPost)  
	}
	
	blogService.getBlogsApproved=function(){
		return $http.get("http://localhost:8079/BackEndProjectTwo/listofblogs/"+true)
	}
	
	blogService.getBlogsWaitingForApproval=function(){
		return $http.get("http://localhost:8079/BackEndProjectTwo/listofblogs/"+false)
	}
	
	blogService.getBlogPost=function(id){
		return $http.get("http://localhost:8079/BackEndProjectTwo/getblogpost/"+id)
	}
	
	blogService.updateBlogPost=function(blogPost){
		return $http.put("http://localhost:8079/BackEndProjectTwo/updateblog",blogPost)
	}
	
	blogService.addComment=function(blogComment){
		return $http.post("http://localhost:8079/BackEndProjectTwo/addblogcomment",blogComment) 
	}
	
	blogService.getComments=function(blogId){
		return $http.get("http://localhost:8079/BackEndProjectTwo/getblogcomments/"+blogId)
	}
	
	blogService.viewWall=function(username){
		return $http.get("http://localhost:8079/BackEndProjectTwo/wall/"+username)
	}
	
	blogService.addCommentWall=function(blog){
		return $http.put("http://localhost:8079/BackEndProjectTwo/addcommentwall",blog) 
	}
	
	return blogService;
})