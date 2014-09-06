//This handles retrieving data and is used by controllers. 3 options (server, factory, provider) with 
//each doing the same thing just structuring the functions/data differently.
app.service('expenseService', function($http) {

	this.getAllExpenses = function() {
		return $http.get('/expenses');
	}

	this.getAllFrequencies = function() {
		return $http.get('/expenses/frequencyEnums');
	}
	
	this.insertExpense = function(name, amount, frequency) {
		var data = {
			"name" : name,
			"amount" : amount,
			"from" : "2014-08-07",
			"to" : "2014-08-10",
			"frequency" : frequency
		};
		$http.post('/expenses', data);
	};
	
	this.deleteExpense = function(id) {
		$http.delete('/expenses/' + id);
	}

});