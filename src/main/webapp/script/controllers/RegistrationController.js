/**
 * Created by Jasmin on 16-Apr-15.
 */

app.controller('RegistrationController',function($rootScope,$scope,$location,authService)
{
   //$scope.newUser={
   //    firstName:'test',
   //    lastName:'test',
   //    username:'test',
   //    password:'test',
   //    email:'jasmin.kaldzija@gmail.com'
   //} ;

    $scope.newUser={
       firstName:'',
       lastName:'',
       username:'',
       password:'',
       email:''
   } ;
   $scope.goToLogin=function()
    {
        $location.path('/login');
    }

    $scope.register=function()
    {
        authService.register($scope.newUser)
            .success(function(data){
                $rootScope.setInfoMessage('ACTIVATION_LINK');})
            .error(function(){
                $rootScope.setDangerMessage('REGISTRATION_ERROR');
        });
    };
});