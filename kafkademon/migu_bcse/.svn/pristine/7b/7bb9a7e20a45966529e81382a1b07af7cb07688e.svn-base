/**
 * Created by Echo on 2017/3/2.
 */
'use strict';
angular.module('create',[]);

angular.module('create')
  .config(['$stateProvider', function($stateProvider) {
    //配置路由
    $stateProvider
      .state('manage.create', {
        url: '/create',
        views: {
          'wrapper': {
            templateUrl: 'views/create/create.html',
            controller: 'createCtrl'
          },
          'content@manage.create': {
            templateUrl: 'views/create/basic.html'
          }
        }
      })
      .state('manage.create.struct', {
        url: '/:appId/struct',
        views: {
          'content': {
            templateUrl: 'views/create/struct.html',
            controller: 'structCtrl'
          },
          'pic-slide@manage.create.struct': {
            templateUrl: 'views/create/struct.slide.html'
          }
        }
      })
      .state('manage.create.confirm', {
        url: '/:appId/confirm',
        views: {
          'content': {
            templateUrl: 'views/create/confirm.html',
            controller: 'confirmCtrl'
          }
        }
      })
      .state('manage.create.finish', {
        url: '/:appId/finish',
        views: {
          'content': {
            templateUrl: 'views/create/finish.html',
            controller: 'finishCtrl'
          }
        }
      });
  }]);
