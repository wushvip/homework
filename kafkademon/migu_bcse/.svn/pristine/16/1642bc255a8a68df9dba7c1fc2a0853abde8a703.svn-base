/**
 * Created by Echo on 2017/2/27.
 */
/*
* 单个用户信息
* @include
* 获取单个用户信息
* 修改用户密码
*/
'use strict';
angular.module('user',[]);

angular.module('user')
  .config(['$stateProvider', function($stateProvider) {
    //配置路由
    $stateProvider
      .state('manage.user', {
        url: '/user',
        views: {
          'header': {
            templateUrl: 'views/templates/header.navbar.html'
          },
          'wrapper': {
            templateUrl: 'views/um/user.view.html',
            controller: 'userCtrl'
          }
        }
      });
  }]);
