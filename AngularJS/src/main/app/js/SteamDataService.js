/**
 * Created by silvia on 06/05/15.
 */

angular.module('steamDataApp').service('SteamDataService', ['$http', function ($http) {

        var urlBase = '/SteamDataHarvestingWebServices/service/app/';

        this.getAllApps = function () {
            return $http.get(urlBase + 'getAllApps')
                .then(function(response){
                    return response.data;
                });
        };

        this.addToWatchlist = function(appId){
            return $http.post(urlBase + 'getApp/' + appId, appId)
                .then(function(response){
                    return response.data;
            });
        };

        this.removeFromWatchlist = function(appId){
            return $http.post(urlBase + 'getApp/' + appId, appId)
                .then(function(response){
                    return response.data;
            });
        };

}]);