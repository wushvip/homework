/**
 * Created by Echo on 2017/2/27.
 */
/*
 * 单个应用涉及的信息
 * @include
 * 创建单个应用
 * 单个应用信息查询
 */
'use strict';
angular.module('app',[]);

angular.module('app')
  .config(['$stateProvider', function($stateProvider) {
    //配置路由
    $stateProvider
      .state('manage.app', {
        url: '/app?appId&userId',
        views: {
          'wrapper': {
            templateUrl: 'views/app/app.html',
            controller: 'appCtrl'
          },
          'left-bar@manage.app': {
            templateUrl: 'views/app/left.bar.html'
          }
        },
        resolve: {
          appInfo: ['$injector','$stateParams', function($injector,$stateParams) {
            var appId=$stateParams.appId;
            var appUserId=$stateParams.userId;
            return $injector.get('restService').promiseRequest($injector.get('restUrl').getUrl('apps')+'/:appId/basicInfo','GET',{appId:appId},{userId: appUserId});
          }]
        }
      })
      .state('manage.app.sample', {
        url: '/sample',
        views: {
          /*'left-bar': {
            templateUrl: ''
          },*/
          'right-content': {
            templateUrl: 'views/templates/index.sample.html',
            controller: 'indexSampleCtrl'
          }
        }
      })
      .state('manage.app.basic', {
        url: '/basic',
        views: {
          'right-content': {
            templateUrl: 'views/app/info/basic.html'
          }
        }
      })
      .state('manage.app.struct', {
        url: '/struct',
        views: {
          'right-content': {
            templateUrl: 'views/app/info/struct.html'
          },
          '@manage.app.struct': {
            templateUrl: 'views/templates/struct.review.html'
          }
        }
      })
      .state('manage.app.index', {
        url: '/index',
        views: {
          'right-content': {
            templateUrl: 'views/app/index/index.html',
            controller: 'appIndexCtrl'
          }
        }
      })
      .state('manage.app.order', {
        url: '/order',
        views: {
          'right-content': {
            templateUrl: 'views/app/config/order/order.html',
            controller: 'configOrderCtrl'
          }
        }
      })
      .state('manage.app.dic', {
        url: '/dic',
        views: {
          'right-content': {
            templateUrl: 'views/app/config/dic/dic.html',
            controller: 'configDicCtrl'
          }
        }
      })
      .state('manage.app.suggestion', {
        url: '/suggestion',
        views: {
          'right-content': {
            templateUrl: 'views/app/config/suggestion/suggestion.html',
            controller: 'configSuggestCtrl'
          }
        }
      })
      .state('manage.app.shield', {
        url: '/shield',
        views: {
          'right-content': {
            templateUrl: 'views/app/config/shield/shield.html',
            controller: 'configShieldCtrl'
          }
        }
      })
      .state('manage.app.spread', {
        url: '/spread',
        views: {
          'right-content': {
            templateUrl: 'views/app/config/spread/spread.html',
            controller: 'configSpreadCtrl'
          }
        }
      })
      .state('manage.app.facet', {
        url: '/facet',
        views: {
          'right-content': {
            templateUrl: 'views/app/config/facet/facet.html',
            controller: 'configFacetCtrl'
          }
        }
      })
      .state('manage.app.analyzier', {
        url: '/analyzier',
        views: {
          'right-content': {
            templateUrl: 'views/app/analyzer/analyzer.html',
            controller: 'configAnalyzerCtrl'
          }
        }
      })
      .state('manage.app.recovery', {
        url: '/recovery',
        views: {
          'right-content': {
            templateUrl: 'views/app/recovery/recovery.html',
            controller: 'configRecoveryCtrl'
          }
        }
      })
      .state('manage.app.error', {
        url: '/error',
        views: {
          'right-content': {
            templateUrl: 'views/app/statistics/error/error.html',
            controller: 'errorLogCtrl'
          }
        }
      })
      .state('manage.app.hot', {
        url: '/hot',
        views: {
          'right-content': {
            templateUrl: 'views/app/statistics/hot/hot.html',
            controller: 'hotWordCtrl'
          }
        }
      })
      .state('manage.app.analysis', {
        url: '/analysis',
        views: {
          'right-content': {
            templateUrl: 'views/app/statistics/analysis/analysis.html',
            controller: 'appAnalysisCtrl'
          }
        }
      });

  }]);
