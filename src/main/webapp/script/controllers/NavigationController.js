/**
 * Created by Jasmin on 16-Apr-15.
 */

app.controller('NavigationController',function($rootScope,$scope,$location,authService, $translate,$cookieStore)
{

    if(!authService.getAuthorization())
    {
        $location.path('login');

    }

    $scope.logout=function() {
        authService.logout();
    };

    $scope.isCurrent=function(tab) {
        if(tab==$rootScope.navigation.current)
            return 'active';
        return '';
    };

});