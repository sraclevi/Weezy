//This handles retrieving data and is used by controllers. 3 options (server, factory, provider) with 
//each doing the same thing just structuring the functions/data differently.
app.service('incomeService', function($http) {

	this.getAllIncomes = function() {
		return $http.get('/incomes');
	}

	this.insertIncome = function(name, amount, frequency) {
		var data = {
			"name" : name,
			"amount" : amount,
			"from" : "2014-08-07",
			"to" : "2014-08-10",
			"frequency" : frequency
		};
		$http.post('/incomes', data);
	};

});