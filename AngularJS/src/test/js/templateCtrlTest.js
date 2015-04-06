
describe("app", function () {
 
    beforeEach(module('app'));
 
    describe("TemplateCtrl", function () {
 
        var scope, httpBackend, http, controller;

        beforeEach(inject(function ($rootScope, $controller, $httpBackend, $http) {
            scope = $rootScope.$new();
            httpBackend = $httpBackend;
            http = $http;
            controller = $controller;
        }));
     
        
        it('Template Test', function () {

            controller('TemplateCtrl', {
                $scope: scope,
                $http: http
            });

            expect(scope.hello_world).toBe("Hello World");
        });
    });
});
