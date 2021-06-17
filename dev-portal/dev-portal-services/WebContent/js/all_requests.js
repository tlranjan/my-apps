app.controller('all_requests', function($scope, $filter, $http) {
	var self = this;	
	self.getallawsrequestmodel = function(){$http({
		method: 'GET',
		url: '/admin/user/getallawsrequestmodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getallawsrequestmodel=response.data;});};
	self.getallawscatalogmodel = function(){$http({
		method: 'GET',
		url: '/admin/user/getallawscatalogmodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getallawscatalogmodel=response.data;});};
	self.requestawscatalog = function(awsrequestmodel){$http({
		method: 'POST',
	    url: '/developer/user/requestawscatalog',
	    data: awsrequestmodel,
	    headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.requestawscatalog=response.data;self.getallawsrequestmodel.unshift(self.requestawscatalog);});};
	self.getallvsphererequestmodel = function(){$http({
		method: 'GET',
		url: '/admin/user/getallvsphererequestmodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getallvsphererequestmodel=response.data;});};
	self.getallvspherecatalogmodel = function(){$http({
		method: 'GET',
		url: '/admin/user/getallvspherecatalogmodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getallvspherecatalogmodel=response.data;});};
	self.requestvspherecatalog = function(vsphererequestmodel){$http({
		method: 'POST',
	    url: '/developer/user/requestvspherecatalog',
	    data: vsphererequestmodel,
	    headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.requestvspherecatalog=response.data;self.getallvsphererequestmodel.unshift(self.requestvspherecatalog);});};
	self.getallawsrequestmodel();
	self.getallawscatalogmodel()
	self.getallvsphererequestmodel();
	self.getallvspherecatalogmodel();
});