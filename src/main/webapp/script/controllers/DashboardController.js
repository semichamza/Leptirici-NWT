/**
 * Created by Jasmin on 20-Apr-15.
 */
(function () {

    var DashboardController=function ($translate, $scope,$rootScope,$location,pmsService) {
        $rootScope.setHeader('DASHBOADR_LABEL');
        $rootScope.setSubHeader('EMPTY_LABEL');
        $rootScope.navigation.current='dashboard';

        if($rootScope.authData.user.userPrincipal.userRole=='ADMINISTRATOR') {
            $location.path("/dashboardAdmin");
        }

    };

    app.controller('DashboardController',DashboardController );

}());