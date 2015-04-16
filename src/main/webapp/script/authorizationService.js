/**
 * Created by Jasmin on 16-Apr-15.
 */
app.service('authService', function ($http, $rootScope, $cookieStore) {
    var authService = {};
    var apiURL = "/PMS-NWT/auth";

    var _setAuthorization = function (userProfile) {
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

    var _login = function () {
        _postData('/login', account).success(function (data) {
            var userProfile = {
                jwt: data.jwt,
                name: data.name
            };

            _setAuthorization(userProfile);
            // $rootScope.showDashboard();
            //$state.go("404");
        }).error(function (error) {
            alert('_loginError');
        });
    };

    var _logout = function () {
        $cookieStore.remove('userProfile');
    };

    authService.setAuthorization = _setAuthorization;
    authService.getAuthorization = _getAuthorization;
    authService.postData = _postData;
    authService.logout = _logout;

    return authService;

});
