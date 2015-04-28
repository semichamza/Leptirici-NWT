/**
 * Created by Jasmin on 20-Apr-15.
 */

app.controller('ProjectController', function ($translate, $scope,$rootScope,$cookieStore) {
    $rootScope.setHeader('PROJECT_LABEL');
    $rootScope.setSubHeader('PROJECT_LABEL');
    $rootScope.navigation.current='projects';


});