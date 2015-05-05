/**
 * Created by Jasmin on 16-Apr-15.
 */
var token = "";

var app = angular.module('app', ['ngRoute', 'ngCookies', 'pascalprecht.translate']);

app.factory('myHttpInterceptor', function ($q, $rootScope) {
    return {
        request: function (config) {
            //alert('interceptor');
            return config || $q.when(config);
        },
        responseError: function (response) {
            alert("GreskaInterceptor");
            $rootScope.setDangerMessage(response.data.code);
            return $q.reject(response);
        }
    }
});

app.run(function ($rootScope, $location, authService) {

    var userProfile = authService.getAuthorization();

    if (userProfile) {
        $rootScope.user = {
            jwt: userProfile.jwt,
            id:userProfile.id,
            name: userProfile.name,
            isAuth: true
        };
        $location.path("dashboard");
    } else {
        $rootScope.user=null;
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
            if (!userProfile) {
                event.preventDefault();
                alert('state1');
            } else {
                alert('state2');
            }
        }
    })

});

