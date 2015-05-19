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

        $scope.update=function(){
          pmsService.updateUser($scope.tempUser).success(function(){
              $rootScope.setInfoMessage('USER_UPDATED');
              $rootScope.authData.user=$scope.tempUser;
          });
        };

    };

    app.controller('UserSettings',UserSettings );

}());