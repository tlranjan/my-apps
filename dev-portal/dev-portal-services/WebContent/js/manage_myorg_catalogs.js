app.controller('manage_myorg_catalogs', function($scope, $filter, $http) {
	var self = this;
	    self.catalog_admin_getmyorgawscatalogmodel = function(){$http({
			method: 'GET',
		    url: '/catalog_admin/user/catalog_admin_getmyorgawscatalogmodel',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.catalog_admin_getmyorgawscatalogmodel=response.data;});};
		self.addawscatalogmodel = function(awscatalogmodel){$http({
			method: 'POST',
		    url: '/catalog_admin/user/addawscatalogmodel',
		    data: awscatalogmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.addawscatalogmodel=response.data;self.catalog_admin_getmyorgawscatalogmodel.unshift(self.addawscatalogmodel);});};
		self.updateawscatalogmodel = function(awscatalogmodel){$http({
			method: 'POST',
		    url: '/catalog_admin/user/updateawscatalogmodel',
		    data: awscatalogmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.updateawscatalogmodel=response.data;});};
		self.deleteawscatalogmodel = function(id){$http({
			method: 'DELETE',
		    url: '/catalog_admin/user/deleteawscatalogmodel?id='+id,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.deleteawscatalogmodel=response.data;});};
		self.getmyorgawsendpointnames = function(){$http({
			method: 'GET',
		    url: '/catalog_admin/user/getmyorgawsendpointnames',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getmyorgawsendpointnames=response.data;});};
		self.catalog_admin_getmyorgvspherecatalogmodel = function(){$http({
			method: 'GET',
		    url: '/catalog_admin/user/catalog_admin_getmyorgvspherecatalogmodel',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.catalog_admin_getmyorgvspherecatalogmodel=response.data;});};
		self.addvspherecatalogmodel = function(vspherecatalogmodel){$http({
			method: 'POST',
		    url: '/catalog_admin/user/addvspherecatalogmodel',
		    data: vspherecatalogmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.addvspherecatalogmodel=response.data;self.catalog_admin_getmyorgvspherecatalogmodel.unshift(self.addvspherecatalogmodel);});};
		self.updatevspherecatalogmodel = function(vspherecatalogmodel){$http({
			method: 'POST',
		    url: '/catalog_admin/user/updatevspherecatalogmodel',
		    data: vspherecatalogmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.updatevspherecatalogmodel=response.data;});};
		self.deletevspherecatalogmodel = function(id){$http({
			method: 'DELETE',
		    url: '/catalog_admin/user/deletevspherecatalogmodel?id='+id,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.deletevspherecatalogmodel=response.data;});};
		self.getmyorgvsphereendpointnames = function(){$http({
			method: 'GET',
		    url: '/catalog_admin/user/getmyorgvsphereendpointnames',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getmyorgvsphereendpointnames=response.data;});};
		self.getcatalogpublishvalues = function(){$http({
			method: 'GET',
		    url: '/getcatalogpublishvalues',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getcatalogpublishvalues=response.data;});};
		self.catalog_admin_getmyorgawscatalogmodel();
		self.catalog_admin_getmyorgvspherecatalogmodel();
		self.getmyorgawsendpointnames();
		self.getmyorgvsphereendpointnames();
		self.getcatalogpublishvalues();
});
