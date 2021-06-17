app.controller('manage_all_machines', function($scope, $filter, $http) {
	var self = this;	
	self.getallmachinemodel = function(){$http({
		method: 'GET',
		url: '/admin/user/getallmachinemodel',
		headers: {'Content-Type': 'application/json'}
	}).then(function(response){self.getallmachinemodel=response.data;});};
	self.getallmachinemodel();
});