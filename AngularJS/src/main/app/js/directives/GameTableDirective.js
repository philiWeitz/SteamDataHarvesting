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
            harvest : '&',
            getCsv : '&'
        },
        templateUrl: '../views/game_table.html'
    }
});