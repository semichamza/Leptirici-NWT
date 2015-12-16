(function () {
    var url = "/PMS-NSI/rest/projects";
    var app = angular.module("Test", ['ngCookies']);

    var ProjectController = function ($scope, $http, $cookieStore) {
        $scope.isAutorized = $cookieStore.get("auth");
        $scope.loginUsername = $cookieStore.get("username");
        var onProjectComplete = function (response) {
            $scope.projects = response.data;
        };

        $scope.selectChanged = function () {
            $http({
                url: '/PMS-NSI/rest/tasks',
                method: "GET",
                data: {},
                headers: {
                    'Authorization': $cookieStore.get("jwt"),
                    'Author': 'testawd',
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).success(function (data, status, headers, config) {
                $scope.tasks = data;
            }).error(function (data, status, headers, config) {
                $scope.status = status;
            });
        };
        $scope.login = function () {
            //$http.get("/PMS-NWT/rest/tasks").then(function (response) {
            //    $scope.tasks = response.data;
            //});

            $http({
                url: '/PMS-NSI/auth/login',
                method: "POST",
                data: {'username': $scope.loginUsername, 'password': $scope.loginPassword},
                headers: {'Author': 'testawd', 'Accept': 'application/json', 'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
                $cookieStore.put('jwt', data.jwt);
                $scope.isAutorized = data.isAutorized;
                $scope.message = data.message;
                $cookieStore.put('auth', data.isAutorized);
                $cookieStore.put('username', $scope.loginUsername);
                getProjects();
            }).error(function (data, status, headers, config) {
                $scope.message = data.message;
            });
        };

        $scope.login = function () {
            //$http.get("/PMS-NWT/rest/tasks").then(function (response) {
            //    $scope.tasks = response.data;
            //});

            $http({
                url: '/PMS-NSI/auth/login',
                method: "POST",
                data: {'username': $scope.loginUsername, 'password': $scope.loginPassword},
                headers: {'Author': 'testawd', 'Accept': 'application/json', 'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
                $cookieStore.put('jwt', data.jwt);
                $scope.isAutorized = data.isAutorized;
                $scope.message = data.message;
                $cookieStore.put('auth', data.isAutorized);
                $cookieStore.put('username', $scope.loginUsername);
                getProjects();
            }).error(function (data, status, headers, config) {
                $scope.message = data.message;
            });
        };

        $scope.register = function () {
            //$http.get("/PMS-NWT/rest/tasks").then(function (response) {
            //    $scope.tasks = response.data;
            //});
            var regData = {
                'name': $scope.newFirstName,
                'lastname': 'prezime',
                'username': $scope.newUsername,
                'email': $scope.newEmail,
                'password': $scope.newPassword
            };
            $http({
                url: '/PMS-NSI/auth/user/register',
                method: "POST",
                data: regData,
                headers: {'Accept': 'application/json', 'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
                alert("Mail poslan");
            }).error(function (data, status, headers, config) {
                $scope.message = data.message;
            });
        };

        var getProjects = function () {
            $http({
                url: '/PMS-NSI/rest/projects',
                method: "GET",
                data: {},
                headers: {
                    'Authorization': $cookieStore.get("jwt"),
                    'Author': 'testawd',
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).success(function (data, status, headers, config) {
                $scope.projects = data;
            }).error(function (data, status, headers, config) {
                $scope.status = status;
            });
        };

        $scope.createProject = function () {
            $http({
                url: '/PMS-NSI/rest/projects',
                method: "POST",
                data: {"name": $scope.projectName},
                headers: {
                    'Authorization': $cookieStore.get("jwt"),
                    'Author': 'testawd',
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).success(function (data, status, headers, config) {
                var tmpId = $scope.currentProject;
                $scope.currentProject = tmpId;

                $http({
                    url: url,
                    method: "GET",
                    data: {},
                    headers: {
                        'Authorization': $cookieStore.get("jwt"),
                        'Author': 'testawd',
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    }
                }).success(function (data, status, headers, config) {
                    $scope.projects = data;
                }).error(function (data, status, headers, config) {
                    $scope.status = status;
                });

            }).error(function (data, status, headers, config) {
                $scope.status = status;
            });
        };

        $scope.logout = function (message) {
            $cookieStore.remove('auth');
            $cookieStore.remove('username');
            $scope.isAutorized = null;
            $scope.loginUsername = null;
            $scope.loginPassword = null;
            $scope.message = message;
        };
        if ($scope.isAutorized) {
            getProjects();
        }

        $scope.reset = function () {
            var reqData = {
                'username': $scope.resetUsername
            };

            $http({
                url: '/PMS-NSI/auth/user/reset',
                method: "PUT",
                data: reqData,
                headers: {'Accept': 'application/json', 'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
                alert("Mail poslan");
            }).error(function (data, status, headers, config) {
                $scope.message = data.message;
            });
        }
    };


    app.controller("ProjectController1", ProjectController);

}())