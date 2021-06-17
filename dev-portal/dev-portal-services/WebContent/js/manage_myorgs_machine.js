app.controller('manage_myorgs_machine', function($scope, $filter, $http) {
	var self = this;
	self.getmyorgsmachinemodel = function(){$http({
		method: 'GET',
		url: '/org_admin/user/getmyorgsmachinemodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getmyorgsmachinemodel=response.data;});};
	self.getmyorgsmachinemodel();
});
