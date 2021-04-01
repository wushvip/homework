/**
 * Created by Echo on 2017/3/4.
 */
'use strict';
angular.module('apps')
  .controller('finishCtrl', ['$scope', '$state', function ($scope, $state) {
    /* scope function defined*/
    $scope.finish = function() {
      $state.go('manage.apps');
    };

    /*控制步骤显示*/
    $scope.nav.steps[0]['passed'] = true;
    $scope.nav.steps[2]['passed'] = true;
    $scope.nav.steps[4]['passed'] = true;
    $scope.nav.steps[6]['passed'] = false;
    $scope.nav.steps[6]['actived'] = true;

  }]);
