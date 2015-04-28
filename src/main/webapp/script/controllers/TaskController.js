/**
 * Created by Jasmin on 20-Apr-15.
 */

app.controller('TaskController', function ($translate, $scope,$rootScope,$cookieStore) {
    $rootScope.setHeader('TASK_LABEL');
    $rootScope.setSubHeader('TASK_LABEL');
    $rootScope.navigation.current='tasks';
});