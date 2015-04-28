/**
 * Created by Jasmin on 20-Apr-15.
 */

app.controller('MessageController', function ($translate, $scope,$rootScope,$cookieStore) {
    if($rootScope.message.text)
    {
        $rootScope.hideTimer();
    }
});