/**
 * Created by Jasmin on 16-Apr-15.
 */
app.config(function ($routeProvider, $httpProvider) {

    $httpProvider.interceptors.push('myHttpInterceptor');

    $routeProvider
        .when("#",{
            redirectTo:"/login"
        }).when("/",{
            redirectTo:"/login"
        })
        .when("/dashboard",{
            templateUrl:"dashboard.html"
        })
        .when("/statistics",{
            templateUrl:"statistics.html"
        })
        .when("/messages",{
            templateUrl:"userMessages.html"
        })
        .when("/userSettings",{
            templateUrl:"userSettings.html"
        })
        .when("/dashboardAdmin",{
            templateUrl:"dashboardAdmin.html"
        })
        .when("/projects",{
            templateUrl:"projects.html"
        })
        .when("/projectDetails/:projectId",{
            templateUrl:"projectDetails.html"
        })
        .when("/tasks",{
            templateUrl:"tasks.html"
        })
        .when("/login",{
            templateUrl:"login.html"
        })
        .when("/404",{
            templateUrl:"404.html"
        })
        .when("/registration",{
            templateUrl:"registration.html"
        })
        .when("/reset",{
            templateUrl:"login.html",
            controller:"ResetController"
        })
        .otherwise({redirectTo:"/404"});
});