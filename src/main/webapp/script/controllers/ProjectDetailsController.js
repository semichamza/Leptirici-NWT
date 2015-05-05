/**
 * Created by Jasmin on 20-Apr-15.
 */
(function () {

    var ProjectDetailsController=function ($translate, $scope,$rootScope,$routeParams,pmsService) {
        $rootScope.setHeader('PROJECT_DETAIL_LABEL');
        $rootScope.setSubHeader('PROJECT_DETAIL_LABEL');
        $rootScope.navigation.current='projects';
        pmsService.getProjectTasks($routeParams.projectId).success(function(data){
            $scope.tasks=data;
        });

        pmsService.getProject($routeParams.projectId).success(function(data){
            $scope.project=data;
            $rootScope.setFixedHeader(data.name);
        });

    };

    app.controller('ProjectDetailsController',ProjectDetailsController );

}());