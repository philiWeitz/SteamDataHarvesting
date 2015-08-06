/**
 * Created by silvia on 06/05/15.
 */

angular.module('steamDataApp').controller('MainCtrl', function ($scope, _, SteamDataService, LocationService) {
	
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

    $scope.harvestDataForApp = function(appId, appName){

        SteamDataService.harvestDataForApp(appId)
            .then(function(data){
                console.log('show data');
                $scope.showAppVersions(appId, appName);
            });

    };

    $scope.showAppVersions = function(appId, appName){
    	LocationService.redirectToAppDataPage(appId, appName);
    };

    var updateGetsUpdated = function(appId, value){

        var gameIndex = _.findIndex($scope.games, function(game){
            return game.appId === appId;
        });

        $scope.games[gameIndex].getsUpdated = value;
    };

    init();

});
