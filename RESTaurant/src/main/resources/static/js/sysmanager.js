(function(){
	var app = angular.module('sysmanager', []);
		
	app.controller("TabController", function(){
	    this.tab = 1;

	    this.isSet = function(checkTab) {
	      return this.tab === checkTab;
	    };

	    this.setTab = function(setTab) {
	      this.tab = setTab;
	    };
	});
	
	app.controller("SysManController", ['$http', '$window', function($http, $window){
		var control = this;
		control.sysman = {};
		control.sysmans = [];
		control.result = "";
		
		this.register = function(){
			
			$http.post('/sysman/addSystemManager', this.sysman).then(function success(response) {
				
				control.result = response.data;
				if(control.result === "OK"){
					control.sysmans.push(control.sysman);
					control.sysman = {};
				}
			}, function error(response) {
				control.result = "Unknown error ocurred."
			});
		};
		
		this.getSysMans = function(){
			$http.get('/sysman/getSystemManagers').then(function success(response){
				control.sysmans = response.data;
			}), function error(response){
				control.result = "Unknown error ocurred."
			}
		};
		
		this.getSysMans();
	}]);
	
	app.controller("RestManController", ['$http', '$window', function($http, $window){
		var control = this;
		control.restman = {};
		control.restmans = [];
		control.result = "";
		
		this.register = function(){
			
			$http.post('/sysman/addRestaurantManager', this.restman).then(function success(response) {
				control.result = response.data;
				if(control.result === "OK"){
					control.restmans.push(control.restman);
					control.restman = {};
				}else if(control.result === "No such restaurant."){
					control.restman.restaurant.name = "";
				}
			}, function error(response) {
				control.result = "Unknown error ocurred.";
			});
		};
		
		this.getRestMans = function(){
			$http.get('/sysman/getRestaurantManagers').then(function success(response){
				control.restmans = response.data;
			}), function error(response){
				control.result = "Unknown error ocurred."
			}
		};
		
		this.getRestMans();
	}]);
	
	app.controller("RestaurantController", ['$http', '$window', function($http, $window){
		var control = this;
		control.restaurant = {};
		control.restaurants = [];
		control.result = "";
		
		this.register = function(){
			$http.post('/sysman/addRestaurant', this.restaurant).then(function success(response) {
				
				control.result = response.data;
				if(control.result === "OK"){
					control.restaurants.push(control.restaurant);
					control.restaurant = {};
				}
				
			}, function error(response) {
				control.result = "Unknown error ocurred."
			});
		};
		
		this.getRestaurants = function(){
			$http.get('/sysman/getRestaurants').then(function success(response){
				control.restaurants = response.data;
			}), function error(response){
				control.result = "Unknown error ocurred."
			}
		};
		
		this.getRestaurants();
	}]);
})();