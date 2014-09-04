//This controller retrieves data from the incomeService and associates it with the $scope
//The $scope is ultimately bound to the incomes view
app.controller('IncomeController', function($scope, incomeService) {

	init();

	function init() {
		incomeService.getAllIncomes().then(function(dataResponse) {
			$scope.incomes = dataResponse.data;
		});
		;
	}

	$scope.insertIncome = function() {
		var name = $scope.newIncome.name;
		var amount = $scope.newIncome.amount;
		var frequency = $scope.newIncome.frequency;
		incomeService.insertIncome(name, amount, frequency);
		$scope.newIncome.name = '';
		$scope.newIncome.amount = '';
		$scope.newIncome.frequency = '';
		init();
	};

	$scope.deleteIncome = function(id) {
		incomeService.deleteIncome(id);
		init();
	};
});

app.controller('NavbarController', function($scope, $location) {
	$scope.getClass = function(path) {
		if ($location.path().substr(0, path.length) == path) {
			return true
		} else {
			return false;
		}
	}
});
