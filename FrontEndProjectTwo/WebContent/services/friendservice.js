/**
 * 
 */
app.factory("FriendService",function($http){
	var friendService={}
	
	friendService.getFriendSuggestions=function(){
		return $http.get("http://localhost:8079/BackEndProjectTwo/friendsuggestions")
	}
	
	friendService.sendFriendRequest=function(toUsername){
		return $http.get("http://localhost:8079/BackEndProjectTwo/addfriend/"+toUsername)
	}
	
	friendService.getPendingRequests=function(){
		return $http.get("http://localhost:8079/BackEndProjectTwo/pendingrequests")
	}
	
	friendService.acceptOrReject=function(fromId,status){
		return $http.put("http://localhost:8079/BackEndProjectTwo/updatependingrequest/"+fromId+"/"+status)
	}
	
	friendService.getFriendList=function(){
		return $http.get("http://localhost:8079/BackEndProjectTwo/viewfriends")
	}
	
	friendService.getFriendDetails=function(username){
		return $http.get("http://localhost:8079/BackEndProjectTwo/viewFriend/"+username)
	}
	return friendService;
})