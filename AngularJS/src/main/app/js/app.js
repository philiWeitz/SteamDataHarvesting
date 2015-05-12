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

        .when('/datasets/:appId', {
            controller: 'MainCtrl',
            templateUrl: 'game_info.html'
        })
});

app.constant('Config', {
    serverUrl : 'localhost:8080'
});

app.constant('_', window._);