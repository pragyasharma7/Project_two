/**
 * 
 */
app.controller('BlogDetailController',function($scope,$location,BlogService,$routeParams){
	var id=$routeParams.id
	
	$scope.blogPost=BlogService.getBlogPost(id).then(function(response){
		$scope.blogPost=response.data;
	},function(response){
		console.log(response.status)
	})
	
	$scope.updateApproval=function(){
		BlogService.updateBlogPost($scope.blogPost).then(function(response){
			$location.path('/getallblogs')
		},function(response){
			console.log(response.status);
			$location.path('/getBlogForApproval/'+id)
		})
	}
	
	$scope.addComment=function(){
		$scope.blogComment.blogPost=$scope.blogPost
		BlogService.addComment($scope.blogComment).then(function(response){
			console.log(response.status)
			alert('Comment added successfully')
			$scope.blogComment.body=''
		},function(response){
			console.log(response.status)
		})
	}
	
	$scope.getBlogComments=function(blogId){
		$scope.showComments=true
		BlogService.getComments(blogId).then(function(response){
			$scope.blogComments=response.data
			console.log(response.status)
		},function(response){
			console.log(response.status)
		})
	}
	
	/*function getAllComments(){
		$scope.showComments=true
		$scope.blogComments=BlogService.getAllComments($scope.blogPost).then(function(response){
			$scope.blogComments=response.data
		},function(response){
			console.log(response.status)
			alert('Fetching comments failed!')
		})
	}
	getAllComments();*/
})