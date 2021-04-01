'use strict';

/**
 * @ngdoc overview
 * @name seApp
 * @description
 * # seApp
 *
 * Main module of the application.
 */
angular
  .module('seApp', ['ui.router', 'ngAnimate','ngResource', 'ui.bootstrap','ngFileUpload', 'toaster','angularjs-dropdown-multiselect', 'manage']);
angular
  .module('seApp')
  .config(['$urlRouterProvider',function($urlRouterProvider) {
    $urlRouterProvider.otherwise('/portal');
  }])
  .config(['$qProvider','$httpProvider', function ($qProvider, $httpProvider) {
    $qProvider.errorOnUnhandledRejections(false);
    $httpProvider.defaults.withCredentials = true;
  }])
  .run(['$rootScope','$state','Handle', function ($rootScope,$state,Handle) {
    /*$rootScope.baseIp = '10.139.4.130';
    $rootScope.port = '8080';*/
    $rootScope.system = 'api/';
    /*以资源名称为键名定义对应的URI地址*/
     $rootScope.restInterfaceUrls = {
     'login': '/login',
     'oaLogin': '/oaLogin',
     'users': '/users',
     'apps': '/apps',
     'config': '/config',
     'analyzer': '/analyzer',
     'recovery': '/recovery',
     'statistics': '/statistics',
     'suggestion': '/suggestion',
     'search': '/search',
     'evaluation': '/evaluation'
     };
    //捕捉路由变化事件
    $rootScope.$on('$stateChangeStart', function(event, toState) {
      var isNavigatingTo = toState.name;
      if(isNavigatingTo === "portal"||isNavigatingTo === "login"){
        return; // no need to redirect
      }
      var loggedIn = Handle.getCookie('secretKey');
      var role = Handle.getCookie('role');
      var denialRoutes = false;
      if(role == '1') {
        denialRoutes = Handle.userNotRoute(isNavigatingTo);
      }
      if (isNavigatingTo && isNavigatingTo!= "portal" && (!loggedIn || loggedIn==="null" || denialRoutes)) {
        $state.go('portal');
        event.preventDefault();
      }
    });
  }]);
