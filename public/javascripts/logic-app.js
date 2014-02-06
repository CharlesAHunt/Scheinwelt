var app = angular.module('log-ic', []);

app.factory('logSearch', ['$http', function($http) {

    return {
        fetch: function($scope, text){
            $http.get('http://localhost:9000/search/' + 'itemid')
                .success(function (data) {

                    $scope.logData = data;

                })
                .error(function (data, status, headers, config) {

                });
        }
    }

}]);

function LogicController($scope, logSearch) {

        $scope.search = false;
        $scope.logData = null;

        $scope.doSearch = function() {
            logSearch.fetch($scope,"go");
        };
}