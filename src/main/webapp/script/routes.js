/**
 * Created by Jasmin on 16-Apr-15.
 */
app.config(function ($routeProvider, $httpProvider) {

    $httpProvider.interceptors.push('myHttpInterceptor');

    $routeProvider
        .when("",{
            redirectTo:"/login"
        }).when("/",{
            redirectTo:"/login"
        })
        .when("/dashboard",{
            templateUrl:"dahsboard.html"
        })
        .when("/login",{
            templateUrl:"login.html"
        })
        .when("/404",{
            templateUrl:"404.html"
        })
        .otherwise({redirectTo:"/404"});
});