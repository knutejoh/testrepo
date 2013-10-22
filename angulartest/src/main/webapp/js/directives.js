'use strict';

var myApp = angular.module('LottoDirectives', []);
    
    
myApp.directive('lottomenu', function() {
    return {
        restrict: 'E',
        templateUrl: 'snippets/lottoMenu.html'
    };
});

myApp.directive('lottoheading', function() {
    return {
        restrict: 'E',
        templateUrl: 'snippets/lottoHeading.html'
    };
});