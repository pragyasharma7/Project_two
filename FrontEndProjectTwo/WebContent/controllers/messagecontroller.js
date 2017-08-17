/**
 * 
 */
app.controller("MessageController",function($scope,MessageService,$window,$routeParams,$timeout,$interval){
	var username=$routeParams.username
	$scope.heading=username
	$scope.send=function(){
		$scope.message.toUser=username
		    MessageService.sendMessage($scope.message).then(function(response){
		    	viewMessages(username)
            console.log(response.status)
		},function(response){
			$window.alert("Failed to send Message.")
			console.log(response.status)
		})
	}
	
	function viewMessages(username){
		$scope.messages=MessageService.viewMessage(username).then(function(response){
			$scope.messages=response.data
		},function(response){
			$window.alert("Failed to fetch Messages.")
			console.log(response.status)
		})
	}
	viewMessages(username);
	
	
	$interval(function () {
        viewMessages(username);
    }, 10000);
})