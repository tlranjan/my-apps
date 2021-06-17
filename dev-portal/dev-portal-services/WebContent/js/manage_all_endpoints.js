app.controller('manage_all_endpoints', function($scope, $filter, $http) {
		var self = this;	
		self.getallawsendpointmodel = function(){$http({
			method: 'GET',
		    url: '/admin/user/getallawsendpointmodel',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getallawsendpointmodel=response.data;});};
		self.addawsendpointmodel = function(awsendpointmodel){$http({
			method: 'POST',
		    url: '/org_admin/user/addawsendpointmodel',
		    data: awsendpointmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.addawsendpointmodel=response.data;self.getallawsendpointmodel.unshift(self.addawsendpointmodel);});};
		self.updateawsendpointmodel = function(awsendpointmodel){$http({
			method: 'POST',
		    url: '/org_admin/user/updateawsendpointmodel',
		    data: awsendpointmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.updateawsendpointmodel=response.data;});};
		self.deleteawsendpointmodel = function(id){$http({
			method: 'DELETE',
		    url: '/org_admin/user/deleteawsendpointmodel?id='+id,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.deleteawsendpointmodel=response.data;});};
		self.getawscomputes = function(awsendpointmodel){$http({
			method: 'POST',
		    url: '/org_admin/user/getawscomputes',
		    data: awsendpointmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getawscomputes=response.data;});};
		self.getallvsphereendpointmodel = function(){$http({
			method: 'GET',
		    url: '/admin/user/getallvsphereendpointmodel',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getallvsphereendpointmodel=response.data;});};
		self.addvsphereendpointmodel = function(vsphereendpointmodel){$http({
			method: 'POST',
		    url: '/org_admin/user/addvsphereendpointmodel',
		    data: vsphereendpointmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.addvsphereendpointmodel=response.data;self.getallvsphereendpointmodel.unshift(self.addvsphereendpointmodel);});};
		self.updatevsphereendpointmodel = function(vsphereendpointmodel){$http({
			method: 'POST',
		    url: '/org_admin/user/updatevsphereendpointmodel',
		    data: vsphereendpointmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.updatevsphereendpointmodel=response.data;});};
		self.deletevsphereendpointmodel = function(id){$http({
			method: 'DELETE',
		    url: '/org_admin/user/deletevsphereendpointmodel?id='+id,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.deletevsphereendpointmodel=response.data;});};
		self.getvspherecomputes = function(vsphereendpointmodel){$http({
			method: 'POST',
		    url: '/org_admin/user/getvspherecomputes',
		    data: vsphereendpointmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getvspherecomputes=response.data;});};
		self.getallorganizationdetailsnames = function(){$http({
			method: 'GET',
		    url: '/admin/user/getallorganizationdetailsnames',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getallorganizationdetailsnames=response.data;});};
		self.getallawsendpointmodel();
		self.getallvsphereendpointmodel();
		self.getallorganizationdetailsnames();
});
