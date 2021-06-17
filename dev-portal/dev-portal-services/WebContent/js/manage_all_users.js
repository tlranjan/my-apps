app.controller('manage_all_users', function($scope, $filter, $http) {
		var self = this;	
		self.getalluserdetailsmodel = function(){$http({
			method: 'GET',
		    url: '/admin/user/getalluserdetailsmodel',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getalluserdetailsmodel=response.data;});};
		self.addnewuser = function(userauthmodel){$http({
			method: 'POST',
		    url: '/admin/user/addnewuser',
		    data: userauthmodel,
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.addnewuser=response.data;self.getalluserdetailsmodel.unshift(self.addnewuser);});};
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
		self.getallorganizationdetailsnames = function(){$http({
			method: 'GET',
		    url: '/admin/user/getallorganizationdetailsnames',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getallorganizationdetailsnames=response.data;});};
		self.getallroles = function(){$http({
			method: 'GET',
		    url: '/admin/user/getallroles',
		    headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getallroles=response.data;});};
		self.getalluserdetailsmodel();
		self.getallorganizationdetailsnames();
		self.getallroles();
});