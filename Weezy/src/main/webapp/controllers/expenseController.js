//This controller retrieves data from the expenseService and associates it with the $scope
//The $scope is ultimately bound to the expenses view
app.controller('ExpenseController', function($scope, expenseService) {

	init();

	function init() {
		expenseService.getAllExpenses().then(function(dataResponse) {
			$scope.expenses = dataResponse.data;
		});
		expenseService.getAllFrequencies().then(function(dataResponse) {
			$scope.expenseFrequencies = dataResponse.data;
		});
	}

	$scope.insertExpense = function() {
		var name = $scope.newExpense.name;
		var amount = $scope.newExpense.amount;
		var frequency = $scope.newExpense.frequency;
		expenseService.insertExpense(name, amount, frequency).then(function(responsedata) {
		    init();
		});
		$scope.newExpense.name = '';
		$scope.newExpense.amount = '';
		$scope.newExpense.frequency = '';
	};

	$scope.deleteExpense = function(id) {
		expenseService.deleteExpense(id).then(function(responsedata) {
		    init();
		});
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
