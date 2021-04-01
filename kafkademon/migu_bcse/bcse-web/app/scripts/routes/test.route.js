/**
 * Created by Echo on 2017/2/27.
 */
/*
* 搜索测试
* @include
* 智能提示
* 测试结果
*/
'use strict';
angular.module('test',[]);

angular.module('test')
  .config(['$stateProvider', function($stateProvider) {
    //配置路由
    $stateProvider
      .state('manage.test', {
        url: '/test?appId',
        views: {
          'header': {
            templateUrl: 'views/templates/header.navbar.html'
          },
          'wrapper': {
            templateUrl: 'views/test/test.html',
            controller: 'testCtrl'
          }
        }
      });
  }]);
