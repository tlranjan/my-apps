app.controller('manage_myorgs_endpoint', function($scope, $filter, $http) {
		var self = this;
		self.getmyorgsawsendpointmodel = function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getmyorgsawsendpointmodel',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getmyorgsawsendpointmodel=response.data;});};
		self.addawsendpointmodel = function(awsendpointmodel){$http({
			method: 'POST',
		    url: '/org_admin/user/addawsendpointmodel',
		    data: awsendpointmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.addawsendpointmodel=response.data;self.getmyorgsawsendpointmodel.unshift(self.addawsendpointmodel);});};
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
		self.getmyorgsawsendpointnames = function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getmyorgsawsendpointnames',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getmyorgsawsendpointnames=response.data;});};
		self.getawscomputes = function(awsendpointmodel){$http({
			method: 'POST',
		    url: '/org_admin/user/getawscomputes',
		    data: awsendpointmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getawscomputes=response.data;});};
		self.getmyorgsvsphereendpointmodel = function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getmyorgsvsphereendpointmodel',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getmyorgsvsphereendpointmodel=response.data;});};
		self.addvsphereendpointmodel = function(vsphereendpointmodel){$http({
			method: 'POST',
		    url: '/org_admin/user/addvsphereendpointmodel',
		    data: vsphereendpointmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.addvsphereendpointmodel=response.data;self.getmyorgsvsphereendpointmodel.unshift(self.addvsphereendpointmodel);});};
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
		self.getmyorgsvsphereendpointnames = function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getmyorgsvsphereendpointnames',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getmyorgsvsphereendpointnames=response.data;});};
		self.getvspherecomputes = function(vsphereendpointmodel){$http({
			method: 'POST',
		    url: '/org_admin/user/getvspherecomputes',
		    data: vsphereendpointmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getvspherecomputes=response.data;});};
		self.getmyorganizationnames = function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getmyorganizationnames',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getmyorganizationnames=response.data;});};
		self.getmyorgsawsendpointmodel();
		self.getmyorgsvsphereendpointmodel();
		self.getmyorganizationnames();
});
