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
            if (response.status === 401) {
                $rootScope.logout();
            }
            alert('interceptorError');
        }
    }
});

app.run(function ($rootScope, $location, authService) {

    var userProfile = authService.getAuthorization();

    if (userProfile) {
        $rootScope.user = {
            jwt: userProfile.jwt,
            name: userProfile.name
        };
    } else {
        $rootScope.user = {isAuth: false};
    }

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

