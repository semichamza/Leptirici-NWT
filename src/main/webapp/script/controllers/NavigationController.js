/**
 * Created by Jasmin on 16-Apr-15.
 */

app.controller('NavigationController',function($rootScope,$scope,$location,authService, pmsService,$cookieStore)
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

    $scope.getUnreadMessages=function(){
        pmsService.getUserUnreadMessages($rootScope.authData.user.id).success(function(data) {
            $rootScope.unreadMessages=data;
        });
    };

    $scope.getUnreadMessages();
});