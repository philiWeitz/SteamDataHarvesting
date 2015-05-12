/**
 * Created by silvia on 06/05/15.
 */

angular.module('steamDataApp').controller('MainCtrl', function ($scope, $http, _, $location, SteamDataService) {

    $scope.searchText = '';
    $scope.games = [];
    $scope.game = {};

    var init = function(){
        $scope.getGames();
    };

    $scope.getGames = function(){

        SteamDataService.getAllApps()
            .then(function(games){
                $scope.games = _.first(games, 200);
        });

    };

    $scope.getAppData = function(appId){
        SteamDataService.getAppData(appId)
            .then(function(game){
                $scope.game = game;
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
        $scope.getAppData(appId);
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

    //$scope.getGames = function(){
    //
    //    $http.get('/SteamDataHarvestingWebServices/service/app/getAllApps').
    //        success(function(data, status, headers, config) {
    //            console.log('success');
    //            console.log(data);
    //            $scope.games = _.first(data, 200);
    //
    //        }).
    //        error(function(data, status, headers, config) {
    //            console.log('error');
    //        });
    //};
    //
    //$scope.addToWatchlist = function(appId){
    //    $http.post('/SteamDataHarvestingWebServices/service/app/addToWatchList', appId).
    //        success(function(data, status, headers, config) {
    //            console.log('post success');
    //
    //            updateGetsUpdated(appId, true);
    //        }).
    //        error(function(data, status, headers, config) {
    //            console.log('post error');
    //        });
    //};
    //
    //$scope.removeFromWatchlist = function(appId){
    //    $http.post('/SteamDataHarvestingWebServices/service/app/removeFromWatchList', appId).
    //        success(function(data, status, headers, config) {
    //            console.log('post success');
    //
    //            updateGetsUpdated(appId, false);
    //        }).
    //        error(function(data, status, headers, config) {
    //            console.log('post error');
    //        });
    //};
    //

});