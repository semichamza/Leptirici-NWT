/**
 * Created by Jasmin on 20-Apr-15.
 */

app.controller('DashboardController', function ($translate, $scope,$rootScope,$cookieStore) {
    $rootScope.setHeader('DASHBOADR_LABEL');
    $rootScope.setSubHeader('DASHBOADR_LABEL');
    $rootScope.navigation.current='dashboard';
});