/**
 * Created by Jasmin on 16-Apr-15.
 */

app.controller('LoginController',function($rootScope,$scope,$location,authService)
{
    $scope.accaunt={
        username:'',
        password:''
    };
    $scope.resetUsername='';

    $scope.login=function() {
        authService.login($scope.accaunt)
            .success(function () {
                $rootScope.setMessage('LOGIN_MESSAGE','info');
                $location.path("/dashboard");
            });
    };
    $scope.reset=function()
    {

        var jsonUsername={
          username:$scope.resetUsername
        };
        authService.reset(jsonUsername)
            .success(function(){
                $rootScope.setInfoMessage('LINK_SENT');
            });
    };
    $scope.goToRegistration=function()
    {
        $location.path('/registration');
    }
});