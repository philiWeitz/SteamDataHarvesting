/**
 * Created by silvia on 06/05/15.
 */

angular.module('steamDataApp').service('SteamDataService', ['$http', function ($http) {

        var urlBase = '/SteamDataHarvestingWebServices/service/app/';

        this.getAllApps = function () {
            return $http.get(urlBase + 'getAllApps')
                .then(function(response){
                    console.log(response.data);
                    return response.data;
                });
        };

        this.getDataSets = function () {
            return $http.get(urlBase + 'getApp/appId' )
                .then(function(response){
                    console.log(response.data);
                    return response.data;
                });
        };

        this.addToWatchlist = function(appId){
            return $http.post(urlBase + 'addToWatchList' , appId)
                .then(function(response){
                    console.log(response.data);
                    return response.data;
            });
        };

        this.removeFromWatchlist = function(appId){
            return $http.post(urlBase + 'removeFromWatchList', appId)
                .then(function(response){
                    console.log(response.data);
                    return response.data;
            });
        };

}]);