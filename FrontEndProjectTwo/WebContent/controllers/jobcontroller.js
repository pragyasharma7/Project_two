/**
 * 
 */
app.controller("JobController",function($scope,$location,JobService){
	
	$scope.myObj = {
		    "color" : "white",
		    "background-color" : "white"
		  }
	
	$scope.save=function(){
		JobService.saveJob($scope.job).then(function(response){
			$location.path('/getalljobs')
		},function(response){
			$scope.message=response.data.message
			if(response.status==401)
			$location.path('/login')
			if(response.status==500)
			$location.path('/savejob')
		})
	}
	
	function getAllJobs(){
		$scope.jobs=JobService.getAllJobs().then(function(response){
			$scope.jobs=response.data
		},function(response){
			console.log(response.status)
		})
	}
	
	getAllJobs();
	
	$scope.viewJobDetails=function(id){
		$scope.showDetails=true;
		JobService.viewJobDetails(id).then(function(response){
			$scope.job=response.data
		},function(response){
			console.log(response.status)
		})
	}
})