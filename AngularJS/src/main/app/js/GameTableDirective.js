/**
 * Created by silvia on 09/05/15.
 */

angular.module('steamDataApp').directive('gameTable', function () {
    return {
        restrict: 'A',
        scope: {
            games: '=',
            other: '=',
            searchText: '=',
            isWatchlist: '=',
            add : '&',
            remove : '&',
            show : '&',
            getCsv : '&'
        },
        templateUrl: '../views/game_table.html'
        //restrict: 'A',
        //scope: {
        //    //@ reads the attribute value, = provides two-way binding, & works with functions
        //    games: '=',
        //    searchText: '=',
        //    isWatchlist: '=',
        //    test : '&'
        //},
        //templateUrl: '../views/game_table.html'
        ////controller: controllerFunction, //Embed a custom controller in the directive
        ////link: function ($scope, element, attrs) { } //DOM manipulation
    }
});