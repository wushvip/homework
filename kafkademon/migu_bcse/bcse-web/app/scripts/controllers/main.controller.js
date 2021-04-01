/**
 * @ngdoc function
 * @name seApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the seApp
 */
'use strict';
angular.module('seApp')
  .controller('mainCtrl', [ '$scope','$rootScope','$state', 'Messages', 'toaster','Handle', function ($scope,$rootScope,$state, Messages, toaster, Handle) {
    $scope.pubObj = {
      navs: [{
          state: 'manage.apps',
          title: '应用管理'
        },
        {
          state: 'manage.analysis',
          title: '数据分析'
        },
        {
          state: 'manage.test',
          title: '搜索测试'
        },
        {
          state: 'manage.eval',
          title: '评测体系'
        },
        {
          state: 'manage.doc',
          title: '文档与支持'
        }],
      page: {
        currentPage:1,
        inputCurPage: 1,
        totalItems: 0,
        numPerPage: 10,
        maxSize: 5,
        maxPages: 1,
        itemsOptions: [10,20,30,50,100]
      }
    };
    $scope.messages = Messages;
    $scope.toaster = toaster;
    getAccount();
    function getAccount() {
      $rootScope.userId = Handle.getCookie('userId')?Handle.getCookie('userId'):null;
      $rootScope.userName = Handle.getCookie('userName')?Handle.getCookie('userName'):null;
      $rootScope.userMail = Handle.getCookie('userMail')?Handle.getCookie('userMail'):null;
      $rootScope.role = Handle.getCookie('role')?Handle.getCookie('role'):null;
      $rootScope.userStatus = Handle.getCookie('userStatus')?Handle.getCookie('userStatus'):null;
      $rootScope.secretKey = Handle.getCookie('secretKey')?Handle.getCookie('secretKey'):null;
      $rootScope.oaUserName = Handle.getCookie('oaUserName')?Handle.getCookie('oaUserName'):null;
      /*创建者以及用户管理界面需要判断信息*/
      $rootScope.isAdmin = function () {
        var userRole = $rootScope.role?$rootScope.role:'1';
        if(userRole&&userRole === '0') {
          return true;
        }
        return false;
      };
    }

    //退出操作
    $scope.logout = function() {
      Handle.setCookie({secretKey:null});
    };
  }]);

