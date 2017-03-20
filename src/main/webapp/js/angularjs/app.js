/*global angular */

/**
 * The main TodoMVC app module
 *
 * @type {angular.Module}
 */
angular.module('todomvc', ['ngRoute', 'ngResource', "ngCookies"])
    .config(function ($routeProvider) {
        'use strict';

        var routeConfig = {
            controller: 'TodoCtrl',
            templateUrl: 'todo.html',
            resolve: {
                store: function (todoStorage) {
                    // Get the correct module (API or localStorage).
                    return todoStorage.then(function (module) {
                        module.get(); // Fetch the todo records in the background.
                        return module;
                    });
                }
            }
        };

        $routeProvider
            .when('/', {templateUrl: 'home.html'})
            .when('/todo', routeConfig)
            .when('/map', {controller: 'MapCtrl', templateUrl: 'map-template'})
            .when('/login', {controller: 'oauthCtrl', templateUrl: 'login.html'})
            .when('/:status', routeConfig)
            .otherwise({
                redirectTo: '/'
            });
    })
/*    .config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.headers.common['Authorization'] = 'Bearer ' + $cookies.get("access_token");
    }])*/;
