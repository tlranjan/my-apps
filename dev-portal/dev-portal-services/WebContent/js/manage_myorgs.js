app.controller('manage_myorgs', function($scope, $filter, $http) {
		var self = this;	
		self.getmyorganizationdetailsmodel = function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getmyorganizationdetailsmodel',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getmyorganizationdetailsmodel=response.data;});};
		self.addorganizationdetailsmodel = function(organizationdetailsmodel){$http({
			method: 'POST',
		    url: '/org_admin/user/addorganizationdetailsmodel',
		    data: organizationdetailsmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.addorganizationdetailsmodel=response.data;self.getmyorganizationdetailsmodel.unshift(self.addorganizationdetailsmodel);});};
		self.updateorganizationdetailsmodel = function(organizationdetailsmodel){$http({
			method: 'POST',
		    url: '/org_admin/user/updateorganizationdetailsmodel',
		    data: organizationdetailsmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.updateorganizationdetailsmodel=response.data;});};
		self.deleteorganizationdetailsmodel = function(id){$http({
			method: 'DELETE',
		    url: '/org_admin/user/deleteorganizationdetailsmodel?id='+id,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.deleteorganizationdetailsmodel=response.data;});};
		self.getmyorganizationdetailsmodel();
});