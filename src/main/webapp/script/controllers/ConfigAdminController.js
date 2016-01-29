/**
 * Created by Jasmin on 20-Apr-15.
 */
(function () {

    var ConfigAdminController=function ($translate, $scope,$rootScope,$location,pmsService) {
        $rootScope.setHeader('DASHBOADR_LABEL');
        $rootScope.setSubHeader('EMPTY_LABEL');
        $rootScope.navigation.current='configAdmin';
        $scope.searchParameter='';
        $scope.configs=[];

        $scope.loadConfigs=function()
        {
                pmsService.getConfigs().success(function(data){
                $scope.configs=data;
            });
        };
        $scope.loadConfigs();
        $scope.updateConfigs=function(){
          pmsService.updateConfigs($scope.configs).success(function(){
              $rootScope.setInfoMessage('CONFIGS_UPDATED');
          })
        };

        $scope.reset=function(){
            if($scope.searchParameter!='')
                $scope.search();
            else
                $scope.loadConfigs();
        };

        $scope.clear=function(){
            $scope.searchParameter='';
            $scope.loadConfigs();
        };

        $scope.search=function(){
            if($scope.searchParameter==null || $scope.searchParameter=='')
            {
                $scope.clear();
                return;
            }
            pmsService.searchConfigs($scope.searchParameter).success(function(data){
                $scope.configs=data;
            });
        };
    };

    app.controller('ConfigAdminController',ConfigAdminController );

}());