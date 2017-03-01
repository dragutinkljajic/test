(function() {
	var app = angular.module('register', []);
	
	app.controller('RegistrationController', ['$http', '$window', function($http, $window) {
		var control = this;
		control.user = {};
		control.result = "";
		control.type = [
		      {id: '1', name: 'Customer'},
		      {id: '2', name: 'Supplier'}
		    ];
		
		control.selected = {id: '1', name: 'Customer'};
		
		this.register = function() {
			control.result = "";
			if (control.selected.id == "1") {
				if (control.user.password == control.user.repeated) {
					control.result = "Processing request."
					var customer = {}
					customer.email = control.user.email;
					customer.password = control.user.password;
					customer.name = control.user.name;
					customer.surname = control.user.surname;
					
					$http.post('/register/customer', customer).then(function success(response) {
						control.result = response.data;
						
					}, function error(response) {
						control.result = "Unknown error ocurred."
					});
				} else {
					control.user.password = "";
					control.user.repeated = "";
					control.result = "Passwords must match.";
				}
			} else {
				if(control.user.password == control.user.repeated){
					control.result = "Processing request.";
					var supplier = {};
					supplier.email = control.user.email;
					supplier.password = control.user.password;
					supplier.label = control.user.label;
					supplier.description = control.user.description;
					
					$http.post('/register/supplier', supplier).then(function success(response) {
						control.result = response.data;
						
					}, function error(response) {
						control.result = "Unknown error ocurred."
					});
				}else{
					control.user.password = "";
					control.user.repeated = "";
					control.result = "Passwords must match";
				}
			}
		}
	}]);
})();