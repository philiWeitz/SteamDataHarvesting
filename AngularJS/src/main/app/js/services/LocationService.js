/**
 * Created by silvia on 06/05/15.
 */

angular.module('steamDataApp').service('LocationService', ['$location', function ($location) {

    var indexUrl = '/'
      , appUrl = '/app/'
      , loginUrl = '/login';


    this.redirectToIndexPage = function () {
    	console.log('redirecting to index page');
        return $location.path(indexUrl);
    };    
    
    this.redirectToAppDataPage = function (appId, appName) {
    	console.log('redirecting to datasets with appId: ' + appId + ' and appName: ' + appName);
        return $location.path(appUrl + appId + '/'+ appName);
    };    

    this.redirectToLoginPage = function () {
    	console.log('redirecting to login page');
        return $location.path(loginUrl);
    };    
    
}]);