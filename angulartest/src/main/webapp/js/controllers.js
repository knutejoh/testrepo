'use strict';

var controllers = angular.module('restTestControllers', []);

controllers.controller('RestController', function($scope, RestTest) {
    RestTest.get(function(sjefen) {
        $scope.navn = sjefen.name;
        $scope.alder = sjefen.age;
        $scope.addresse = sjefen.address;
    });
    $scope.tull = "TULLBALL";
});



controllers.controller('PhoneListCtrl', function($scope) {
    $scope.phones = [
        {"name": "Nexus S",
            "snippet": "Fast just got faster with Nexus S."},
        {"name": "Motorola XOOM™ with Wi-Fi",
            "snippet": "The Next, Next Generation tablet."},
        {"name": "MOTOROLA XOOM™",
            "snippet": "The Next, Next Generation tablet."}
    ];
});


controllers.controller('LottoController', function($scope, DrawListService, UserService, $location) {
    $scope.lotto = {};
    $scope.lotto.currentdraw = {};
    $scope.draws = [];
    $scope.smartdraws = {
        MONTH_TABLE: new Array("Januar", "Februar", "Mars", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Desember"),
        DAY_TABLE: new Array("S&oslash;", "Ma", "Ti", "On", "To", "Fr", "L&oslash;"),
        YEAR_TABLE : new Array(),
        draws : [],
        currentYear : "2013"
    };
    
    $scope.showYear = function(year, $event) {
        console.log('Changing to year..' + year);
        $scope.smartdraws.currentYear = year;
        $event.stopPropagation();
        
    };
    
    $scope.getDrawsSmart = function() {
        
            DrawListService.getAllDraws(function(draws) {
                for (var i = 0; i < draws.length; i++) {
                    var dateArgs = draws[i].drawDate.split("-");
                    if ($.inArray(dateArgs[0], $scope.smartdraws.YEAR_TABLE) === -1) {
                        $scope.smartdraws.YEAR_TABLE.push(dateArgs[0]);
                    }
                }
                $scope.smartdraws.YEAR_TABLE.sort();
                $scope.smartdraws.YEAR_TABLE.reverse();
                $scope.smartdraws.currentYear = $scope.smartdraws.YEAR_TABLE[0];
                console.log($scope.smartdraws.YEAR_TABLE);
                $scope.smartdraws.draws = draws;
            });
        
        
    };
    
    $scope.getDraws = function() {
        DrawListService.getAllDraws(function(draws) {
//            console.log(lottoResult);
            $scope.draws = draws;
        });
    };
    
    $scope.retrieveLottoDrawings = function() {
        DrawListService.updateDrawList(function(status) {
            console.log(status);
        }, function(status) {
            console.log(status);
        });
    };

    $scope.getDrawDetails = function(ID) {
        DrawListService.getDrawDetails({methodname:ID}, function(status) {
            
            console.log(status);
            $scope.lotto.currentdraw = status;
        }, function(status) {
            console.log(status);
        });
    };


    $scope.changeView = function(view) {
        console.log('Moving to' + view);
        $location.path('lotto/' + view); // path not hash
    };
    
    $scope.userInfo = UserService.getUserData();
});

controllers.controller('LogInController', function($scope, AuthService, UserService) {


    $scope.login = function(user) {
        console.log("Vi kaller når vi trykker knappen!! :D" + user.name);

        var mellomBruker = {};

        mellomBruker.username = user.name;
        mellomBruker.password = user.password;

        var newUser = new AuthService(mellomBruker);
        newUser.$login({}, function(loggedInUser) {
            console.log('Login gikk bra!!');
            UserService.setLoggedIn(true);
            UserService.setUserData(loggedInUser);
            console.log(UserService.getUserData());
        }, function(dynge) {
            console.log('gikk dynge');
        });
    };


    $scope.isLoggedIn = UserService.isLoggedIn();
    $scope.userInfo = UserService.getUserData();


    $scope.isUnchanged = function(user) {
        return angular.equals(user, $scope.master);
    };

    $scope.logout = function() {
        var status = AuthService.logout({}, function() {
            UserService.setLoggedIn(false);
            UserService.setUserData(null);
        });
        console.log(status);
    };
});
