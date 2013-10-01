angular.module('restTestServices', ['ngResource']).
        factory('RestTest', function($resource) {
    return $resource('http://localhost\\:8080/resttest/resources/helloworld');
});

