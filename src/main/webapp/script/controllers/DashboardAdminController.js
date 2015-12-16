/**
 * Created by Jasmin on 20-Apr-15.
 */
(function () {

    var DashboardAdminController=function ($translate, $scope,$rootScope,$location,pmsService) {
        $rootScope.setHeader('DASHBOADR_LABEL');
        $rootScope.setSubHeader('DASHBOADR_LABEL');
        $rootScope.navigation.current='dashboardAdmin';
        $scope.searchParameter='';

        $scope.loadUsers=function(){
            pmsService.getUsers().success(function(data){
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
            pmsService.searchUsers($scope.searchParameter,"notDeleted").success(function(data){
                $scope.users=data;
                var i;
                for(i=0;i<$scope.users.length;i++)
                {
                    if($scope.users[i].blocked==1) $scope.users[i].tr_class='danger';
                    else $scope.users[i].tr_class='';
                }
            });
        };
        $scope.globalUnblock=function(){
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
            pmsService.unblockUsers(users).success(function(){
                $rootScope.setInfoMessage('USER_BLOCKED') ;
                var i;
                for(i=0;i<$scope.users.length;i++)
                {
                    if(userIds.indexOf($scope.users[i].id+'')>-1)
                    {
                        $scope.users[i].tr_class='';
                    }
                }
            });
        };

        $scope.globalDelete=function(){
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
            pmsService.deleteUsers(users).success(function(){
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

        $scope.globalBlock=function(){
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
            pmsService.blockUsers(users).success(function(){
                $rootScope.setInfoMessage('USER_BLOCKED') ;
                var i;
                for(i=0;i<$scope.users.length;i++)
                {
                    if(userIds.indexOf($scope.users[i].id+'')>-1)
                    {
                        $scope.users[i].tr_class='danger';
                    }
                }
            });
        };

        $scope.clear=function(){
            $scope.searchParameter='';
            $scope.loadUsers();
        };

        $scope.blockUser=function(id){
           pmsService.blockUser(id).success(function(){
               $rootScope.setInfoMessage('USER_BLOCKED') ;
               var i;
               for(i=0;i<$scope.users.length;i++)
               {
                   if($scope.users[i].id==id) $scope.users[i].tr_class='danger';
               }
           });
        };

        $scope.unblockUser=function(id){
            pmsService.unblockUser(id).success(function(){
                $rootScope.setInfoMessage('USER_UNBLOCKED') ;
                var i;
                for(i=0;i<$scope.users.length;i++)
                {
                    if($scope.users[i].id==id) $scope.users[i].tr_class='';
                }
            });
        };
        $scope.blocked_class='danger';
        $scope.unblocked_class='success';

        $scope.loadUsers();
    };

    app.controller('DashboardAdminController',DashboardAdminController );

}());