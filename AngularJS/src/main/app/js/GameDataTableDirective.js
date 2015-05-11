/**
 * Created by silvia on 10/05/15.
 */

angular.module('steamDataApp').directive('gameInfoTable', function () {
    return {
        restrict: 'A',
        scope: {
            //@ reads the attribute value, = provides two-way binding, & works with functions
            game: '=',
            add : '&',
            remove : '&'
        },
        templateUrl: '../views/game_info.html'
        //controller: controllerFunction, //Embed a custom controller in the directive
        //link: function ($scope, element, attrs) { } //DOM manipulation
    }
});