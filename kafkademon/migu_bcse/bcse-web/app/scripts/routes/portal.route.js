/**
 * Created by Echo on 2017/2/27.
 */
'use strict';
angular.module('portal',[]);

angular.module('portal')
.config(['$stateProvider', function($stateProvider) {
  //配置路由
  $stateProvider
    .state('portal', {
      url: '/portal',
      views: {
        'main': {
          templateUrl: 'views/portal/portal.view.html',
          controller: 'portalCtrl'
        },
        'header@portal': {
          templateUrl: 'views/templates/header.html'
        }
      }
    });
}]);
