/**
 * Created by Jasmin on 16-Apr-15.
 */
app.service('pmsService', function ($http, $rootScope, $cookieStore,$location) {
    var UserApiURL = "/PMS-NWT/rest/users";
    var ProjectApiURL = "/PMS-NWT/rest/projects";
    var TaskApiURL = "/PMS-NWT/rest/tasks";
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
    var _searchUsers=function(text){
        return _getData(UserApiURL+"/search/"+text);
    };
    var _getProject=function(id){
        return _getData(ProjectApiURL+"/"+id);
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
    var _updateUser=function(user){
        return _putData(UserApiURL,user);
    };

    var _getUserMessages=function(userId){
        return _getData(UserApiURL+"/"+userId+"/messages");
    };
    var _getUserSentMessages=function(userId){
        return _getData(UserApiURL+"/"+userId+"/sentmessages");
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
    pmsService.updateUser=_updateUser;
    pmsService.searchUsers=_searchUsers;
    pmsService.unblockUser=_unblockUser;
    pmsService.getUser=_getUser;
    pmsService.getUsers=_getUsers;
    pmsService.getUserData=_getUserData;
    pmsService.getProjects=_getProjects;
    pmsService.getProject=_getProject;
    pmsService.getTask=_getTask;
    pmsService.getProjectTasks=_getProjectTasks;

    return pmsService;


});

