/**
 * Created by Jasmin on 20-Apr-15.
 */
(function () {

var BarCtrl=function ($translate, $scope,$rootScope,$location,pmsService,$translate) {
    $scope.labels = [];
    $scope.series = ['Inbox', 'Outbox'];
    $scope.selectedUser=null;
    $scope.users=[];

    pmsService.getUsers().success(function(data){
        $scope.users=data;
        $scope.selectedUser=$scope.users[0];
        $scope.data = [];

        $scope.refresh=function(){
            $scope.labels=[];
            $scope.data=[];
            var row1=[];
            var row2=[];
            pmsService.getUserMessages($scope.selectedUser.id).success(function(data){
                var messages=JSOG.decode(data);
                if(!messages || messages.length==0){
                    for(var j=0;j<$scope.users.length;j++)
                    {
                        var user=$scope.users[j];
                        $scope.labels.push(user.firstName+' '+user.lastName);
                        row1.push(0);
                    }
                }
                else for(var j=0;j<$scope.users.length;j++)
                    {
                        var user=$scope.users[j];
                        $scope.labels.push(user.firstName+' '+user.lastName);
                        var counter=0;
                        for(var i=0;i<messages.length;i++){
                            var message=messages[i];
                            if(message.sender.id==user.id)
                            {
                                counter++;
                            }
                        }
                        row1.push(counter);
                    }
                $scope.data.push(row1);
                console.log($scope.data);
            });

            pmsService.getUserSentMessages($scope.selectedUser.id).success(function(data){
                var messages=JSOG.decode(data);
                if(!messages || messages.length==0){
                    for(var j=0;j<$scope.users.length;j++)
                    {
                        row2.push(0);
                    }
                    return;
                }else
                    for(var j=0;j<$scope.users.length;j++)
                    {
                        var user=$scope.users[j];
                        var counter=0;
                        for(var i=0;i<messages.length;i++){
                            var message=messages[i];
                            if(message.receiver.id==user.id)
                            {
                                counter++;
                            }
                        }
                        row2.push(counter);
                    }
                $scope.data.push(row2);
                console.log($scope.data);

            });
        };
        $scope.refresh();
        console.log($scope.series);
    });

};
    var BarCtrlAll=function ($translate, $scope,$rootScope,$location,pmsService,$translate) {
        $scope.labels = [];
        $scope.series = ['Inbox', 'Outbox'];
        $scope.users=[];
        var tempData=[[],[]];
        var loadData=function(users,count){
            if(count>=users.length) {
                $scope.data=tempData;
                return;
            }
            var user=users[count];
            $scope.labels.push(user.firstName+' '+user.lastName);
            pmsService.getUserMessages(user.id).success(function(data) {
                var messages = data;
                tempData[0].push(messages.length);

                pmsService.getUserSentMessages(user.id).success(function(data){
                    var messages = data;
                tempData[1].push(messages.length);
                    loadData(users,count+1);
                });
            });
        };

        pmsService.getUsers().success(function(data){
            $scope.labels=[];
            $scope.data=[[],[]];
            loadData(data,0);
        });

    };
var DoughnutCtrl=function ($translate, $scope,$rootScope,$location,pmsService) {
    var projects=[];
    $scope.labels=[];
    $scope.data=[];
    $scope.selectedUser=null;
    $scope.users=[];
    pmsService.getUsers().success(function(data){
        $scope.users=data;
        $scope.selectedUser=$scope.users[1];
        $scope.refresh=function(){
            pmsService.getProjects($scope.selectedUser.id).success(function(data){
                projects=data;
                $scope.labels=[];
                $scope.data=[];
                if(!projects || projects.length==0) return;
                for(var i=0;i<projects.length;i++){
                    var project=projects[i];
                    $scope.labels.push(project.name);
                    pmsService.getProjectTasks(project.id).success(function(data) {
                        $scope.data.push(data.length);
                    });
                }
            });
        };
        $scope.refresh();
    });
};

var PieCtrl=function ($translate, $scope,$rootScope,$location,pmsService) {

    var projects=[];
    $scope.labels=[];
    $scope.data=[];
    pmsService.getAllProjects().success(function(data){
        projects=data;
        console.log(data);
        for(var i=0;i<projects.length;i++){
            var project=projects[i];
            $scope.labels.push(project.name);
            pmsService.getProjectTasks(project.id).success(function(data) {
                $scope.data.push(data.length);
            });
        }
    });

};

app.controller('BarCtrl',BarCtrl );
app.controller('BarCtrlAll',BarCtrlAll );
app.controller('DoughnutCtrl',DoughnutCtrl );
app.controller('PieCtrl',PieCtrl );

}());