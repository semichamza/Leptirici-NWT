/**
 * Created by Jasmin on 20-Apr-15.
 */
(function () {

    var DashboardController=function ($translate, $scope,$rootScope,$cookieStore,pmsService) {
        $rootScope.setHeader('DASHBOADR_LABEL');
        $rootScope.setSubHeader('DASHBOADR_LABEL');
        $rootScope.navigation.current='dashboard';

            };

    app.controller('DashboardController',DashboardController );

}());