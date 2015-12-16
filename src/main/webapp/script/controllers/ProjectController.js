/**
 * Created by Jasmin on 20-Apr-15.
 */

app.controller('ProjectController', function ($translate, $scope,$rootScope,$cookieStore,pmsService,$location) {
    $rootScope.setHeader('PROJECT_LABEL');
    $rootScope.setSubHeader('PROJECT_LABEL');
    $rootScope.navigation.current='projects';
    $scope.test="awdawdawd";
    $scope.currentProject={};
    $scope.tasks=[];
    $scope.projects=null;
    $scope.loadProjects=function()
       {
           pmsService.getProjects($rootScope.authData.user.id).success(function(data){
               $scope.projects=data;
           });
       };

    $scope.loadProjects();

    $scope.goToProjectDetails=function(projectId){

        $location.path("/projectDetails/"+projectId);
    };

    $scope.refreshTasks=function(projectId){
        pmsService.getProject(projectId).success(function(data){
            $scope.currentProject=data;
            $scope.tasks=[];
            var i,len;
            for (i = 0, len = $scope.currentProject.tasks.length; i < len; ++i) {
                pmsService.getTask($scope.currentProject.tasks[i]).success(function(data){
                    $scope.tasks.push(data);
                });
            }
        });
    };

    $scope.newProject={
      name:""
    };
    $scope.createNewProject=function(){
        pmsService.createProject($scope.newProject).success(function(data)
        {

            $scope.currentProject=data;
            pmsService.addUserToProject($scope.currentProject.id,$rootScope.authData.user.id,'OWNER').success(function(data)
                {
                    $rootScope.setInfoMessage("PROJECT_CREATED");
                    $scope.newProject={
                        name:""
                    };
                    $scope.loadProjects();
                }
            )
        });
    }
});