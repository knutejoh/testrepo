'use strict';

angular.module('helloworld', ['restTestServices', 'restTestControllers', 'ui.bootstrap'])


    .config(function($routeProvider) {
         $routeProvider.
            when('/', {controller:'RestController', templateUrl:'templates/sjefen.html'}).
            when('/test/', {controller:'PhoneListCtrl', templateUrl:'templates/phones.html'}).
            otherwise({redirectTo:'/'});

    })


;