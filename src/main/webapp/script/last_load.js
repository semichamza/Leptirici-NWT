/**
 * Created by Jasmin on 16-Apr-15.
 */
var token = "";

app.run(function ($rootScope, $location, $cookieStore,$translate) {


    if($cookieStore.get('currentLng'))
         $rootScope.currentLng=$cookieStore.get('currentLng');
    else
         $rootScope.currentLng='en';

});

