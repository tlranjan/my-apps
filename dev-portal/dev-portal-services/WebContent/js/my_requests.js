app.controller('my_requests', function($scope, $filter, $http) {
	var self = this;
	self.getmyawsrequestmodel = function(){$http({
		method: 'GET',
		url: '/developer/user/getmyawsrequestmodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getmyawsrequestmodel=response.data;});};
	self.getmyvsphererequestmodel = function(){$http({
		method: 'GET',
		url: '/developer/user/getmyvsphererequestmodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getmyvsphererequestmodel=response.data;});};
	self.getmyorgawspublishedcatalogmodel = function(){$http({
		method: 'GET',
	    url: '/developer/user/getmyorgawspublishedcatalogmodel',
	    headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getmyorgawspublishedcatalogmodel=response.data;});};
	self.getmyorgvspherepublishedcatalogmodel = function(){$http({
		method: 'GET',
		url: '/developer/user/getmyorgvspherepublishedcatalogmodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getmyorgvspherepublishedcatalogmodel=response.data;});};
	self.requestawscatalog = function(awsrequestmodel){$http({
		method: 'POST',
	    url: '/developer/user/requestawscatalog',
	    data: awsrequestmodel,
	    headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.requestawscatalog=response.data;self.getmyawsrequestmodel.unshift(self.requestawscatalog);});};
	self.requestvspherecatalog = function(vsphererequestmodel){$http({
		method: 'POST',
	    url: '/developer/user/requestvspherecatalog',
	    data: vsphererequestmodel,
	    headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.requestvspherecatalog=response.data;self.getmyvsphererequestmodel.unshift(self.requestvspherecatalog);});};
	self.getmyawsrequestmodel();
	self.getmyvsphererequestmodel();
	self.getmyorgawspublishedcatalogmodel();
	self.getmyorgvspherepublishedcatalogmodel();
});
