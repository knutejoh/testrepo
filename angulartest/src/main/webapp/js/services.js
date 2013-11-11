'use strict';

angular.module('restTestServices', ['ngResource']).
        factory('RestTest', function($resource) {
    return $resource('http://localhost\\:8080/resttest/resources/helloworld');
});

angular.module('lottoServices', ['ngResource'])
    .factory('DrawListService', function($resource) {
        return $resource('resources/lotto/:area/:methodname', {}, {
            updateDrawList: {method:'GET', params: {area:'admin', methodname: 'updatedrawlist'}},
            getAllDraws: {method:'GET', params: {area:'drawlist', methodname: ''}, isArray:true},
            getAllDrawsSmart: {method:'GET', params: {area:'drawlistsmart', methodname: ''}, isArray:false},
            getDrawDetails: {method:'GET', params: {area:'drawinfo'}},
            getMapTest: {method:'GET', params: {area:'maptest'}}
        });
 });
 
 
var userServices = angular.module('UserServices', ['ngResource']);

userServices.factory('AuthService', function($resource) {
    return $resource('resources/auth/:methodname', {}, {
        login: {method:'POST', params:{methodname:'login'}},
        logout: {method:'GET', params:{methodname:'logout'}},
        register: {method:'PUT', params:{methodname:'register'}},
        ping: {method:'GET', params:{methodname:'ping'}},
        timeout: {method:'GET', params:{methodname:'timeout'}}  
    });
 });
 
 userServices.factory('UserService', function($window, $timeout, AuthService) {
     
     var user = {
         data: null,
         isLoggedIn : false
         
     };
     
     var returnObject = {
         timeout: function() {
            AuthService.timeout(function(output) {
                console.log('Timeout response: '+ output.message);
            });
            $timeout(returnObject.timeout, 30000);
         },
         setLoggedIn: function(loggedIn) {
             user.isLoggedIn = loggedIn;
             $window.sessionStorage.setItem('loggedin', loggedIn);
         },
         isLoggedIn: function() {
             if ($window.sessionStorage.getItem('loggedin') !== null) {
                 user.isLoggedIn = $window.sessionStorage.getItem('loggedin');
             };
             return isLoggedIn;
         },
         setUserData: function(data) {
            $window.sessionStorage.setItem('userdata', angular.toJson(data));
            console.log('Setter data til ' + angular.toJson(data));
            user.data = data;
         },
         getUserData: function() {
            if ($window.sessionStorage.getItem('userdata') !== null) {
                user.data = angular.fromJson($window.sessionStorage.getItem('userdata'));
            }
            console.log('Er i getUserData og returnerer: ', user);
            return user;
         }
         
     };
     return returnObject;
     
     
 });