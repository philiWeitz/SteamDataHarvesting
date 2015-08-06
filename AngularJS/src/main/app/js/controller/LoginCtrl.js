/**
 * Created by silvia on 06/05/15.
 */

angular.module('steamDataApp').controller('LoginCtrl', function ($scope, _, SteamDataService, LocationService) {

	var init = function() {

	}
	
    $scope.login = function() {
    	SteamDataService.login($scope.username, $scope.password, function() {
        	LocationService.redirectToIndexPage();
    	}, function() {
    		$scope.loginError = true;
    	});
    };

	init();
	
});
