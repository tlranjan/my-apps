app.controller('published_orgs_catalogs', function($scope, $filter, $http) {
	var self = this;
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
	self.getmyorgawspublishedcatalogmodel();
	self.getmyorgvspherepublishedcatalogmodel();
});
