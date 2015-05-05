/**
 * Created by Jasmin on 20-Apr-15.
 */

app.controller('MainController', function ($translate, $scope,$rootScope,$cookieStore) {
    //NAVIGATION
    $translate.use($rootScope.currentLng);

    $rootScope.setHeader=function(header_label)
    {
        $rootScope.navigation.headerLabel=header_label;
        $rootScope.navigation.header="error";
        $translate(header_label)
            .then(function (translatedValue) {
                $rootScope.navigation.header=translatedValue;
            });
    };
    $rootScope.setFixedHeader=function(header_label)
    {
        $rootScope.navigation.header=header_label;
    };

    $rootScope.setSubHeader=function(subHeader_label)
    {
        $rootScope.navigation.subHeaderLabel=subHeader_label;
        $translate(subHeader_label)
            .then(function (translatedValue) {
                $rootScope.navigation.subHeader=translatedValue;
            });
    };
    //MESSAGE CONTROL

    $rootScope.setMessage=function(message_label,type)
    {
        $translate(message_label)
            .then(function (translatedValue) {
                $rootScope.message.text=translatedValue;
                $rootScope.message.type=type;
            });
    };

    $rootScope.setInfoMessage=function(message_label)
    {
        $("#message_panel").show();
        $rootScope.setMessage(message_label,'info');
        $rootScope.hideTimer();
    };

    $rootScope.setDangerMessage=function(message_label)
    {
        $("#message_panel").show();
        $rootScope.setMessage(message_label,'danger');
        $rootScope.hideTimer();
    };

    $rootScope.hideTimer=function(){
        $("#message_panel").fadeTo(3000, 500).slideUp(500, function(){
            $("#message_panel").hide();
            $rootScope.message={
                text:null,
                type:null
            };
        });
    };

    $scope.toggleLang = function () {
        $translate.use() == 'en' ? $translate.use('bs') : $translate.use('en');
        $cookieStore.put('currentLng',$translate.use());
        $rootScope.currentLng=$translate.use();
        $rootScope.setInfoMessage('CHANGE_LANGUAGE');

        $rootScope.setHeader($rootScope.navigation.headerLabel);
        $rootScope.setSubHeader($rootScope.navigation.subHeaderLabel);
    };
});