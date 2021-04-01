/**
 * Created by Echo on 2017/2/27.
 */
/*
* 所有用户信息
* @include
* 获取用户管理列表
* 重置用户密码
* 更改用户状态
*/
'use strict';
angular.module('users',[]);

angular.module('users')
  .config(['$stateProvider', function($stateProvider) {
    //配置路由
    $stateProvider
      .state('manage.users', {
        url: '/users',
        views: {
          'header': {
            templateUrl: 'views/templates/header.navbar.html'
          },
          'wrapper': {
            templateUrl: 'views/um/users.view.html',
            controller: 'usersCtrl'
          }
        }
      });
  }]);
