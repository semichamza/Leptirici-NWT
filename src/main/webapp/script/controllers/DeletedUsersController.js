/**
 * Created by Jasmin on 20-Apr-15.
 */
(function () {

    var DeletedUsersController=function ($translate, $scope,$rootScope,$location,pmsService) {
        $rootScope.setHeader('DELETED_USERS_LABEL');
        $rootScope.setSubHeader('DELETED_USERS_LABEL');
        $rootScope.navigation.current='deletedUsers';
        $scope.searchParameter='';

        $scope.loadUsers=function(){
            pmsService.getDeletedUsers().success(function(data){
                $scope.users=data;
                var i;
                for(i=0;i<$scope.users.length;i++)
                {
                    if($scope.users[i].id==$rootScope.authData.user.id)
                        $scope.users[i].escapeClass='escape';
                    else
                        $scope.users[i].escapeClass='';

                    if($scope.users[i].blocked==1) $scope.users[i].tr_class='danger';
                    else $scope.users[i].tr_class='';
                }
            });
        };

        $scope.searchUsers=function(){
            if($scope.searchParameter==null || $scope.searchParameter=='')
            {
                $scope.clear();
                return;
            }
            pmsService.searchUsers($scope.searchParameter,"deleted").success(function(data){
                $scope.users=data;
                var i;
                for(i=0;i<$scope.users.length;i++)
                {
                    if($scope.users[i].blocked==1) $scope.users[i].tr_class='danger';
                    else $scope.users[i].tr_class='';
                }
            });
        };
        $scope.globalRevert=function(){
            var items=$('#selectedItems').text();
            var userIds=items.substr(0,items.length-1).split(',');
            if(!userIds.length>0 || !items.length>0)
                return;
            var users=[];
            for(var i=0;i<userIds.length;i++)
            {
                var id=userIds[i];
                var user={"id":id};
                users.push(user);
            }
            pmsService.revertUsers(users).success(function(){
                $rootScope.setInfoMessage('USERS_UPDATED') ;
                var i;
                for(i=0;i<$scope.users.length;i++)
                {
                    if(userIds.indexOf($scope.users[i].id+'')>-1)
                    {
                        $scope.users[i].tr_class='';
                    }
                }
                $scope.loadUsers();
            });
        };


        $scope.clear=function(){
            $scope.searchParameter='';
            $scope.loadUsers();
        };
        $scope.blocked_class='danger';
        $scope.unblocked_class='success';

        $scope.loadUsers();
    };

    app.controller('DeletedUsersController',DeletedUsersController );

}());