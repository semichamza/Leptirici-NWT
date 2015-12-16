/**
 * Created by Jasmin on 20-Apr-15.
 */
(function () {

    var ProjectDetailsController=function ($translate, $scope,$rootScope,$routeParams,pmsService) {
        $rootScope.setHeader('PROJECT_DETAIL_LABEL');
        $rootScope.setSubHeader('PROJECT_DETAIL_LABEL');
        $rootScope.navigation.current='projects';
        $scope.isUserOwner=false;
        $scope.selectedTask={};
        $scope.formatedDate=null;

        $scope.setSelectedTask=function(task){
            $scope.selectedTask=task;
            if( $scope.selectedTask.dueDate!=null) {
                $scope.formatedDate = task.dueDate.substr(0, 10);
                //$scope.selectedTask.dueDate = task.dueDate.substr(0, 10);
            }
        };
        $scope.loadTasks=function()
        {
            pmsService.getProjectTasks($routeParams.projectId).success(function(data){
                $scope.tasks=data;
                $scope.setSelectedTask(data[0]);
            });
        };

        $scope.getReport=function()
        {
            $window.open('http://localhost:18080/PMS-NSI/rest/projects/'+project.id+'/report','_blank');
            console.log('aa');
        };

        $scope.loadTasks();
        pmsService.getUserRole($routeParams.projectId,$rootScope.authData.user.id).success(function(data)
        {
           if(data=="OWNER")
               $scope.isUserOwner=true;
        });
        pmsService.getProject($routeParams.projectId).success(function(data){
            $scope.project=data;
            $rootScope.setFixedHeader(data.name);
        });

        $scope.loadUsers=function()
        {
            pmsService.getProjectUsers($routeParams.projectId).success(function(data){
                $scope.projectUsers=JSOG.decode(data);
            });
            pmsService.getFreeUsers($routeParams.projectId).success(function(data){
                $scope.unlinkedUsers=JSOG.decode(data);
            });
        };


        $scope.loadUsers();
        //New task
        $scope.newTask={
            project:{id:$routeParams.projectId}
        };
        $scope.createNewTask=function()
        {
            pmsService.createTask($scope.newTask).success(function(data)
            {
                $rootScope.setInfoMessage("TASK_CREATED");
                $('#newTaskModal').modal('hide');
                $scope.loadTasks();
            });
        };

        $scope.createNewTask=function()
        {
            pmsService.createTask($scope.newTask).success(function(data)
            {
                $rootScope.setInfoMessage("TASK_CREATED");
                $('#newTaskModal').modal('hide');
                $scope.loadTasks();
            });
        };

        $scope.addUserOnProject=function()
        {
            pmsService.addUserToProject($scope.currentProject.id,$rootScope.authData.user.id,'OWNER').success(function(data)
                {
                    $scope.newProject={
                        name:""
                    };
                    $scope.loadProjects();
                }
            )
        };

        $scope.removeUserFromProject=function(userId)
        {
            pmsService.removeUser($routeParams.projectId,userId).success(function(data)
            {
                $scope.loadUsers()
            });
        };
        $scope.addUser=function(id)
        {
            pmsService.addUserToProject($routeParams.projectId,id,'MEMBER').success(function(data)
                {
                    $scope.loadUsers();
                });

        };

        $scope.updateTask=function()
        {
            console.log("Begin task update");
            pmsService.updateTask($scope.selectedTask).success(function(data){
                $rootScope.setInfoMessage("TASK_EDITED");
                $('#editTaskModal').modal('hide');
                console.log("End task update");
            });
        };

        $scope.updateProject=function()
        {
            console.log("Begin project update");

            pmsService.updateProject($scope.project).success(function(data){
                $rootScope.setInfoMessage("PROJECT_EDITED");
                $('#editProjectModal').modal('hide');
                console.log("End project update");
                pmsService.getProject($routeParams.projectId).success(function(data){
                    $scope.project=data;
                    $rootScope.setFixedHeader(data.name);
                });
            });
        };
    };

    app.controller('ProjectDetailsController',ProjectDetailsController );

}());