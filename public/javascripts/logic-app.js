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
        if(!app){
            app = '#';
        }
        var env = document.getElementById('env').value;
        if(!env){
            env = '#';
        }
        var region = document.getElementById('region').value;
        if(!region){
            region = '#';
        }
        var level = document.getElementById('level').value;
        if(!level){
            level = '#';
        }
        var exception = document.getElementById('exception').value;
        if(!exception){
            exception = '#';
        }
        var message = document.getElementById('message').value;
        if(!message){
            message = '#';
        }
        var beforeDate = document.getElementById('beforeDate').value;
        if(!beforeDate){
            beforeDate = '#';
        }
        var afterDate = document.getElementById('afterDate').value;
        if(!afterDate){
            afterDate = '#';
        }

        logSearch.fetch($scope, app+'/'+env+'/'+region+'/'+level+'/'+exception+'/'+message+'/'+beforeDate+'/'+afterDate);
    };
}