/**
 * Created by Echo on 2017/2/27.
 */
'use strict';
angular.module('manage', [ 'portal','user','users','app','apps','test','eval','create','analysis','doc']);
angular
  .module('manage')
  .config(['$stateProvider',function($stateProvider) {
    $stateProvider
      .state('manage', {
        url: '/manage',
        views: {
          'main': {
            templateUrl: 'views/main.html',
            controller: 'mainCtrl'
          },
          'header@manage': {
            templateUrl: 'views/templates/header.navbar.html'
          },
          'wrapper@manage': {
            templateUrl: 'views/apps/apps.view.html',
            controller: 'appsCtrl'
          }
        }
      });
  }]);
