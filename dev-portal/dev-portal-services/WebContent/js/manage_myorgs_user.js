app.controller('manage_myorgs_user', function($scope, $filter, $http) {
		var self = this;	
		self.getmyorgsuserdetailsmodel = function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getmyorgsuserdetailsmodel',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getmyorgsuserdetailsmodel=response.data;});};
		self.updateuserdetailsmodel = function(userdetailsmodel){$http({
			method: 'POST',
		    url: '/org_admin/user/updateuserdetailsmodel',
		    data: userdetailsmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.updateuserdetailsmodel=response.data;});};
		self.deleteuserdetailsmodel = function(userdetailsmodel){$http({
			method: 'DELETE',
		    url: '/org_admin/user/deleteuserdetailsmodel',
		    data: userdetailsmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.deleteuserdetailsmodel=response.data;});};
		self.getmyorganizationnames = function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getmyorganizationnames',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getmyorganizationnames=response.data;});};
		self.getallroles = function(){$http({
			method: 'GET',
		    url: '/org_admin/user/getallroles',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getallroles=response.data;});};
		self.getmyorgsuserdetailsmodel();
		self.getmyorganizationnames();
		self.getallroles();
});