var app = angular.module('devportal', ['ngRoute','xeditable']);
app.run(function(editableOptions){editableOptions.theme='bs3';});
app.controller('AppController', function($scope, $filter, $http) {
	return{
		getusermenu : function(){$http({
			method: 'GET',
		    url: '/getusermenu',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getusermenu=response.data;});},
		getallorganization : function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getallorganization',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getallorganization=response.data;});},
		getallorganizationnames : function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getallorganizationnames',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getallorganizationnames=response.data;});},
		addorganization : function(organization){$http({
			method: 'POST',
		    url: '/org_admin/user/addorganization',
		    data: organization,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.addorganization=response.data;$scope.getallorganization.unshift($scope.addorganization);});},
		addmyorganization : function(organization){$http({
			method: 'POST',
		    url: '/org_admin/user/addorganization',
		    data: organization,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.addmyorganization=response.data;$scope.getmyorganizations.unshift($scope.addmyorganization);});},
		updateorganization : function(organization){$http({
			method: 'POST',
		    url: '/org_admin/user/updateorganization',
		    data: organization,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.updateorganization=response.data;});},
		deleteorganization : function(id){$http({
			method: 'DELETE',
		    url: '/org_admin/user/deleteorganization?id='+id,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.deleteorganization=response.data;});},
		getmyorganizations : function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getmyorganizations',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getmyorganizations=response.data;});},
		getmyorganizationnames : function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getmyorganizationnames',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getmyorganizationnames=response.data;});},
		getallawsendpoint : function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getallawsendpoint',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getallawsendpoint=response.data;});},
		getallawsendpointnames : function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getallawsendpointnames',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getallawsendpointnames=response.data;});},
		addawsendpoint : function(endpoint){$http({
			method: 'POST',
		    url: '/org_admin/user/addawsendpoint',
		    data: endpoint,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.addawsendpoint=response.data;$scope.getallawsendpoint.unshift($scope.addawsendpoint);});},
		updateawsendpoint : function(endpoint){$http({
			method: 'POST',
		    url: '/org_admin/user/updateawsendpoint',
		    data: endpoint,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.updateawsendpoint=response.data;});},
		deleteawsendpoint : function(id){$http({
			method: 'DELETE',
		    url: '/org_admin/user/deleteawsendpoint?id='+id,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.deleteawsendpoint=response.data;});},
		getawsregions : function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getawsregions',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getawsregions=response.data;});},
		getallvsphereendpoint : function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getallvsphereendpoint',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getallvsphereendpoint=response.data;});},
		addvsphereendpoint : function(endpoint){$http({
			method: 'POST',
		    url: '/org_admin/user/addvsphereendpoint',
		    data: endpoint,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.addvsphereendpoint=response.data;$scope.getallvsphereendpoint.unshift($scope.addvsphereendpoint);});},
		updatevsphereendpoint : function(endpoint){$http({
			method: 'POST',
		    url: '/org_admin/user/updatevsphereendpoint',
		    data: endpoint,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.updatevsphereendpoint=response.data;});},
		deletevsphereendpoint : function(id){$http({
			method: 'DELETE',
		    url: '/org_admin/user/deletevsphereendpoint?id='+id,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.deletevsphereendpoint=response.data;});},
		getvspherecomputes : function(endpoint){$http({
			method: 'POST',
		    url: '/org_admin/user/getvspherecomputes',
		    data: endpoint,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getvspherecomputes=response.data;});},
		addnewuser : function(userAuthModel){$http({
			method: 'POST',
		    url: '/admin/user/addnewuser',
		    data: userAuthModel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.addnewuser=response.data;$scope.getalluserdetails.unshift($scope.addnewuser);});},
		getalluserdetails : function(){$http({
			method: 'GET',
		    url: '/admin/user/getalluserdetails',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getalluserdetails=response.data;});},
		updateuserdetails : function(userDetailsModel){$http({
			method: 'POST',
		    url: '/admin/user/updateuserdetails',
		    data: userDetailsModel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.updateuserdetails=response.data;});},
		deleteuserdetails : function(userDetailsModel){$http({
			method: 'DELETE',
		    url: '/admin/user/deleteuserdetails',
		    data: userDetailsModel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.deleteuserdetails=response.data;});},
		getallroles : function(){$http({
			method: 'GET',
		    url: '/getallroles',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getallroles=response.data;});},
		getallawscatalog : function(){$http({
			method: 'GET',
		    url: '/catalog_admin/user/getallawscatalog',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getallawscatalog=response.data;});},
		addawscatalog : function(awscatalog){$http({
			method: 'POST',
		    url: '/catalog_admin/user/addawscatalog',
		    data: awscatalog,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.addawscatalog=response.data;$scope.getallawscatalog.unshift($scope.addawscatalog);});},
		updateawscatalog : function(awscatalog){$http({
			method: 'POST',
		    url: '/catalog_admin/user/updateawscatalog',
		    data: awscatalog,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.updateawscatalog=response.data;});},
		deleteawscatalog : function(id){$http({
			method: 'DELETE',
		    url: '/catalog_admin/user/deleteawscatalog?id='+id,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.deleteawscatalog=response.data;});},
		getbooleanvalues : function(){$http({
			method: 'GET',
		    url: '/getbooleanvalues',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getbooleanvalues=response.data;});},
		requestawscatalogs : function(awscatalog){$http({
			method: 'POST',
		    url: '/developer/user/requestawscatalogs',
		    data: awscatalog,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.requestawscatalogs=response.data;$scope.getallrequestmodel();});},
		getallawsrequestmodel : function(){$http({
			method: 'GET',
		    url: '/developer/user/getallawsrequestmodel',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getallawsrequestmodel=response.data;});},
		getallmachines : function(){$http({
			method: 'GET',
		    url: '/admin/user/getallmachines',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getallmachines=response.data;});},
		getallvspherecatalog : function(){$http({
			method: 'GET',
		    url: '/catalog_admin/user/getallvspherecatalog',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getallvspherecatalog=response.data;});},
		addvspherecatalog : function(vspherecatalog){$http({
			method: 'POST',
		    url: '/catalog_admin/user/addvspherecatalog',
		    data: vspherecatalog,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.addvspherecatalog=response.data;$scope.getallvspherecatalog.unshift($scope.addvspherecatalog);});},
		updatevspherecatalog : function(vspherecatalog){$http({
			method: 'POST',
		    url: '/catalog_admin/user/updatevspherecatalog',
		    data: vspherecatalog,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.updatevspherecatalog=response.data;});},
		deletevspherecatalog : function(id){$http({
			method: 'DELETE',
		    url: '/catalog_admin/user/deletevspherecatalog?id='+id,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.deletevspherecatalog=response.data;});},
		requestvspherecatalogs : function(vspherecatalog){$http({
			method: 'POST',
		    url: '/developer/user/requestvspherecatalogs',
		    data: vspherecatalog,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.requestvspherecatalogs=response.data;$scope.requestvspherecatalogs();});},
		getallvsphererequestmodel : function(){$http({
			method: 'GET',
		    url: '/developer/user/getallvsphererequestmodel',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){$scope.getallvsphererequestmodel=response.data;});}
	};
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
    .when("/manage_my_org", {
        templateUrl : "templates/manage_my_org.html"
    })
    .when("/manage_my_endpoints", {
        templateUrl : "templates/manage_my_endpoints.html"
    })
    .when("/manage_myorg_catalogs", {
        templateUrl : "templates/manage_myorg_catalogs.html"
    })
    .when("/my_org_requests", {
        templateUrl : "templates/my_org_requests.html"
    })
    .when("/manage_myorg_machines", {
        templateUrl : "templates/manage_myorg_machines.html"
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
