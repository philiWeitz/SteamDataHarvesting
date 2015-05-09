
describe("steamDataApp", function () {

    beforeEach(module('steamDataApp'));

    describe("MainCtrl", function () {

        var scope, httpBackend, http, controller;

        beforeEach(inject(function ($rootScope, $controller, $httpBackend, $http) {
            scope = $rootScope.$new();
            httpBackend = $httpBackend;
            http = $http;
            controller = $controller;
        }));


        it('Template Test', function () {

            controller('MainCtrl', {
                $scope: scope
            });

        });
    });
});
