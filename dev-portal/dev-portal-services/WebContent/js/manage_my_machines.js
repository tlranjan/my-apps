app.controller('manage_my_machines', function($scope, $filter, $http) {
	var self = this;
	self.getmymachinemodel = function(){$http({
		method: 'GET',
		url: '/developer/user/getmymachinemodel',
		headers: {'Content-Type': 'application/json'}
		}).then(function(response){self.getmymachinemodel=response.data;});};
	self.getmymachinemodel();
});
