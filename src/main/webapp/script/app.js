/**
 * Created by Jasmin on 16-Apr-15.
 */
var token = "";

var app = angular.module('app', ['ngRoute', 'ngCookies', 'pascalprecht.translate','ui.bootstrap','ngResource','chart.js']);

app.factory('myHttpInterceptor', function ($q, $rootScope) {
    return {
        request: function (config) {
            //alert('interceptor');
            return config || $q.when(config);
        },
        responseError: function (response) {
            $rootScope.setDangerMessage(response.data.code);
            return $q.reject(response);
        }
    }
});
app.directive("fileread", [function () {
    return {
        scope: {
            fileread: "="
        },
        link: function (scope, element, attributes) {
            element.bind("change", function (changeEvent) {
                var reader = new FileReader();
                reader.onload = function (loadEvent) {
                    scope.$apply(function () {
                        scope.fileread = loadEvent.target.result;
                    });
                }
                reader.readAsDataURL(changeEvent.target.files[0]);
            });
        }
    }
}]);
app.run(function ($rootScope, $location, authService) {

    var authData = authService.getAuthorization();
    $rootScope.authData = {
        jwt: null,
        user: {
            "id": '',
            "userPrincipal": {
                "username": "",
                "passwordHash": "",
                "userRole": ""
            },
            "firstName": "",
            "lastName": "",
            "active": "",
            "blocked": "",
            "email": ""
        }
    };
    $rootScope.unreadMessages=[];

    $rootScope.showTabs=function(user){
        $rootScope.tabs={
            dashboard:user.userPrincipal.userRole=='NORMAL',
            projects:user.userPrincipal.userRole=='NORMAL',
            tasks:user.userPrincipal.userRole=='NORMAL',
            dashboardAdmin:user.userPrincipal.userRole=='ADMINISTRATOR',
            configAdmin:user.userPrincipal.userRole=='ADMINISTRATOR',
            deletedUsers:user.userPrincipal.userRole=='ADMINISTRATOR',
            statistics:user.userPrincipal.userRole=='ADMINISTRATOR'
        };
    };

    if (authData) {
        $rootScope.authData = {
            jwt: authData.jwt,
            user:authData.user
        };
        $rootScope.showTabs($rootScope.authData.user);
        $location.path("dashboard");
    } else {
        $rootScope.authData=null;
    }

    $rootScope.message={
        text:null,
        type:null
    };

    $rootScope.navigation={
        current:'dashboard',
        headerLabel:'DASHBOADR_LABEL',
        subHeaderLabel:'DASHBOADR_LABEL',
        header:'',
        subHeader:''
    };
   /* $rootScope.setCurrentTab=function(tab){
        var isDashboard='';
        if(tab=='dashboard')
            isDashboard='active';
        $rootScope.navigation={
            isDashboard:isDashboard
        };
    };*/
    //$rootScope.logout = function () {
    //    authService.logout();
    //    $rootScope.user = {isAuth: false};
    //    $state.go("login");
    //}

    $rootScope.$on("$stateChangeStart", function (event, toState) {
        if (toState.access.requiresLogin) {
            if (!authData) {
                event.preventDefault();
                alert('state1');
            } else {
                alert('state2');
            }
        }
    });

    $rootScope.closeModal=$(function(modalId){
        $('#closeModal').click(function(){
            $('#'+modalId).modal('hide');
        });
    });
});

