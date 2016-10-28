var app = angular.module('SudokuApp', ['ngRoute']);

app.config (function ($routeProvider){
	$routeProvider
  	.when('/',{
  	 	controller: 'SudokuController',
    	templateUrl: 'views/sudoku.html'
  	})
  	.when('/solved',{
  	 	controller: 'SudokuController',
    	templateUrl: 'views/solved.html'
  	})
  	.otherwise({
  		redirectTo:'/'
  	});

});