'use strict';

App.factory('VideoService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllVideos: function() {
					return $http.get('/videoList/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching videos');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createVideo: function(video){
					return $http.post('/video/', video)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating video');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateVideo: function(video, id){
					return $http.post('/video/'+id, video)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating video');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteVideo: function(id){
					return $http.delete('/video/'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting video');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);
