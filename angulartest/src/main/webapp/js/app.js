'use strict';

angular.module('helloworld', ['restTestServices', 'restTestControllers', 'lottoServices', 'UserServices', 'LottoDirectives'])


    .config(function($routeProvider) {
         $routeProvider.
            when('/', {controller:'WelcomeController', templateUrl:'templates/welcome.html'}).
            when('/bosses/', {controller:'RestController', templateUrl:'templates/sjefen.html'}).
            when('/lotto/', {controller:'LottoController', templateUrl:'templates/lotto_main.html'}).
            when('/lotto/trekninger', {controller:'LottoController', templateUrl:'templates/lotto_trekninger.html'}).
            when('/lotto/kupong', {controller:'LottoController', templateUrl:'templates/lotto_main.html'}).
            when('/lotto/minside', {controller:'LottoController', templateUrl:'templates/lotto_main.html'}).
            when('/lotto/admin', {controller:'LottoController', templateUrl:'templates/lotto_admin.html'}).
            when('/phones/', {controller:'PhoneListCtrl', templateUrl:'templates/phones.html'}).
            when('/login/', {controller:'LogInController', templateUrl:'templates/login.html'}).
            otherwise({redirectTo:'/'});

    })

//    .run(function($timeout, UserService) {
//        console.log('starting run');
//        $timeout(function() {
//            UserService.timeout();
//            console.log('Timer started');
//        }, 3000);
//    })
;