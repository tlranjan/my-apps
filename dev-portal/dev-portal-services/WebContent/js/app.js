var app = angular.module('devportal', ['ngRoute','xeditable']);
app.run(function(editableOptions){editableOptions.theme='bs3';});
app.controller('devportal', function($scope, $filter, $http) {
	$scope.getusermenu = function(){$http({
		method: 'GET',
		url: 'getusermenu',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){$scope.getusermenu=response.data;});};
	$scope.getusermenu();
});
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "templates/home.html"
    })
    .when("/manage_all_orgs", {
        templateUrl : "templates/manage_all_orgs.html"
    })
    .when("/manage_all_users", {
        templateUrl : "templates/manage_all_users.html"
    })
    .when("/manage_all_endpoints", {
        templateUrl : "templates/manage_all_endpoints.html"
    })
    .when("/manage_all_catalogs", {
        templateUrl : "templates/manage_all_catalogs.html"
    })
    .when("/all_requests", {
        templateUrl : "templates/all_requests.html"
    })
    .when("/manage_all_machines", {
        templateUrl : "templates/manage_all_machines.html"
    })
    .when("/manage_myorgs", {
        templateUrl : "templates/manage_myorgs.html"
    })
    .when("/manage_myorgs_user", {
        templateUrl : "templates/manage_myorgs_user.html"
    })
    .when("/manage_myorgs_endpoint", {
        templateUrl : "templates/manage_myorgs_endpoint.html"
    })
    .when("/manage_myorg_catalogs", {
        templateUrl : "templates/manage_myorg_catalogs.html"
    })
    .when("/my_orgs_request", {
        templateUrl : "templates/my_orgs_request.html"
    })
    .when("/manage_myorgs_catalog", {
        templateUrl : "templates/manage_myorgs_catalog.html"
    })
    .when("/my_org_requests", {
        templateUrl : "templates/my_org_requests.html"
    })
    .when("/manage_myorgs_machine", {
        templateUrl : "templates/manage_myorgs_machine.html"
    })
    .when("/published_orgs_catalogs", {
        templateUrl : "templates/published_orgs_catalogs.html"
    })
    .when("/my_requests", {
        templateUrl : "templates/my_requests.html"
    })
    .when("/manage_my_machines", {
        templateUrl : "templates/manage_my_machines.html"
    });
});
