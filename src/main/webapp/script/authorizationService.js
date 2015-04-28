/**
 * Created by Jasmin on 16-Apr-15.
 */
app.service('authService', function ($http, $rootScope, $cookieStore,$location) {
    var authService = {};
    var apiURL = "/PMS-NWT/auth";

    var _setAuthorization = function (userProfile) {
        $rootScope.user=userProfile;
        $cookieStore.put('userProfile', userProfile);
    };

    var _getAuthorization = function () {
        return $cookieStore.get('userProfile');
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
            var userProfile = {
                jwt: data.jwt,
                name: data.name
            };
            _setAuthorization(userProfile);
        });
    };

    var _logout = function () {
        $cookieStore.remove('userProfile');
        $rootScope.user=null;
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

