app.controller('my_orgs_request', function($scope, $filter, $http) {
	var self = this;
	self.getmyorgsawsrequestmodel = function(){$http({
		method: 'GET',
		url: '/org_admin/user/getmyorgsawsrequestmodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getmyorgsawsrequestmodel=response.data;});};
	self.requestawscatalog = function(awsrequestmodel){$http({
		method: 'POST',
	    url: '/developer/user/requestawscatalog',
	    data: awsrequestmodel,
	    headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.requestawscatalog=response.data;self.getmyorgsawsrequestmodel.unshift(self.requestawscatalog);});};
	self.getmyorgsawscatalogmodel = function(){$http({
		method: 'GET',
		url: '/org_admin/user/getmyorgsawscatalogmodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getmyorgsawscatalogmodel=response.data;});};
	self.getmyorgsvsphererequestmodel = function(){$http({
		method: 'GET',
		url: '/org_admin/user/getmyorgsvsphererequestmodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getmyorgsvsphererequestmodel=response.data;});};
	self.requestvspherecatalog = function(vsphererequestmodel){$http({
		method: 'POST',
	    url: '/developer/user/requestvspherecatalog',
	    data: vsphererequestmodel,
	    headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.requestvspherecatalog=response.data;self.getmyorgsvsphererequestmodel.unshift(self.requestvspherecatalog);});};
	self.getmyorgsvspherecatalogmodel = function(){$http({
		method: 'GET',
		url: '/org_admin/user/getmyorgsvspherecatalogmodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getmyorgsvspherecatalogmodel=response.data;});};
	self.getmyorgsawsrequestmodel();
	self.getmyorgsawscatalogmodel();
	self.getmyorgsvsphererequestmodel();
	self.getmyorgsvspherecatalogmodel();
});
