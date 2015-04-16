/**
 * Created by Jasmin on 16-Apr-15.
 */
app.config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

    $httpProvider.interceptors.push('myHttpInterceptor');

    $urlRouterProvider.otherwise('/404');
    $urlRouterProvider.when('', '/login');
    $urlRouterProvider.when('/', '/login');

    $stateProvider
        .state('dashboard', {
            url: '/dashboard',
            templateUrl: 'dahsboard.html',
            access: {
                requiresLogin: false
            }
        })
        .state('login', {
            url: '/login',
            templateUrl: 'login.html',
            access: {
                requiresLogin: false
            }
        })
        .state('404', {
            url: '/404',
            templateUrl: '404.html',
            access: {
                requiresLogin: false
            }
        });

});