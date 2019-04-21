app.controller('indexController',function($scope,LoginService) {
    $scope.showLoginName =function() {
        //读取当前登录人
        LoginService.LoginName().success(
            function (response) {
                $scope.loginName = response.loginName;
            }
        )
    }


});