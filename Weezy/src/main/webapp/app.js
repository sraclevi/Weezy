var app = angular.module('weezyApp', [ 'ngRoute' ]);

//This configures the routes and associates each route with a view and a controller
app.config(function($routeProvider) {
	$routeProvider.when('/expenses', {
		controller : 'ExpenseController',
		templateUrl : '/partials/expenses.html'
	}).when('/incomes', {
		controller : 'IncomeController',
		templateUrl : '/partials/incomes.html'
	}).otherwise({
		redirectTo : '/expenses'
	});
});
