/**
 * Created by Jasmin on 16-Apr-15.
 */

app.controller('LoginController',function($rootScope,$scope,$location,authService)
{
    $scope.accaunt={
        username:'',
        password:''
    };

    $scope.login=function() {
        authService.login($scope.accaunt)
            .success(function (data) {
                var userProfile = {
                    jwt: data.jwt,
                    name: data.name
                };

                authService.setAuthorization(userProfile);
                $location.path("/dashboard");
            });
    };
});