'use strict';

angular.module('restTestControllers', []).
    controller('RestController', function($scope, RestTest) {
    //    $scope.sjefen = {};
        RestTest.get(function(sjefen) {
            $scope.navn = sjefen.name;
            $scope.alder = sjefen.age;
            $scope.addresse = sjefen.address;
        });
        $scope.tull = "TULLBALL";
    })
    
            
            
    .controller('PhoneListCtrl', function($scope) {
        $scope.phones = [
            {"name": "Nexus S",
                "snippet": "Fast just got faster with Nexus S."},
            {"name": "Motorola XOOM™ with Wi-Fi",
                "snippet": "The Next, Next Generation tablet."},
            {"name": "MOTOROLA XOOM™",
                "snippet": "The Next, Next Generation tablet."}
        ];
    });

//function PhoneListCtrl($scope) {
//    $scope.phones = [
//        {"name": "Nexus S",
//            "snippet": "Fast just got faster with Nexus S."},
//        {"name": "Motorola XOOM™ with Wi-Fi",
//            "snippet": "The Next, Next Generation tablet."},
//        {"name": "MOTOROLA XOOM™",
//            "snippet": "The Next, Next Generation tablet."}
//    ];
//}
//
//
//function RestController($scope, RestTest) {
////    $scope.sjefen = {};
//    RestTest.get(function(sjefen) {
//        $scope.navn = sjefen.name;
//        $scope.alder = sjefen.age;
//        $scope.addresse = sjefen.address;
//    });
//
//
//
//
//    $scope.tull = "TULLBALL";
//
//}
//
////RestController.$inject = ['$scope', 'RestTest'];