var app = angular.module('log-ic', []);

app.factory('logSearch', ['$http', function($http) {

    return {
        fetch: function($scope, text){
            $http.get('search/' + text)
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

        var app = document.getElementById('app').value;
        var env = document.getElementById('env').value;
        var region = document.getElementById('region').value;
        var level = document.getElementById('level').value;
        var exception = document.getElementById('exception').value;
        var message = document.getElementById('message').value;
        var beforeDate = document.getElementById('beforeDate').value;
        var afterDate = document.getElementById('afterDate').value;

        logSearch.fetch($scope, app+' ::: '+env+' ::: '+region+' ::: '+level+' ::: '+exception+' ::: '+message+' ::: '+beforeDate+' ::: '+afterDate + " ::: ");
    };
}