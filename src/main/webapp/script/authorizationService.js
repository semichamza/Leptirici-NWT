/**
 * Created by Jasmin on 16-Apr-15.
 */
app.service('authService', function ($http, $rootScope,$resource, $cookieStore,$location) {
    var authService = {};
    var apiURL = "/PMS-NSI/auth";

    var _setAuthorization = function (authData) {
        $rootScope.authData=authData;
        $cookieStore.put('authData', authData);
    };

    var _getAuthorization = function () {
        return $cookieStore.get('authData');
    };

    var _postData = function (path, data) {
        return $http.post(apiURL + path, data, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
    };
    var _putData = function (path, data) {
        return $http.put(apiURL + path, data, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
    };

    var _login = function (account) {
        return _postData('/login', account).success(function (data) {
            var authData = {
                jwt: data.jwt,
                user:data.user
            };
            $rootScope.showTabs(authData.user);
            _setAuthorization(authData);
        });
    };


    var _logout = function () {
        $cookieStore.remove('authData');
        $rootScope.authData=null;
        $location.path("/login");
    };

    var _register = function (newUser) {
        return _postData('/user/register', newUser).success(function(data){

        });
    };

    var _reset=function(username)
    {
        return _putData('/user/reset',username);
    };
    authService.reset=_reset;
    authService.setAuthorization = _setAuthorization;
    authService.getAuthorization = _getAuthorization;
    authService.postData = _postData;
    authService.logout = _logout;
    authService.login = _login;
    authService.register=_register;

    $rootScope.$watch(function() { return $location.path(); }, function(newValue, oldValue){
        if (!_getAuthorization() && $location.path() != '/login' && $location.path() != '/reset' && $location.path() != '/registration'){
            $location.path('/login');
        }
        else if(_getAuthorization() && ($location.path() == '/login' || $location.path() == '/registration')){
            $location.path('/dashboard');
        }
    });

    return authService;

});

