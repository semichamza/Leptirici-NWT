/**
 * Created by Jasmin on 20-Apr-15.
 */
(function () {

    var StatisticsController=function ($translate, $scope,$rootScope,$location,pmsService) {
        $rootScope.setHeader('STATISTICS_LABEL');
        $rootScope.setFixedSubHeader('');
        $rootScope.navigation.current='statistics';

    };

    app.controller('StatisticsController',StatisticsController );

}());