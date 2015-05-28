/**
 * Created by silvia on 06/05/15.
 */

angular.module('steamDataApp').controller('MainCtrl', function ($scope, _, $location, SteamDataService) {

    $scope.searchText = '';
    $scope.games = [];

    var init = function(){
        $scope.getGames();
    };

    $scope.getGames = function(){

        SteamDataService.getApps(20, $scope.searchText)
            .then(function(games){
                $scope.games = games;
        });
    };

    $scope.addToWatchlist = function(appId){
        console.log('adding');
        SteamDataService.addToWatchlist(appId)
            .then(function(games){
                updateGetsUpdated(appId, true);
            });
    };

    $scope.removeFromWatchlist = function(appId){
        console.log('removing');
        SteamDataService.removeFromWatchlist(appId)
            .then(function(games){
                updateGetsUpdated(appId, false);
            });
    };

    $scope.showGameData = function(appId){
        //$scope.getAppData(appId);
        console.log('redirecting '+ appId);
        $location.path('datasets/' + appId);
    };

    $scope.showAppVersions = function(appId, appName){
        //$scope.getAppData(appId);
        console.log('redirecting '+ appId);
        $location.path('app/' + appId + '/'+ appName);
    };

    var updateGetsUpdated = function(appId, value){

        var gameIndex = _.findIndex($scope.games, function(game){
            return game.appId === appId;
        });

        $scope.games[gameIndex].getsUpdated = value;
    };

    init();

});

angular.module('steamDataApp').controller('GameCtrl', function ($scope, $location, $routeParams, _, SteamDataService) {

    $scope.gamesWithData = [];
    $scope.searchText= '';
	
	$scope.gameVersions = [];
    $scope.gameDlcs = [];
    $scope.unversionedReviews = [];
	$scope.appId = $routeParams.appId;
    $scope.appName = $routeParams.appName;

    var init = function(){

        if(!$scope.appId)
            $scope.getAppsWhichHaveData();
        else{
            $scope.getAppVersions($scope.appId);
            $scope.getAppDlcs($scope.appId);
        }

    };

    $scope.getAppsWhichHaveData = function(){

        SteamDataService.getAppsWhichHaveData()
            .then(function(games){
                $scope.gamesWithData = games;
            });
    };

    $scope.showAppVersions = function(appId, appName){
        //$scope.getAppData(appId);
        console.log('redirecting '+ appId);
        $location.path('app/' + appId + '/'+ appName);
    };

    $scope.getAppDlcs = function(appId){

        SteamDataService.getAppDlcs(appId)
            .then(function(gameDlcs){
                $scope.gameDlcs = _.map(gameDlcs, function(dlc){
                    return {dlcInfo: dlc, reviews : []};
                });

        });
    };

    $scope.getAppVersions = function(appId){

        SteamDataService.getAppVersions(appId)
            .then(function(gameVersions){
                $scope.gameVersions = _.map(gameVersions, function(version){
                    return {versionInfo: version, reviews : []};
                });
            });
    };

    $scope.getVersionReviews = function(versionId){

        if(!hasReviews(true, versionId)){
            SteamDataService.getReviewsByAppIdAndVersion($scope.appId, versionId)
                .then(function(reviews){


                    $scope.gameVersions = _.each($scope.gameVersions, function(version){
                        if(version.versionInfo.published === versionId)
                            version.reviews = reviews;
                        return version;
                    });

            });
        }
    };

    $scope.getDlcReviews = function(dlcId){

        if(!hasReviews(false, dlcId)){
            SteamDataService.getReviewsByDlcId(dlcId)
                .then(function(reviews){

                    $scope.gameDlcs = _.each($scope.gameDlcs, function(dlc){
                        if(dlc.dlcInfo.dlcId === dlcId)
                            dlc.reviews = reviews;
                        return dlc;
                    });

                });
        }
    };

    $scope.getUnversionedReviews = function(){

        if(!$scope.unversionedReviews.length){
            SteamDataService.getReviewsWithoutVersionByAppId($scope.appId)
                .then(function(reviews){
                    $scope.unversionedReviews = reviews;
                });
        }
    };

    $scope.getCsvFile = function(appId){
        SteamDataService.getCsvFile(appId);
    };

    init();

    var hasReviews = function(isVersion, id){

        if(isVersion){
            var version = _.find($scope.gameVersions, function(version){
                return version.versionInfo.published == id;
            });

            if(version.reviews.length)
                return true;

            return false;
        }

        else{
            var dlc = _.find($scope.gameDlcs, function(dlc){
                return dlc.dlcInfo.dlcId == id;
            });

            if(dlc.reviews.length)
                return true;

            return false;
        }


    };

    //$scope.getAppData = function(appId){
    //    SteamDataService.getAppData(appId)
    //        .then(function(game){
    //            $scope.game = game;
    //        });
    //}($scope.appId);

});
