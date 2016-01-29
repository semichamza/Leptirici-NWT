/**
 * Created by Jasmin on 20-Apr-15.
 */
(function () {

    var UserSettings=function ($translate,$resource, $scope,$rootScope,$location,pmsService) {
        $rootScope.setHeader('SETTINGS');
        $rootScope.setFixedSubHeader('');
        $rootScope.navigation.current='dashboard';
        $scope.newImage={};
        $scope.tempUser=jQuery.extend(true,{},$rootScope.authData.user);
        $scope.newPassword={
          pass1:'',
          pass2:''
        };
        $scope.update=function(){
          pmsService.updateUser($scope.tempUser).success(function(){
              $rootScope.setInfoMessage('USER_UPDATED');
              $rootScope.authData.user=$scope.tempUser;
          });
        };
        $scope.updatePassword=function(){
            if($scope.newPassword.pass1!=$scope.newPassword.pass2)
            {
                $rootScope.setDangerMessage('PASSWORD_NOT_MATCHES');
            }else if(!$scope.newPassword.pass1||$scope.newPassword.pass1=='')
            {
                $rootScope.setDangerMessage('PASSWORD_EMPTY');
            }else {
                var password={
                    userId:$scope.tempUser.id,
                    password:$scope.newPassword.pass1
                };
                pmsService.updatePassword(password).success(function(){
                    $rootScope.setInfoMessage('PASSWORD_UPDATED');
                });
            }
        };
    };

    app.controller('UserSettings',UserSettings );

}());