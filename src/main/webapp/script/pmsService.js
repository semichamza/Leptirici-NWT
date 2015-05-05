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
                'Content-Type': 'application/json'
            }
        });
    };
    var _putData = function (path, data) {
        return $http.put(path, data, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
    };

    var _getData=function (path){
      return $http({
          url: path,
          method: "GET",
          data: {},
          headers: {
              'Authorization':$rootScope.user.jwt,
              'Accept': 'application/json',
              'Content-Type': 'application/json'
          }
      })
    };

    var _getUserData=function(){
        return _getData(UserApiURL+"/"+$rootScope.user.name);
    };

    var _getProject=function(id){
        return _getData(ProjectApiURL+"/"+id);
    };

    var _getProjects=function(id){
        return _getData(UserApiURL+"/"+id+"/projects");
    };

    var _getProjectTasks=function(id){
        return _getData(ProjectApiURL+"/"+id+"/tasks");
    };
    var _getTask=function(id){
        return _getData(TaskApiURL+"/"+id);
    };


    pmsService.getUserData=_getUserData;
    pmsService.getProjects=_getProjects;
    pmsService.getProject=_getProject;
    pmsService.getTask=_getTask;
    pmsService.getProjectTasks=_getProjectTasks;

    return pmsService;


});

