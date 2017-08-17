/**
 * 
 */
app.controller("WallController",function($scope,$location,BlogService,$window,$routeParams,$rootScope,FriendService){
	var username=$routeParams.username
	$scope.wallName=username
	
		$scope.blogPost=BlogService.viewWall(username).then(function(response){
			$scope.blogPost=response.data
		},function(response){
			$window.alert('Failed to open Wall')
			console.log(response.status)
		})
		
		$scope.model = {};
		$scope.addComment=function(blog){
		$scope.date = new Date();
		blog.comments.push({"body":$scope.comment,"commentedOn":$scope.date,"commentedBy":$rootScope.currentUser});
		BlogService.addCommentWall(blog).then(function(response){
			console.log(response.status)
			alert('Comment added successfully')
			$scope.comment=''
		},function(response){
			console.log(response.status)
		})
	}
		
		/*$scope.getBlogComments=function(blogId){
		$scope.showComments=true
		BlogService.getComments(blogId).then(function(response){
			$scope.blogComments=response.data
			console.log(response.data)
			console.log(response.status)
		},function(response){
			console.log(response.status)
		})
	}
*/
		function friendDetails(username){
			FriendService.getFriendDetails(username).then(function(response){
				$scope.friendDetailsOne=response.data
			},function(response){
				$window.alert("Failed to fetch Friend Details. Read console for details")
				console.log(response.status)
			})
		}
		friendDetails(username);
		
		$scope.saveBlogPost = function() {
			BlogService.saveBlogPost($scope.blogPost).then(function(response) {
			$scope.message = 'Blog posted successfully and awaiting Administrator approval. Have a nice day!'
			$location.path('/listofblogs')
			},function(response) {
			$scope.message = response.data.message
			if (response.status == 401) 
			{
		       $location.path('/login')
			}
			if (response.status == 500) 
			{
			   $location.path('/saveblogpost')
			}
		})
		}
	
	
})