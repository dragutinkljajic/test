(function(){
	var app = angular.module('restmanager', ['mwl.calendar', 'ngAnimate', 'ui.bootstrap', 'colorpicker.module']);
	
	app.controller("TabController", function(){
	    this.tab = 1;

	    this.isSet = function(checkTab) {
	      return this.tab === checkTab;
	    };

	    this.setTab = function(setTab) {
	      this.tab = setTab;
	    };
	});
	
	app.service('restaurantService', function(){
		var restaurant = {};
		var manager = {};
		
		var setRestaurantManager = function(newRestaurantManager) {
			manager = newRestaurantManager;
		}
		
		var getRestaurantManager = function() {
			return manager;
		}
		
		var setRestaurant = function(newRestaurant) {
			restaurant = newRestaurant;
		}
		
		var getRestaurant = function() {
			return restaurant;
		}
		
		var getRestaurantMenu = function(){
			return restaurant.menu;
		}
		
		var getRestaurantDrinksMenu = function(){
			return restaurant.drinksMenu;
		}
		
		var getRestaurantSeating = function(){
			return restaurant.seatingArrangement;
		}
		
		var getRestaurantSchedule = function(){
			return restaurant.schedule;
		}
		
		var getRestaurantDetails = function() {
			var tmp = {};
			tmp.restaurantID = restaurant.restaurantID;
			tmp.name = restaurant.name;
			tmp.type = restaurant.type;
			tmp.description = restaurant.description;
			return tmp;
		}
		
		var setRestaurantName = function(name) {
			restaurant.name = name;
		}
		
		var setRestaurantDetails = function(details) {
			restaurant.type = details.type;
			restaurant.description = details.description;
		}
		
		return {
		    setRestaurant: setRestaurant,
		    getRestaurant: getRestaurant,
		    getRestaurantDetails: getRestaurantDetails,
		    setRestaurantName: setRestaurantName,
		    setRestaurantDetails: setRestaurantDetails,
		    getRestaurantMenu: getRestaurantMenu,
		    getRestaurantDrinksMenu: getRestaurantDrinksMenu,
		    setRestaurantManager: setRestaurantManager,
		    getRestaurantManager: getRestaurantManager,
		    getRestaurantSeating: getRestaurantSeating,
		    getRestaurantSchedule: getRestaurantSchedule
		  };
	});
	
	app.controller('HomeController', ['$scope', '$http', '$window', 'restaurantService', function($scope, $http, $window, restaurantService) {
		var control = this;
		control.restaurant = {};
		control.dish = {};
		control.dishes = [];
		control.employee = {};
		control.employees = [];
		
		this.getDishes = function(){
			$http.get('/restmanager/getDishesNew').then(function success(response){
				control.dishes = response.data;
				
				for(it in control.dishes){
					control.dishes[it].rating = (Math.random() * 4 + 1).toFixed(2);
				}
				
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
			
		};
		
		this.getEmployees = function(){
			$http.get('/restmanager/getEmployeesNew').then(function success(response){
				control.employees = response.data;
				
				for(it in control.employees){
					control.employees[it].rating = (Math.random() * 4 + 1).toFixed(2);
				}
			}), function error(response){
				control.result = "Unknown error ocurred."
			}
		};
		
		this.getRestaurant = function(){
			$http.get('/restmanager/getRestaurantNew').then(function success(response){
				control.restaurant = response.data;
				
				control.restaurant.rating = (Math.random() * 4 + 1).toFixed(2);
				
			}), function error(response){
				control.result = "Unknown error ocurred."
			}
		};
		
		$scope.$watch('tab.tab', function(newValue) {
			if(newValue == 1){
				control.getRestaurant();
				control.getDishes();
				control.getEmployees();
			}
		});
		
		$scope.visitsChart = new CanvasJS.Chart("visitsContainer",
	    {
	      title: {
	        text: "Weekly visits"
	      },
	        data: [
	      {
	        type: "area",
	        dataPoints: [//array

	        { x: new Date(2017, 02, 1), y: Math.floor(Math.random()*30) + 1},
	        { x: new Date(2017, 02, 2), y: Math.floor(Math.random()*30) + 1},
	        { x: new Date(2017, 02, 3), y: Math.floor(Math.random()*30) + 1},
	        { x: new Date(2017, 02, 4), y: Math.floor(Math.random()*30) + 1},
	        { x: new Date(2017, 02, 5), y: Math.floor(Math.random()*30) + 1},
	        { x: new Date(2017, 02, 6), y: Math.floor(Math.random()*30) + 1},
	        { x: new Date(2017, 02, 7), y: Math.floor(Math.random()*30) + 1},
	        ]
	      }
	      ]
	    });

	    $scope.visitsChart.render();
	    
		$scope.incomeChart = new CanvasJS.Chart("incomeContainer",
			    {
			      title: {
			        text: "Yearly income"
			      },
			        data: [
			      {
			        type: "area",
			        dataPoints: [//array

			        { x: new Date(2016, 00, 1), y: Math.floor(Math.random()*2000) + 1},
			        { x: new Date(2016, 01, 1), y: Math.floor(Math.random()*2000) + 1},
			        { x: new Date(2016, 02, 1), y: Math.floor(Math.random()*2000) + 1},
			        { x: new Date(2016, 03, 1), y: Math.floor(Math.random()*2000) + 1},
			        { x: new Date(2016, 04, 1), y: Math.floor(Math.random()*2000) + 1},
			        { x: new Date(2016, 05, 1), y: Math.floor(Math.random()*2000) + 1},
			        { x: new Date(2016, 06, 1), y: Math.floor(Math.random()*2000) + 1},
			        { x: new Date(2016, 07, 1), y: Math.floor(Math.random()*2000) + 1},
			        { x: new Date(2016, 08, 1), y: Math.floor(Math.random()*2000) + 1},
			        { x: new Date(2016, 09, 1), y: Math.floor(Math.random()*2000) + 1},
			        { x: new Date(2016, 10, 1), y: Math.floor(Math.random()*2000) + 1},
			        { x: new Date(2016, 11, 1), y: Math.floor(Math.random()*2000) + 1}
			        ]
			      }
			      ]
			    });

			    $scope.incomeChart.render();
	    
	}]);
	
	app.controller('RestManagerController', ['$http', '$window', 'restaurantService', function($http, $window, restaurantService) {
		
		this.authorize = function() {
			
			$http({
				method: 'POST',
				url: '/login/authorize',
				headers: {
					   'Content-Type': 'text/plain'
					 },
					 data: 'index'
			}).then(function success(response) {
				if (response.data == "Not logged in") {
					$window.location.href = '/login.html';
				}
			});
		}
		
		this.logout = function() {
			$http.get("/login/logout").then(function(response) {
				$window.location.href = '/login.html';
			});
			
		}
		
		this.loadUser = function() {
			
			$http({
				method: 'GET',
				url: '/restmanager/load'
			}).then(function success(response) {
				if (response.data.email != null) {
					restaurantService.setRestaurant(response.data.restaurant);
					restaurantService.setRestaurantManager(response.data);
				}
			});
		}
				
		this.authorize();
		this.loadUser();
	}]);
	
	app.controller('RestaurantController', ['$scope', '$http', '$window', 'restaurantService', function($scope, $http, $window, restaurantService) {
		var control = this;
		control.form = {};
		
		this.update = function(type) {
			var requestData = {};
			
			
			if (type == 0) {
				requestData.restaurantID = control.form.restaurantID;
				requestData.name = control.form.name;
				$http({
					method: 'POST',
					url: '/restmanager/updateRestaurantName',
					headers: {
						   'Content-Type': 'application/json',	   
						 },
						 data: requestData
				}).then(function success(response) {
					if(response.data === "same"){
						//qwe
					}else if(response.data === "taken"){
						toastr["error"]('Restaurant name is already taken.');
					}else if(response.data === "No name sent"){
						toastr["error"](response.data);
					}else{
						toastr["success"]('Restaurant name successfully updated.');
						control.form.name = response.data;
						restaurantService.setRestaurantName(response.data);
					}
				});
			} else if (type == 1) {
				requestData.restaurantID = control.form.restaurantID;
				requestData.type = control.form.type;
				requestData.description = control.form.description;
				$http({
					method: 'POST',
					url: '/restmanager/updateRestaurantDetails',
					headers: {
						   'Content-Type': 'application/json',	   
						 },
						 data: requestData
				}).then(function success(response) {
					toastr["success"]('Restaurant details successfully updated.');
					control.form.type = response.data.type;
					control.form.description = response.data.description;
					restaurantService.setRestaurantDetails(response.data);
				});
			}
		}

		$scope.$watch('tab.tab', function(newValue) {
			if(newValue == 2)
				control.form = JSON.parse(JSON.stringify(restaurantService.getRestaurantDetails()));
		});

	}]);
	
	app.controller('MenuController', ['$scope', '$http', '$window', 'restaurantService', function($scope, $http, $window, restaurantService) {
		var control = this;
		control.dish = {};
		control.dishes = [];
		control.dishType = [
			{
				id: 0,
				value: "Baked"
			},
			{
				id: 1,
				value: "Vegan"
			},
			{
				id: 2,
				value: "Cooked"
			},
			{
				id: 3,
				value: "Grilled"
			},
			{
				id: 4,
				value: "Universal"
			}
		];
		control.result = "";
		
		this.register = function(){
			control.dish.menu = restaurantService.getRestaurantMenu();
			$http.post('/restmanager/addDish', this.dish).then(function success(response) {
				control.result = response.data;
				if(control.result === "OK"){
					control.dishes.push(control.dish);
					toastr["success"]('Dish successfully added.');
					control.dish = {};
				}
			}, function error(response) {
				control.result = "Unknown error ocurred."
			});
		};
		
		this.getDishes = function(){
			$http.post('/restmanager/getDishes', restaurantService.getRestaurantMenu()).then(function success(response){
				control.dishes = response.data;
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
			
		};
		
		$scope.$watch('tab.tab', function(newValue) {
			if(newValue == 3){
				control.getDishes();
			}
		});
		
		this.removeDish = function(dish){
			$http.post('/restmanager/removeDish', dish).then(function success(response){
				toastr["success"](response.data);
				var index = -1;		
				var dishArr = eval( control.dishes );
				for( var i = 0; i < dishArr.length; i++ ) {
					if( dishArr[i].label === dish.label ) {
						index = i;
						break;
					}
				}
				if( index === -1 ) {
					toastr["error"]('Something gone wrong.');
				}
				control.dishes.splice( index, 1 );
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
		}
	}]);
	
	app.controller('DrinksMenuController', ['$scope', '$http', '$window', 'restaurantService', function($scope, $http, $window, restaurantService) {
		var control = this;
		control.drink = {};
		control.drinks = [];
		control.result = "";
		
		this.register = function(){
			control.drink.drinksMenu = restaurantService.getRestaurantDrinksMenu();
			$http.post('/restmanager/addDrink', this.drink).then(function success(response) {
				control.result = response.data;
				if(control.result === "OK"){
					control.drinks.push(control.drink);
					toastr["success"]('Drink successfully added.');
					control.drink = {};
				}
			}, function error(response) {
				control.result = "Unknown error ocurred."
			});
		};
		
		this.getDrinks = function(){
			$http.post('/restmanager/getDrinks', restaurantService.getRestaurantDrinksMenu()).then(function success(response){
				control.drinks = response.data;
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
			
		};
		
		$scope.$watch('tab.tab', function(newValue) {
			if(newValue == 4)
				control.getDrinks();
		});
		
		this.removeDrink = function(drink){
			$http.post('/restmanager/removeDrink', drink).then(function success(response){
				toastr["success"](response.data);
				var index = -1;		
				var drinkArr = eval( control.drinks );
				for( var i = 0; i < drinkArr.length; i++ ) {
					if( drinkArr[i].label === drink.label ) {
						index = i;
						break;
					}
				}
				if( index === -1 ) {
					toastr["error"]('Something gone wrong.');
				}
				control.drinks.splice( index, 1 );
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
		}
	}]);
	

	app.controller('BidsController', ['$scope', '$http', '$window', 'restaurantService', function($scope, $http, $window, restaurantService){
		var control = this;
		control.drinks = [];
		control.groceries = [];
		control.bid = {};
		control.bid.drinks = [];
		control.bid.groceries = [];
		
		$scope.bidBegin = {
		         value: new Date()
		};
		
		this.getAllDrinks = function(){
			$http.get('/restmanager/getAllDrinks').then(function success(response){
				control.drinks = response.data;
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
		}
		
		this.getAllGroceries = function(){
			$http.get('/restmanager/getAllGroceries').then(function success(response){
				control.groceries = response.data;
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
		}
		
		this.addDrink = function(drink){
			var index = -1;		
			var drinkArr = eval( control.bid.drinks );
			for( var i = 0; i < drinkArr.length; i++ ) {
				if( drinkArr[i].label === drink.label ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				control.bid.drinks.push(drink);
			}
		}
		
		this.addGrocery = function(grocery){
			var index = -1;		
			var groceryArr = eval( control.bid.groceries );
			for( var i = 0; i < groceryArr.length; i++ ) {
				if( groceryArr[i].label === grocery.label ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				control.bid.groceries.push(grocery);
			}
		}
		
		this.removeDrink = function(drink){
			var index = -1;		
			var drinkArr = eval( control.bid.drinks );
			for( var i = 0; i < drinkArr.length; i++ ) {
				if( drinkArr[i].label === drink.label ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				toastr["error"]( "Something gone wrong" );
			}
			control.bid.drinks.splice( index, 1 );
		}
		
		this.removeGrocery = function(grocery){
			var index = -1;		
			var groceryArr = eval( control.bid.groceries );
			for( var i = 0; i < groceryArr.length; i++ ) {
				if( groceryArr[i].label === grocery.label ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				toastr["error"]( "Something gone wrong" );
			}
			control.bid.groceries.splice( index, 1 );
		}
		
		this.addBid = function(){
			control.bid.beginning = $scope.bidBegin.value;
			control.bid.end= $scope.bidEnd.value;
			control.bid.manager = restaurantService.getRestaurantManager();
			
			if(control.bid.beginning < control.bid.end)
				if(control.bid.drinks.length !== 0 || control.bid.groceries.length !== 0)
					$http.post('/restmanager/addBid', control.bid).then(function success(response){
						control.bid = {};
						control.bid.drinks = [];
						control.bid.groceries = [];
						toastr["success"]("Bid successfully added!");
					}), function error(response){
						control.result = "Unknown error ocurred.";
					}
				else{
					toastr["warning"]('Grocery or drink list is empty.');
				}
			else
				toastr["warning"]('End date needs to be after begin date.');
		}
		
		$scope.$watch('tab.tab', function(newValue) {
			if(newValue == 5){
				control.getAllDrinks();
				control.getAllGroceries();
			}
		});
	}]);
	
	app.controller('OffersController', ['$scope', '$http', '$window', 'restaurantService', function($scope, $http, $window, restaurantService){
		var control = this;
		control.offers = [];
		control.seeOffer = {};
		control.toggleContent = true;
		
		this.isToggled = function(){
			return control.toggleContent;
		}
		
		this.loadOffers = function(){
			$http.post('/restmanager/getOffers', restaurantService.getRestaurant()).then(function success(response) {
				control.offers = response.data;
				
				for(it in control.offers){
					control.offers[it].delivery = new Date(control.offers[it].delivery);
					control.offers[it].warranty = new Date(control.offers[it].warranty);
					control.offers[it].lastsUntil = new Date(control.offers[it].lastsUntil);
				}
			}, function error(response) {
				control.result = "Unknown error ocurred."
			});
		}
		
		this.seeContent = function(offer){
			control.seeOffer = offer;
			control.toggleContent = false;
		}
		
		this.acceptOffer = function(offer){
			$http.post('/restmanager/acceptOffer', offer).then(function success(response) {
				if(response.data === "OK"){
					toastr["success"]('Offer accepted.');
				}else{
					toastr["error"]('Bid already has an offer.');
				}
			}, function error(response) {
				control.result = "Unknown error ocurred."
			});
		}
		
		this.goBack = function(){
			control.seeOffer = {};
			control.toggleContent = true;
		}
		
		$scope.$watch('tab.tab', function(newValue) {
			if(newValue == 6){
				control.loadOffers();
			}
		});
	}]);
	
	app.controller('SeatingController', ['$scope', '$http', '$window', 'restaurantService', function($scope, $http, $window, restaurantService) {
		var control = this;
		control.segment = {};
		control.segments = [];
		control.toggleArrange = false;
		control.result = "";
		control.selectedSegment = {};
		control.tables = {};
		
		this.isToggled = function(){
			return control.toggleArrange;
		}
		
		//Instanciranje matrice stolova, stolovi su inicajlno prazni(nema stolova)
		this.matrix = function(segment){
			var arr = [];
			for (var i = 0; i < segment.tableRows; ++i){
				var columns = [];
				for (var j = 0; j < segment.tableColumns; ++j){
					var table = {};
					table.status = "empty";
					table.segment = segment;
					table.tableCode = table.segment.label + i + j;
					table.tableRow = i;
					table.tableCol = j;
					table.tableClass = control.setTableClass(table);
					columns[j] = table;
				}
				arr[i] = columns;
			}
			return arr;
		}
		
		this.register = function(){
			control.segment.seating = restaurantService.getRestaurantSeating();
			$http.post('/restmanager/addSegment', this.segment).then(function success(response) {
				control.result = response.data;
				toastr["success"]('Segment successfully added.');
				if(control.result === "OK"){
					control.segments.push(control.segment);
					control.segment = {};
				}
			}, function error(response) {
				control.result = "Unknown error ocurred."
			});
		};
		
		this.getSegments = function(){
			$http.post('/restmanager/getSegments', restaurantService.getRestaurantSeating()).then(function success(response){
				control.segments = response.data;
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
		};
		
		$scope.$watch('tab.tab', function(newValue) {
			if(newValue == 7){
				control.getSegments();
			}
		});
		
		this.removeSegment = function(segment){
			$http.post('/restmanager/removeSegment', segment).then(function success(response){
				toastr["success"](response.data);
				var index = -1;		
				var segmentArr = eval( control.segments );
				for( var i = 0; i < segmentArr.length; i++ ) {
					if( segmentArr[i].label === segment.label ) {
						index = i;
						break;
					}
				}
				if( index === -1 ) {
					toastr["error"]( "Something gone wrong" );
				}
				control.segments.splice( index, 1 );
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
		}
		
		//Uzimaju se svi stolovi nekog segmenta
		this.arrangeTables = function(segment){
			control.selectedSegment = segment;
			control.tables = control.matrix(segment);
				
			$http.post('/restmanager/getTables', segment).then(function success(response){
				for(it in response.data){
					//Svi stolovi iz segmenta se postavljaju na svoje pozicije
					control.tables[response.data[it].tableRow][response.data[it].tableCol] = response.data[it];
					control.tables[response.data[it].tableRow][response.data[it].tableCol].tableClass = control.setTableClass(response.data[it]);
				}
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
			
			control.toggleArrange = true;
		}
		
		this.manageTable = function(table){
			//Klikom na sto switchuje se izmedju slobodnog stola i praznine
			//kad ti budes rezervisao sto bice crveni pa necu moci da ih diram
			if(table.status === "empty"){
				table.status = "free";
				table.tableClass = control.setTableClass(table);
				
				var restTable = {};
				restTable.tableCode = table.tableCode;
				restTable.status = table.status;
				restTable.segment = table.segment;
				restTable.tableRow = table.tableRow;
				restTable.tableCol = table.tableCol;
				
				$http.post('/restmanager/addTable', restTable).then(function success(response){
					
					toastr["success"](response.data);
				}), function error(response){
					control.result = "Unknown error ocurred.";
				}
			}else if(table.status === "free"){
				table.status = "empty";
				table.tableClass = control.setTableClass(table);
				
				var restTable = {};
				restTable.tableCode = table.tableCode;
				restTable.status = table.status;
				restTable.segment = table.segment;
				restTable.tableRow = table.tableRow;
				restTable.tableCol = table.tableCol;
				
				$http.post('/restmanager/removeTable', restTable).then(function success(response){
					toastr["success"](response.data);
				}), function error(response){
					control.result = "Unknown error ocurred.";
				}
			}
		}
		
		//ovom metodom postavljas klasu buttona koji prikazuje sto
		this.setTableClass = function(table){
			if(table.status === "empty")
				return "btn btn-default";
			else if(table.status === "free")
				return "btn btn-success";
			else if(table.status === "taken")
				return "btn btn-danger";
		}
		
		this.goBack = function(){
			control.selectedSegment = {};
			control.tables = {};
			
			control.toggleArrange = false;
		}
	}]);
	
	app.controller("EmployeeController", ['$scope', '$http', '$window', 'restaurantService', function($scope, $http, $window, restaurantService){
		var control = this;
		control.employee = {};
		control.employees = [];
		control.roles = [{
			id : 0,
			label : 'Bartender'
		}, {
			id : 1,
			label : 'Waiter'
		}, {
			id : 2,
			label : 'Chef'
		}];
		control.result = "";
		
		this.register = function(){
			control.employee.restaurant = restaurantService.getRestaurant();
			
			$http.post('/restmanager/addEmployee', this.employee).then(function success(response) {
				control.result = response.data;
				toastr["success"]('Employee successfully registered.');
				if(control.result === "OK"){
					control.employees.push(control.employee);
					control.employee = {};
				}else if(control.result === "No such restaurant."){
					control.employee.restaurant.name = "";
				}
			}, function error(response) {
				control.result = "Unknown error ocurred.";
			});
			
		};
		
		this.getEmployees = function(){
			$http.post('/restmanager/getEmployees', restaurantService.getRestaurant()).then(function success(response){
				control.employees = response.data;
				
				for(it in control.employees){
					control.employees[it].dateBirth = new Date(control.employees[it].dateBirth);
				}
			}), function error(response){
				control.result = "Unknown error ocurred."
			}
		};

		$scope.$watch('tab.tab', function(newValue) {
			if(newValue == 8){
				control.getEmployees();
			}
		});
	}]);
	
	// ZA KALENDAR
	
	
	
	app.controller('KitchenSinkCtrl', function(moment, alertGagi, calendarConfig) {

	    var vm = this;

	    vm.calendarView = 'month';
	    vm.viewDate = new Date();
	    var actions = [{
	      label: '<i class=\'glyphicon glyphicon-pencil\'></i>',
	      onClick: function(args) {
	    	  alertGagi.show('Edited', args.calendarEvent);
	      }
	    }, {
	      label: '<i class=\'glyphicon glyphicon-remove\'></i>',
	      onClick: function(args) {
	    	  alertGagi.show('Deleted', args.calendarEvent);
	      }
	    }];
	    vm.events = [
	      {
	        title: 'An event',
	        color: calendarConfig.colorTypes.warning,
	        startsAt: moment().startOf('week').subtract(2, 'days').add(8, 'hours').toDate(),
	        endsAt: moment().startOf('week').add(1, 'week').add(9, 'hours').toDate(),
	        draggable: true,
	        resizable: true,
	        actions: actions,
	        alreadyAdded: false
	      }
	    ];

	    vm.cellIsOpen = true;

	    vm.addEvent = function() {
	      vm.events.push({
	        title: 'New event',
	        startsAt: moment().startOf('day').toDate(),
	        endsAt: moment().endOf('day').toDate(),
	        color: calendarConfig.colorTypes.important,
	        draggable: true,
	        resizable: true,
	        alreadyAdded: false
	      });
	    };

	    vm.eventClicked = function(event) {
	    	alertGagi.show('Clicked', event);
	    };

	    vm.eventEdited = function(event) {
	    	alertGagi.show('Edited', event);
	    };

	    vm.eventDeleted = function(event) {
	    	alertGagi.show('Deleted', event);
	    };

	    vm.eventTimesChanged = function(event) {
	    	alertGagi.show('Dropped or resized', event);
	    };

	    vm.toggle = function($event, field, event) {
	      $event.preventDefault();
	      $event.stopPropagation();
	      event[field] = !event[field];
	    };

	    vm.timespanClicked = function(date, cell) {

	      if (vm.calendarView === 'month') {
	        if ((vm.cellIsOpen && moment(date).startOf('day').isSame(moment(vm.viewDate).startOf('day'))) || cell.events.length === 0 || !cell.inMonth) {
	          vm.cellIsOpen = false;
	        } else {
	          vm.cellIsOpen = true;
	          vm.viewDate = date;
	        }
	      } else if (vm.calendarView === 'year') {
	        if ((vm.cellIsOpen && moment(date).startOf('month').isSame(moment(vm.viewDate).startOf('month'))) || cell.events.length === 0) {
	          vm.cellIsOpen = false;
	        } else {
	          vm.cellIsOpen = true;
	          vm.viewDate = date;
	        }
	      }

	    };
	});
	
	app.controller("ShiftController", ['$scope', '$http', 'restaurantService', function($scope, $http, restaurantService){
		var control = this;
		control.shift = {};
		$scope.arbeit = {};
		$scope.parameter = {};
		control.employees = [];
		control.segments = [];
		$scope.mapa = [];
		
		//----novi deo
		control.shifts = [];
		control.employee = {};
		control.segment = {};
		control.shiftBegins = new Date();
		control.shiftEnds = new Date();
		control.event = {
			id: 0,
			title: 'New shift.',
	        startsAt: new Date(),
	        endsAt: new Date(),
	        draggable: true,
	        resizable: true,
		};
		control.showSegment = false;
		control.newShift = false;
		//---novi deo
		
		control.events = [];
		
		this.checkIfWaiter = function(employee){
			if(employee.role === "WAITER")
				control.showSegment = true;
			else{
				control.showSegment = false;
			}
		}
		
		this.isWaiter = function(){
			return control.showSegment;
		}
		
		this.isNewShift = function(){
			return control.newShift;
		}
		
		this.register = function(){
				var shift = {};
				shift.employee = control.employee;
				shift.segment = control.segment;
				shift.schedule = restaurantService.getRestaurantSchedule();
				shift.shiftBegins = control.event.startsAt;
				shift.shiftEnds = control.event.endsAt;
				
				$http.post('/restmanager/addShift', shift).then(function success(response){
					if(response.data !== null){
						toastr["success"]('Shift successfully added!');
						control.event.id = response.data.idShift;
						control.event.title = control.employee.email;
						control.shifts.push(response.data);
						control.events.push(control.event);
						
						control.employee = {};
						control.segment = {};
						control.shiftBegins = new Date();
						control.shiftEnds = new Date();
						control.event = {
							id: 0,
							title: 'New shift.',
					        startsAt: new Date(),
					        endsAt: new Date(),
					        draggable: true,
					        resizable: true,
						};
						control.showSegment = false;
						control.newShift = false;
					}else{
						toastr["error"]('Error');
					}
					
				}), function error(response){
					control.result = "Unknown error ocurred.";
				}
		}
		
		this.cancelShift = function(){
			control.employee = {};
			control.segment = {};
			control.shiftBegins = new Date();
			control.shiftEnds = new Date();
			control.event = {
				id: 0,
				title: 'New shift.',
		        startsAt: new Date(),
		        endsAt: new Date(),
		        draggable: true,
		        resizable: true,
			};
			control.showSegment = false;
			control.newShift = false;
		}
		
		control.addEvent = function() {
			control.newShift = true;
		};
		
		$scope.fillArbeit = function() {
			
			$http({
				method: 'POST',
				url: '/employee/getRole',
				headers: {
					   'Content-Type': 'text/plain'
					 },
					 data: $scope.parameter
			}).then(function success(response) {
				if (response.data != null) {
					control.employees = [];
					$scope.arbeit = response.data
					 angular.forEach($scope.arbeit, function(value, key){
						 control.employees.push(value);
					   });

					
				}
			});
		}
		
		$scope.$watch('tab.tab', function(newValue) {
				
				if(newValue !== 9)
					return;
			
				$http.post('/restmanager/getSegments', restaurantService.getRestaurantSeating()).then(function success(response){
					control.segments = response.data;
				}), function error(response){
					control.result = "Unknown error ocurred.";
				}
				
				$http.post('/restmanager/getShifts', restaurantService.getRestaurantSchedule()).then(function success(response){
					control.shifts = response.data;
					for(it in response.data){
						
						event = {
						        id: response.data[it].idShift,
								title: response.data[it].employee.email,
						        startsAt: new Date(response.data[it].shiftBegins),
						        endsAt: new Date(response.data[it].shiftEnds),
						        draggable: true,
						        resizable: true,
						      }
						
						control.events.push(event);
					}
				}), function error(response){
					control.result = "Unknown error ocurred.";
				}
		});
		
		$scope.changedValue = function(item) {
		  $scope.parameter=item.name;
		  $scope.fillArbeit();
		}  
		
		$scope.fillArbeit();
		
		this.removeShift = function(event){
			
			var shift = {};
			shift.idShift = event.id;
			
			$http.post('/restmanager/removeShift', shift).then(function success(response){
				toastr["success"](response.data);
				
				var index = -1;		
				var shiftsArr = eval( control.shifts );
				for( var i = 0; i < shiftsArr.length; i++ ) {
					if( shiftsArr[i].idShift === event.id ) {
						index = i;
						break;
					}
				}
				if( index === -1 ) {
					toastr["error"]( "Something gone wrong" );
				}else{
					control.shifts.splice( index, 1 );
					control.events.splice(index, 1);
				}
				
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
		}
		
	}]);
	
	
	
})();