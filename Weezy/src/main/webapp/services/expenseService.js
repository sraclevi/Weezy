//This handles retrieving data and is used by controllers. 3 options (server, factory, provider) with 
//each doing the same thing just structuring the functions/data differently.
app.service('expenseService', function($http) {

	this.getAllExpenses = function() {
		return $http.get('/expenses');
	}

	this.getAllFrequencies = function() {
		return $http.get('/expenses/frequencyEnums');
	}
	
	this.getAllExpenseMonths = function() {
		return $http.get('/expenses/expenseMonths');
	}
	
	this.insertExpense = function(name, amount, frequency, from, to) {
		var data = {
			"name" : name,
			"amount" : amount,
			"from" : from,
			"to" : to,
			"frequency" : frequency
		};
		return $http.post('/expenses', data);
	};
	
	this.deleteExpense = function(id) {
		return $http.delete('/expenses/' + id);
	}
	
	this.getMonthlyAmount = function(month) {
		return $http.get('/expenses/amountForMonth/' + month);
	}

});