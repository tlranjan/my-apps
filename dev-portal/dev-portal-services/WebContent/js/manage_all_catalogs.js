app.controller('manage_all_catalogs', function($scope, $filter, $http) {
		var self = this;
		self.getallawscatalogmodel = function(){$http({
			method: 'GET',
		    url: '/admin/user/getallawscatalogmodel',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getallawscatalogmodel=response.data;});};
		self.addawscatalogmodel = function(awscatalogmodel){$http({
			method: 'POST',
		    url: '/catalog_admin/user/addawscatalogmodel',
		    data: awscatalogmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.addawscatalogmodel=response.data;self.getallawscatalogmodel.unshift(self.addawscatalogmodel);});};
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
		self.getallawsendpointnames = function(){$http({
			method: 'GET',
		    url: '/admin/user/getallawsendpointnames',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getallawsendpointnames=response.data;});};
		self.getallvspherecatalogmodel = function(){$http({
			method: 'GET',
		    url: '/admin/user/getallvspherecatalogmodel',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getallvspherecatalogmodel=response.data;});};
		self.addvspherecatalogmodel = function(vspherecatalogmodel){$http({
			method: 'POST',
		    url: '/catalog_admin/user/addvspherecatalogmodel',
		    data: vspherecatalogmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.addvspherecatalogmodel=response.data;self.getallvspherecatalogmodel.unshift(self.addvspherecatalogmodel);});};
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
		self.getallvsphereendpointnames = function(){$http({
			method: 'GET',
		    url: '/admin/user/getallvsphereendpointnames',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getallvsphereendpointnames=response.data;});};
		self.getcatalogpublishvalues = function(){$http({
			method: 'GET',
		    url: '/getcatalogpublishvalues',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getcatalogpublishvalues=response.data;});};
		self.getallawscatalogmodel();
		self.getallawsendpointnames();
		self.getallvspherecatalogmodel();
		self.getallvsphereendpointnames();
		self.getcatalogpublishvalues();
});