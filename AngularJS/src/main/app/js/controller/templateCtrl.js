var app = angular.module('app', []);

app.constant('Config', {
	serverUrl : 'localhost:8080'
});

app.controller('TemplateCtrl', function ($scope, $http, Config) {

    $scope.searchText = '';
    $scope.watchlist = [];

	/*$scope.games = [
        {appId: '1', name: 'Game 1', getsUpdated: true},
        {appId: '2', name: 'Game 2', getsUpdated: false},
        {appId: '3', name: 'Game 3', getsUpdated: false}
    ];*/

    $scope.getGames = function(){

        $http.get('/SteamDataHarvestingWebServices/service/app/getAllApps').
            success(function(data, status, headers, config) {
                console.log('success');
                console.log(data);
                $scope.games = data;

            }).
            error(function(data, status, headers, config) {
                console.log('error');
            });
    };

    var setWatchlist = function(){

        console.log('posting');
        console.log($scope.watchlist);

        $http.post('/setWatchList', {watchlist: $scope.watchlist}).
            success(function(data, status, headers, config) {
                console.log('post success');
            }).
            error(function(data, status, headers, config) {
                console.log('post error');
            });
    };

    $scope.addToWatchlist = function(appId){
        $scope.watchlist.push(appId);
        setWatchlist();
    };

    $scope.removeFromWatchlist = function(appId){
        $scope.watchlist = _.without($scope.watchlist, appId);
        setWatchlist();
    };

    $scope.getGames();

});