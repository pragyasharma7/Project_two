/**
 * 
 */
app.controller("UserController",function(UserService,$location,$scope,$rootScope,$cookieStore,$window){
	
	
	$scope.register=function(){
		UserService.registerUser($scope.user).then(function(response){
			$scope.message="Registered successfully. Please Log in."
				$location.path('/login')
		},function(response){
			$scope.error=response.data
			$location.path('/registration')
		}) 
	}
	
	$scope.login=function(){
		UserService.loginUser($scope.user).then(function(response){
			$rootScope.currentUser=response.data
			$cookieStore.put("currentUser",response.data)
			$location.path('/home')
		},function(response){
//			$scope.error=response.data.message
			$window.alert("Username or password is incorrect!Please try again, oh forgetful one.....:p")
			$location.path('/login')
			console.log(response.data)
		})
	}
	
	
	$scope.userobj=UserService.getUserDetails().then(function(response){
		     $scope.userobj=response.data
	},function(response){
		console.log(response.status)
	})
	
	
	$scope.update=function(){
		UserService.updateUserDetails($scope.userobj).then(function(response){
			$window.alert("Profile updated successfully.")
		},function(response){
			console.log(response.status)
		})
	}
})