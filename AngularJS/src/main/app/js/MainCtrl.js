/**
 * Created by silvia on 06/05/15.
 */

angular.module('steamApp').controller('TemplateCtrl', function ($scope) {

    $scope.searchText = '';

    $scope.games = [
     {appId: '1', name: 'Game 1', getsUpdated: true},
     {appId: '2', name: 'Game 2', getsUpdated: false},
     {appId: '3', name: 'Game 3', getsUpdated: false}
     ];

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
    //var updateGetsUpdated = function(appId, value){
    //
    //    var gameIndex = _.findIndex($scope.games, function(game){
    //        return game.appId === appId;
    //    });
    //
    //    $scope.games[gameIndex].getsUpdated = value;
    //};

});