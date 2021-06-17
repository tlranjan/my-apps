app.controller('manage_myorgs_catalog', function($scope, $filter, $http) {
		var self = this;
	    self.getmyorgsawscatalogmodel = function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getmyorgsawscatalogmodel',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getmyorgsawscatalogmodel=response.data;});};
		self.addawscatalogmodel = function(awscatalogmodel){$http({
			method: 'POST',
		    url: '/catalog_admin/user/addawscatalogmodel',
		    data: awscatalogmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.addawscatalogmodel=response.data;self.getmyorgsawscatalogmodel.unshift(self.addawscatalogmodel);});};
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
		self.getmyorgsawsendpointnames = function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getmyorgsawsendpointnames',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getmyorgsawsendpointnames=response.data;});};
		self.getmyorgsvspherecatalogmodel = function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getmyorgsvspherecatalogmodel',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getmyorgsvspherecatalogmodel=response.data;});};
		self.addvspherecatalogmodel = function(vspherecatalogmodel){$http({
			method: 'POST',
		    url: '/catalog_admin/user/addvspherecatalogmodel',
		    data: vspherecatalogmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.addvspherecatalogmodel=response.data;self.getmyorgsvspherecatalogmodel.unshift(self.addvspherecatalogmodel);});},
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
		self.getmyorgsvsphereendpointnames = function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getmyorgsvsphereendpointnames',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getmyorgsvsphereendpointnames=response.data;});};
		self.getcatalogpublishvalues = function(){$http({
			method: 'GET',
		    url: '/getcatalogpublishvalues',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getcatalogpublishvalues=response.data;});};
		self.getmyorgsawscatalogmodel();
		self.getmyorgsawsendpointnames();
		self.getmyorgsvspherecatalogmodel();
		self.getmyorgsvsphereendpointnames();
		self.getcatalogpublishvalues();
});
