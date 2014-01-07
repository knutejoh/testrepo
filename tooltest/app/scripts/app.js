'use strict';

var tooltestApp = angular.module('tooltestApp', [
  'ngRoute',
  'ngResource',
  'MainControllers'
]);

tooltestApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/plain_test.html',
        controller: 'MainCtrl'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl'
      })
      .when('/trekninger', {
        templateUrl: 'views/trekninger.html',
        controller: 'TrekningerCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  }]);