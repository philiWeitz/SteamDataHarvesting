/**
 * Created by silvia on 06/05/15.
 */

angular.module('steamDataApp').controller('MainCtrl', function ($scope, _, $location, SteamDataService) {

    $scope.searchText = '';
    $scope.games = [];

    var init = function(){
        $scope.getGames();
    };

    $scope.getGames = function(){

        SteamDataService.getApps(20, $scope.searchText)
            .then(function(games){
                $scope.games = games;
        });

    };

    $scope.addToWatchlist = function(appId){
        console.log('adding');
        SteamDataService.addToWatchlist(appId)
            .then(function(games){
                updateGetsUpdated(appId, true);
            });
    };

    $scope.removeFromWatchlist = function(appId){
        console.log('removing');
        SteamDataService.removeFromWatchlist(appId)
            .then(function(games){
                updateGetsUpdated(appId, false);
            });
    };

    $scope.showGameData = function(appId){
        //$scope.getAppData(appId);
        console.log('redirecting '+ appId);
        $location.path('datasets/' + appId);
    };

    var updateGetsUpdated = function(appId, value){

        var gameIndex = _.findIndex($scope.games, function(game){
            return game.appId === appId;
        });

        $scope.games[gameIndex].getsUpdated = value;
    };

    init();

});

angular.module('steamDataApp').controller('GameCtrl', function ($scope, $location, $routeParams, _, SteamDataService) {
	
	$scope.game = {};
	$scope.appId = $routeParams.appId;

	$scope.getAppData = function(appId){
        SteamDataService.getAppData(appId)
            .then(function(game){
                $scope.game = game;
            });
    }($scope.appId);
	
	
});