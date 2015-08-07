/**
 * Created by silvia on 06/05/15.
 */

angular.module('steamDataApp').service('SteamDataService', ['$http', '$window', function ($http, $window) {

    var pureAppBase = '/SteamDataHarvestingWebServices/'
      , urlBase = pureAppBase + 'service/'
      , appUrlBase = urlBase + 'app/'
      , versionUrlBase = urlBase + 'version/'
      , reviewUrlBase = urlBase + 'review/'
      , userUrlBase = urlBase + 'user/';

    
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

    this.getAppData = function (appId) {
        return $http.get(appUrlBase + 'getAppData/' + appId)
            .then(function(response){
                return response.data;
            });
    };

    this.harvestDataForApp = function (appId) {

        return $http.get(appUrlBase + 'harvestDataForApp/' + appId)
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

    this.login = function(username, password, succ, err) {	
        $http({
            method: "post",
            url: pureAppBase + 'login',
            data: $.param({'username': username, 'password': password}),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).error(function(data, status, headers, config) {
        	// the login page sends a page not available after login -> ugly fix
        	if(status === 404) {
        		succ();
        	} else {
        		err();
        	}
        });
    };

    this.logout = function() {    
        $http({
            method: "post",
            url: pureAppBase + 'logout',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
    };
    

    this.getCurrentUser = function(succ, err) {
        $http.get(userUrlBase + 'getCurrentUser')
        .success(function(data, status, headers, config) {
        	succ(data);
        }).error(function(data, status, headers, config) {
        	err();
        });
    };    
    
}]);