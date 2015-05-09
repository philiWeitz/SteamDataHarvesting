/**
 * Created by silvia on 06/05/15.
 */

angular.module('steamApp').factory('SteamApp', function($resource){

    return $resource('/SteamDataHarvestingWebServices/service/app/getAllApps');
});