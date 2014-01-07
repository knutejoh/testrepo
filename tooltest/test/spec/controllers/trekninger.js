'use strict';

describe('Controller: TrekningerCtrl', function () {

  // load the controller's module
  beforeEach(module('tooltestApp'));

  var TrekningerCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    TrekningerCtrl = $controller('TrekningerCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
