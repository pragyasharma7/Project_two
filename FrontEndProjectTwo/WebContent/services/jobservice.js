/**
 * 
 */
app.factory("JobService",function($http){
	var jobService={}
	jobService.saveJob=function(job){
		return $http.post("http://localhost:8079/BackEndProjectTwo/savejob",job)
	}
	
	jobService.getAllJobs=function(){
		return $http.get("http://localhost:8079/BackEndProjectTwo/getalljobs")
	}
	
	jobService.viewJobDetails=function(id){
		return $http.get("http://localhost:8079/BackEndProjectTwo/viewjob/"+id)
	}
	return jobService;
})