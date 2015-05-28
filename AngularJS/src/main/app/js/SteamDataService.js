/**
 * Created by silvia on 06/05/15.
 */

angular.module('steamDataApp').service('SteamDataService', ['$http', '$window', function ($http, $window) {

    var urlBase = '/SteamDataHarvestingWebServices/service/';
        appUrlBase = urlBase + 'app/';
        versionUrlBase = urlBase + 'version/';
        reviewUrlBase = urlBase + 'review/'

    //App services
    this.getAllApps = function () {
        return $http.get(appUrlBase + 'getAllApps')
            .then(function(response){
                
                return response.data;
            });
    };

    this.getApps = function (max, searchTerm) {
        return $http.get(appUrlBase + 'getAllAppsAndUpdateList' +
        '?max=' + max +
        '&searchTerm=' + searchTerm)
            .then(function(response){
                
                return response.data;
            });
    };

    this.getAppsWhichHaveData = function () {
        return $http.get(appUrlBase + 'getAppsWhichHaveData')
            .then(function(response){
                
                return response.data;
            });
    };

    this.getAppDlcs = function (appId) {
        return $http.get(appUrlBase + 'getAppDlcs/' + appId)
            .then(function(response){
                
                return response.data;
            });
    };

    this.addToWatchlist = function(appId){
        console.log(appId);
        return $http.post(appUrlBase + 'addToWatchList' , appId)
            .then(function(response){
                
                return response.data;
            });
    };

    this.removeFromWatchlist = function(appId){
        return $http.post(appUrlBase + 'removeFromWatchList', appId)
            .then(function(response){
                
                return response.data;
            });
    };

    this.getCsvFile = function(appId){
        $window.open(appUrlBase + 'getCSVFile/' + appId, '_parent', '');
    };

    //Versions services

    this.getAppVersions = function(appId){
        return $http.get(versionUrlBase + 'getVersionsByAppId/' + appId)
            .then(function(response){
                
                return response.data;
            });
    };

    //Reviews services

    this.getReviewsByAppIdAndVersion = function(appId, versionId){
        return $http.get(reviewUrlBase + 'getReviewsByAppIdAndVersion/' + appId + '/' + versionId)
            .then(function(response){
                
                return response.data;
            });
    };

    this.getReviewsByDlcId = function(dlcId){
        return $http.get(reviewUrlBase + 'getReviewsByDlcId/' + dlcId)
            .then(function(response){
                
                return response.data;
            });
    };

    this.getReviewsWithoutVersionByAppId = function(appId){
        return $http.get(reviewUrlBase + 'getReviewsWithoutVersionByAppId/' + appId)
            .then(function(response){
                console.log(response.data);
                return response.data;
            });
    };





}]);