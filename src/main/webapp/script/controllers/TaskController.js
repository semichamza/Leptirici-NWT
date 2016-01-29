/**
 * Created by Jasmin on 20-Apr-15.
 */

app.controller('TaskController', function ($translate, $scope,$rootScope,pmsService,$cookieStore) {
    $rootScope.setHeader('TASK_LABEL');
    $rootScope.setSubHeader('TASK_LABEL');
    $rootScope.navigation.current='tasks';
    $scope.userTasks=null;
    $scope.userProjects=null;
    $scope.currentProjectId=null;
    $scope.initCurrentProjectId=null;

    $scope.loadProjects=function ()
    {
            pmsService.getProjects($rootScope.authData.user.id).success(function(data){
                $scope.userProjects=data;
                console.log($scope.userProjects[0].id);
                if($scope.userProjects!=null)
                {
                    $scope.currentProjectId=$scope.userProjects[0].id;
                    $scope.loadUserTasks();
                }
            });
    };
    $scope.loadProjects();

    $scope.loadUserTasks=function()
    {
            pmsService.getUserTasks($rootScope.authData.user.id,$scope.currentProjectId).success(function(data){
                $scope.userTasks=data;
                for(var i=0;i<$scope.userTasks.length;i++)
                {
                    if($scope.userTasks[i].taskStatus=='OPEN')
                    {
                        $scope.userTasks[i].style='warning';
                        $scope.userTasks[i].taskStatusString="Open";
                    }
                    if($scope.userTasks[i].taskStatus=='RESOLVED')
                    {
                        $scope.userTasks[i].style='success';
                        $scope.userTasks[i].taskStatusString="Resolved";
                    }
                    if($scope.userTasks[i].taskStatus=='IN_PROGRESS')
                    {
                        $scope.userTasks[i].style='info';
                        $scope.userTasks[i].taskStatusString="In progress"
                    }
                    if($scope.userTasks[i].taskStatus=='CLOSED')
                    {
                        $scope.userTasks[i].style='danger';
                        $scope.userTasks[i].taskStatusString="Closed";
                    }

                    console.log($scope.userTasks[i].style);
                }
            })
    };

    $scope.changeCurrentProject=function(currentProjectId){
        $scope.currentProjectId=currentProjectId;
        $scope.loadUserTasks();
    };

    $scope.changeStatus=function(task,status){
        pmsService.updateTaskStatus(task.id,status).success(function(data){
            $scope.loadUserTasks();
        });
    }
});