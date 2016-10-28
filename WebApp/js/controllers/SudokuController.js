app.controller('SudokuController',  ['$scope', '$location', '$routeParams',
	function($scope, $location, $routeParams){
		$scope.changeView=function(view){
			$location.path(view);
		}

	}

]);