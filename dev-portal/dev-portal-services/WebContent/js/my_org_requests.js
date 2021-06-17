app.controller('my_org_requests', function($scope, $filter, $http) {
	var self = this;
	self.getmyorgawsrequestmodel = function(){$http({
		method: 'GET',
		url: '/catalog_admin/user/getmyorgawsrequestmodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getmyorgawsrequestmodel=response.data;});};
	self.getmyorgvsphererequestmodel = function(){$http({
		method: 'GET',
		url: '/catalog_admin/user/getmyorgvsphererequestmodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getmyorgvsphererequestmodel=response.data;});};
	self.getmyorgawscatalogmodel = function(){$http({
		method: 'GET',
		url: '/catalog_admin/user/getmyorgawscatalogmodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getmyorgawscatalogmodel=response.data;});};
	self.getmyorgvspherecatalogmodel = function(){$http({
		method: 'GET',
		url: '/catalog_admin/user/getmyorgvspherecatalogmodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getmyorgvspherecatalogmodel=response.data;});};
	self.requestawscatalog = function(awsrequestmodel){$http({
		method: 'POST',
	    url: '/developer/user/requestawscatalog',
	    data: awsrequestmodel,
	    headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.requestawscatalog=response.data;self.getmyorgawsrequestmodel.unshift(self.requestawscatalog);});};
	self.requestvspherecatalog = function(vsphererequestmodel){$http({
		method: 'POST',
	    url: '/developer/user/requestvspherecatalog',
	    data: vsphererequestmodel,
	    headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.requestvspherecatalog=response.data;self.getmyorgvsphererequestmodel.unshift(self.requestvspherecatalog);});};
	self.getmyorgawsrequestmodel();
	self.getmyorgvsphererequestmodel();
	self.getmyorgawscatalogmodel();
	self.getmyorgvspherecatalogmodel();
});
