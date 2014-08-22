function Hello($scope, $http) {
    $http.get('/expenses').
        success(function(data) {
            $scope.expenses = data;
        });
}