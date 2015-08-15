/**
 * Created by silvia on 06/05/15.
 */

var app = angular.module('steamDataApp', ['ngResource', 'ngRoute']);

app.config(function($routeProvider){	
    $routeProvider

        .when('/', {
            controller: 'MainCtrl',
            templateUrl: 'watchlist.html'
        })

        .when('/watchlist', {
            controller: 'MainCtrl',
            templateUrl: 'watchlist.html'
        })

        .when('/app/:appId/:appName', {
            controller: 'GameCtrl',
            templateUrl: 'game_info.html'
        })

        .when('/datagames', {
            controller: 'GameCtrl',
            templateUrl: 'datagames.html'
        })
        
        .when('/login', {
            controller: 'LoginCtrl',
            templateUrl: 'login.html'
        })
        
}).run(function($rootScope, SteamDataService, LocationService) {  
	// every route change
	$rootScope.$on('$locationChangeStart', function (event, newUrl, oldUrl) {
    	SteamDataService.getCurrentUser(function(user) {
    		// user is already logged in
    		$rootScope.authenticated = true;
    	}, function() {
    		// user is not logged in
    		$rootScope.authenticated = false;   		
    		LocationService.redirectToLoginPage();
    	});
    });

	$rootScope.logout = SteamDataService.logout;
});


app.constant('Config', {
    serverUrl : 'localhost:8080'
});

app.constant('_', window._);

app.filter('truncate', function () {
    return function (value, wordwise, max, tail) {
        if (!value) return '';

        max = parseInt(max, 10);
        if (!max) return value;
        if (value.length <= max) return value;

        value = value.substr(0, max);
        if (wordwise) {
            var lastspace = value.lastIndexOf(' ');
            if (lastspace != -1) {
                value = value.substr(0, lastspace);
            }
        }

        return value + (tail || ' …');
    };
});

app.directive('toggleClass', function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            element.bind('click', function() {
                element.toggleClass(attrs.toggleClass);
            });
        }
    };
});
