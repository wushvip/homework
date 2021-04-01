/**
 * Created by Echo on 2017/2/27.
 */
/*
* 所有信用信息
* include
* 应用管理列表
* 查询应用*/
'use strict';
angular.module('apps',[]);

angular.module('apps')
  .config(['$stateProvider', function($stateProvider) {
    //配置路由
    $stateProvider
      .state('manage.apps', {
        url: '/apps',
        views: {
          'wrapper': {
            templateUrl: 'views/apps/apps.view.html',
            controller: 'appsCtrl'
          }
        }
      });

  }]);
