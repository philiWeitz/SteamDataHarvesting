
describe("steamDataApp", function () {

    beforeEach(module('steamDataApp'));

    describe("MainCtrl", function () {

        var scope, httpBackend, http, controller;

        beforeEach(inject(function ($rootScope, $controller, $httpBackend, $http) {
            scope = $rootScope.$new();
            httpBackend = $httpBackend;

            httpBackend.when("GET", "/SteamDataHarvestingWebServices/service/app/getAllApps").respond([
                {appId: '1', name: 'Game 1', getsUpdated: true},
                {appId: '2', name: 'Game 2', getsUpdated: false},
                {appId: '3', name: 'Game 3', getsUpdated: false}
            ]);

            http = $http;
            controller = $controller;

            controller('MainCtrl', {
                $scope: scope,
                $http: http
            });

        }));

        it("empty", function(){
            expect(true).toBe(true);
        });

        //it("should have 3 games", function () {
        //    SteamDataService.getAllApps();
        //    httpBackend.flush();
        //    expect(scope.games.length).toBe(3);
        //});

    });

});