/**
 * 
 */
app.factory("UserService",function($http){
	var userService={}

	userService.registerUser=function(user){
		return $http.post("http://localhost:8079/BackEndProjectTwo/registration",user)
	}
	
	userService.loginUser=function(user){
		return $http.post("http://localhost:8079/BackEndProjectTwo/login",user)
	}
	
	userService.logoutUser=function(user){
		return $http.get("http://localhost:8079/BackEndProjectTwo/logout",user)
	}
	
	userService.getUserDetails=function(){
		return $http.get("http://localhost:8079/BackEndProjectTwo/getuserdetails")
	}
	
	userService.updateUserDetails=function(user){
		return $http.put("http://localhost:8079/BackEndProjectTwo/updateprofile",user)
	}
	return userService;
})