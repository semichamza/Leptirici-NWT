/**
 * Created by Jasmin on 16-Apr-15.
 */
app.service('pmsService', function ($http, $rootScope, $cookieStore,$location) {
    var UserApiURL = "/PMS-NSI/rest/users";
    var ProjectApiURL = "/PMS-NSI/rest/projects";
    var TaskApiURL = "/PMS-NSI/rest/tasks";
    var ConfigApiURL = "/PMS-NSI/rest/config";
    var pmsService={};

     var _postData = function (path, data) {
        return $http.post(path, data, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization':$rootScope.authData.jwt
            }
        });
    };
    var _putData = function (path, data) {
        return $http.put(path, data, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization':$rootScope.authData.jwt
            }
        });
    };

    var _getData=function (path){
      return $http({
          url: path,
          method: "GET",
          data: {},
          headers: {
              'Authorization':$rootScope.authData.jwt,
              'Accept': 'application/json',
              'Content-Type': 'application/json'
          }
      })
    };

    var _getUserData=function(){
        return _getData(UserApiURL+"/"+$rootScope.user.name);
    };

    var _getUser=function(username){
        return _getData(UserApiURL+"/"+username);
    };
    var _getUsers=function(){
        return _getData(UserApiURL);
    };
    var _getDeletedUsers=function(){
        return _getData(UserApiURL+"/deleted");
    };
    var _searchUsers=function(text,deleted){
        return _getData(UserApiURL+"/search/"+text+"/"+deleted);
    };
    var _searchProjects=function(text){
        return _getData(ProjectApiURL+"/search/"+text);
    };
    var _searchConfigs=function(text){
        return _getData(ConfigApiURL+"/search/"+text);
    };
    var _removeUser=function(projectId,userId){
        return _putData(ProjectApiURL+"/"+projectId+"/users/"+userId+"/delete");
    };

    var _getUserRole=function(projectId,userId){
        return _getData(ProjectApiURL+"/"+projectId+"/users/"+userId+"/role");
    };
    var _getProject=function(id){
        return _getData(ProjectApiURL+"/"+id);
    };

    var _createProject=function(project){
        return _postData(ProjectApiURL,project);
    };

    var _closeProject=function(projectId){
        return _putData(ProjectApiURL+"/"+projectId+"/close");
    };
    var _assigneeTask=function(taskId,userId){
        return _putData(TaskApiURL+"/"+taskId+"/users/"+userId+"/assignee");
    };

    var _createTask=function(task){
        return _postData(TaskApiURL,task);
    };

    var _createUser=function(user){
        return _postData(UserApiURL,user);
    };

    var _getProjects=function(id){
        return _getData(UserApiURL+"/"+id+"/projects");
    };

    var _getAllProjects=function(){
        return _getData(ProjectApiURL);
    };

    var _getProjectTasks=function(id){
        return _getData(ProjectApiURL+"/"+id+"/tasks");
    };

    var _getProjectTasksOrdered=function(id,order){
        return _getData(ProjectApiURL+"/"+id+"/tasks/"+order);
    };

    var _getProjectUsers=function(id){
        return _getData(ProjectApiURL+"/"+id+"/users");
    };

    var _getFreeUsers=function(id){
        return _getData(ProjectApiURL+"/"+id+"/users/free");
    };

    var _getConfigs=function(){
        return _getData(ConfigApiURL);
    };

    var _addUserToProject=function(projectId,userId,projectRole){
        return _postData(ProjectApiURL+"/"+projectId+"/addUser?userId="+userId+"&projectRole="+projectRole);
    };

    var _blockUser=function(id){
        return _putData(UserApiURL+"/block/"+id,'{}');
    };
    var _unblockUser=function(id){
        return _putData(UserApiURL+"/unblock/"+id,'{}');
    };
    var _blockUsers=function(users){
        return _putData(UserApiURL+"/block",users);
    };
    var _unblockUsers=function(users){
        return _putData(UserApiURL+"/unblock",users);
    };
    var _deleteUsers=function(users){
        return _putData(UserApiURL+"/delete",users);
    };
    var _revertUsers=function(users){
        return _putData(UserApiURL+"/revert",users);
    };
    var _updateUser=function(user){
        return _putData(UserApiURL,user);
    };

    var _updatePassword=function(password){
        return _putData(UserApiURL+"/newPassword",password);
    };

    var _updateTask=function(task){
        return _putData(TaskApiURL,task);
    };

    var _updateTaskStatus=function(task,status){
        return _putData(TaskApiURL+"/"+task+"/status/"+status);
    };
    var _updateProject=function(project){
        return _putData(ProjectApiURL,project);
    };
    var _updateConfigs=function(configs){
        return _putData(ConfigApiURL,configs);
    };
    var _getUserMessages=function(userId){
        return _getData(UserApiURL+"/"+userId+"/messages");
    };
    var _getUserSentMessages=function(userId){
        return _getData(UserApiURL+"/"+userId+"/sentmessages");
    };

    var _getUserTasks=function(userId,projectId){
        return _getData(TaskApiURL+"/users?userId="+userId+"&projectId="+projectId);
    };

    var _getUserUnreadMessages=function(userId){
        return _getData(UserApiURL+"/"+userId+"/messages/unread");
    };

    var _readAllMessages=function(userId){
        return _putData(UserApiURL+"/"+userId+"/messages/readAll","");
    };

    var _sendMessage=function(sender,receiver,text){
        console.log(sender);
        console.log(receiver);
        var message={
            "sender":sender,
            "receiver":receiver,
            "text":text
        };
        return _postData(UserApiURL+"/messages/send",message);
    };

    var _getTask=function(id){
        return _getData(TaskApiURL+"/"+id);
    };


    pmsService.blockUser=_blockUser;
    pmsService.getAllProjects=_getAllProjects;
    pmsService.getUserMessages=_getUserMessages;
    pmsService.getUserSentMessages=_getUserSentMessages;
    pmsService.getUserUnreadMessages=_getUserUnreadMessages;
    pmsService.sendMessage=_sendMessage;
    pmsService.readAllMessages=_readAllMessages;
    pmsService.blockUsers=_blockUsers;
    pmsService.unblockUsers=_unblockUsers;
    pmsService.deleteUsers=_deleteUsers;
    pmsService.revertUsers=_revertUsers;
    pmsService.updateUser=_updateUser;
    pmsService.searchUsers=_searchUsers;
    pmsService.unblockUser=_unblockUser;
    pmsService.getUser=_getUser;
    pmsService.getUsers=_getUsers;
    pmsService.getDeletedUsers=_getDeletedUsers;
    pmsService.getUserData=_getUserData;
    pmsService.getProjects=_getProjects;
    pmsService.getProject=_getProject;
    pmsService.createProject=_createProject;
    pmsService.closeProject=_closeProject;
    pmsService.assigneeTask=_assigneeTask;
    pmsService.createTask=_createTask;
    pmsService.createUser=_createUser;
    pmsService.getTask=_getTask;
    pmsService.getProjectTasks=_getProjectTasks;
    pmsService.getProjectTasksOrdered=_getProjectTasksOrdered;
    pmsService.getProjectUsers=_getProjectUsers;
    pmsService.getFreeUsers=_getFreeUsers;
    pmsService.addUserToProject=_addUserToProject;
    pmsService.getConfigs=_getConfigs;
    pmsService.updateConfigs=_updateConfigs;
    pmsService.searchConfigs=_searchConfigs;
    pmsService.searchProjects=_searchProjects;
    pmsService.removeUser=_removeUser;
    pmsService.getUserRole=_getUserRole;
    pmsService.updateTask=_updateTask;
    pmsService.getUserTasks=_getUserTasks;
    pmsService.updateProject=_updateProject;
    pmsService.updateTaskStatus=_updateTaskStatus;
    pmsService.updatePassword=_updatePassword;
    return pmsService;


});

