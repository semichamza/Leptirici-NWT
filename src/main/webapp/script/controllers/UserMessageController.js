/**
 * Created by Jasmin on 20-Apr-15.
 */
(function () {

    var UserMessageController=function ($translate, $scope,$resource,$rootScope,$location,pmsService) {
        $rootScope.setHeader('MESSAGES');
        $rootScope.setFixedSubHeader('');
        $rootScope.navigation.current='dashboard';
        $scope.messageText='';$scope.sentMessages=[];
        $scope.loadUsers=function(){
            pmsService.getUsers().success(function(data){
                var i;
                $scope.users=data;
                for(i=0;i<$scope.users.length;i++)
                {
                    if($scope.users[i].id==$rootScope.authData.user.id)
                    {
                        $scope.users.splice( i, 1 );
                    }
                }
                $scope.receiver=$scope.users[0];
            });
                   };
        $scope.loadUsers();
        $scope.sendMessage=function(){
            pmsService.sendMessage($rootScope.authData.user,$scope.receiver,$scope.messageText).success(
              function(data){
                   $rootScope.setInfoMessage("MESSAGE_SENT");
                  $scope.getSentMessages();
              });
            $scope.receiver=$scope.users[0];
            $scope.messageText='';

        };
        $scope.getAllMessages=function()
        {
            pmsService.getUserMessages($rootScope.authData.user.id).success(function(data){
                $scope.messages=JSOG.decode(data);
                console.log($scope.messages);
            });
        };

        $scope.getSentMessages=function()
        {
            pmsService.getUserSentMessages($rootScope.authData.user.id).success(function(data){
                $scope.sentMessages=JSOG.decode(data);
                console.log($scope.sentMessages);
            });
        };
        $scope.readAllMessages=function(){
            pmsService.readAllMessages($rootScope.authData.user.id);
        };

        $scope.getAllMessages();
        $scope.readAllMessages();
        $scope.getSentMessages();
    };

        app.controller('UserMessageController',UserMessageController );

}());