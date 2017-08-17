/**
 * 
 */
app.controller("FriendController",function($scope,$location,FriendService,$window){
	function friendSuggestions(){
		$scope.listOfSuggestedFriends=FriendService.getFriendSuggestions().then(function(response){
			$scope.listOfSuggestedFriends=response.data
//			$window.alert("Data successfully fetched")
		},function(response){
			console.log(response.status);
		})
	}
	
	$scope.friendRequest=function(toUsername){
		FriendService.sendFriendRequest(toUsername).then(function(response){
			friendSuggestions()
			$location.path('/friendsuggestions')
			$window.alert("Friend Request sent successfully")
		},function(response){
			$window.alert("Friend request failed.Check console for errors")
			console.log(response.status)
		})
	}
	
	function pendingRequests(){
		$scope.pendingRequests=FriendService.getPendingRequests().then(function(response){
			$scope.pendingRequests=response.data
			$scope.message=response.data.message
		},function(response){
			$window.alert("Fetching Data failed. Check console for errors")
			console.log(response.status)
		})
	}
	
	$scope.acceptOrRejectRequest=function(fromId,status){
		FriendService.acceptOrReject(fromId,status).then(function(response){
			$window.alert("Friend added successfully")
			pendingRequests();
			$location.path('/pendingrequests')
		},function(response){
			$window.alert("Failed to process action")
			console.log(response.status)
		})
	}
	
	function viewFriendList(){
		FriendService.getFriendList().then(function(response){
			$scope.friends=response.data
			console.log(response.data)
		},function(response){
			$window.alert("Failed to fetch Friend List. Read console for details")
			console.log(response.status) 
		})
	}
	
	$scope.friendDetails=function(username){
		$scope.showDetails=false
		FriendService.getFriendDetails(username).then(function(response){
			$scope.showDetails=true
			$scope.friendDetails=response.data
		},function(response){
			$window.alert("Failed to fetch Friend Details. Read console for details")
			console.log(response.status)
		})
	}
	
	friendSuggestions();
	pendingRequests();
	viewFriendList();
})