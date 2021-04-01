/**
 * Created by Echo on 2017/2/27.
 */
'use strict';
angular.module('eval',[]);

angular.module('eval')
  .config(['$stateProvider', function($stateProvider) {
    //配置路由
    $stateProvider
      .state('manage.eval', {
        url: '/eval',
        views: {
          'wrapper': {
            templateUrl: 'views/eval/eval.html',
            controller: 'evalCtrl'
          },
          'content@manage.eval': {
            templateUrl: 'views/eval/basic.html'
          }
        }
      })
      .state('manage.eval.sample', {
        url: '/sample',
        views: {
          'wrapper@manage': {
            templateUrl: 'views/eval/sample.html'
          }
        }
      })
      .state('manage.eval.corpus', {
        url: '/corpus',
        views: {
          'content': {
            templateUrl: 'views/eval/corpus.html',
            controller: 'corpusCtrl'
          }
        }
      })
      .state('manage.eval.result', {
        url: '/result',
        views: {
          'content': {
            templateUrl: 'views/eval/result.html',
            controller: 'resultCtrl'
          }
        }
      });
  }]);
