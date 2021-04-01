/**
 * Created by Echo on 2017/3/4.
 */
/*
 * 搜索测试
 * @include
 * 智能提示
 * 测试结果
 */
'use strict';
angular.module('analysis',[]);

angular.module('analysis')
  .config(['$stateProvider', function($stateProvider) {
    //配置路由
    $stateProvider
      .state('manage.analysis', {
        url: '/analysis',
        views: {
          'wrapper': {
            templateUrl: 'views/analysis/analysis.html',
            controller: 'analysisCtrl'
          }
        },
        'resolve': {
          apps: ['$injector','$rootScope','Handle', function($injector,$rootScope,Handle) {
            var userId = Handle.getCookie('userId');
            return $injector.get('restService').promiseRequest($injector.get('restUrl').getUrl('apps'),'GET',{},{userId: userId,pageNum:-1});
          }]
        }
      });
  }]);
