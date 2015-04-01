(function () {
    var url = "/PMS-NWT/rest/projects";
    var app = angular.module("Test", []);

    var ProjectController = function ($scope, $http) {

        var onProjectComplete = function (response) {
            $scope.projects = response.data;
        }

        $http.get(url).then(onProjectComplete)
        $scope.selectChanged = function () {
            $http.get("/PMS-NWT/rest/tasks").then(function (response) {
                $scope.tasks = response.data;
            });

        };
    };


    app.controller("ProjectController", ProjectController);
}())