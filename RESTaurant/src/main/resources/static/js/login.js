(function() {
	var app = angular.module('login', []);
	
	app.controller('AuthorizationController', ['$http', '$window', function($http, $window) {
		
		this.authorize = function() {
			
			$http({
				method: 'POST',
				url: '/login/authorize',
				headers: {
					   'Content-Type': 'text/plain'
					 },
					 data: 'login'
			}).then(function success(response) {
				if (response.data == "Logged in") {
					$window.location.href = '/index.html';
				}
			});
		}
		
		this.authorize();
	}]);
	
	app.controller('LoginController', [ '$http', '$window', function($http, $window) {
		var control = this;
		control.user = {};
		control.result = "";
		
		this.login = function() {
			$http.post('/login/login', this.user).then(function success(response) {
				if (response.data.indexOf("Logged in") !== -1) {
					if (response.data.indexOf("CUSTOMER") !== -1)
						$window.location.href = '/index.html';
					else if (response.data.indexOf("SYSMANAGER") !== -1)
						$window.location.href = '/sysmanager.html';
					else if (response.data.indexOf("RESTMANAGER") !== -1)
						$window.location.href = '/restmanager.html';
					else if (response.data.indexOf("SUPPLIER") !== -1)
						//NE POSTOJI RESURS JOS UVEK
						$window.location.href = '/supplier.html';
					else if (response.data.indexOf("EMPLOYEE") !== -1)
						//NE POSTOJI RESURS JOS UVEK
						$window.location.href = '/employee.html';
				} else {
					control.result = response.data;
				}
				
			}, function error(response) {
				control.result = "Unknown error ocurred."
			});
		}
		
		
	}]);
	
})();