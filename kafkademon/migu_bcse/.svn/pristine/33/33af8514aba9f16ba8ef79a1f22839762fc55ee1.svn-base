/**
 * Created by Echo on 2017/2/28.
 */
'use strict';
angular.module('apps')
  .controller('appsCtrl', ['$scope','$rootScope', '$state','restService','restUrl','TipModal', function ($scope,$rootScope, $state, restService, restUrl, TipModal) {
    var userId = $rootScope.userId;
    var modalOptions = {};
    $scope.searchText = '';
    $scope.apps = [];
    $scope.page = angular.copy($scope.pubObj.page);
    getAllApps();
    /*define scope function*/
    $scope.appDetail = function(app, flag) {
      switch (flag) {
        case '0':
          if($rootScope.isAdmin()) {
            $state.go('manage.app.basic',{appId: app.appId, userId: app.userId});
          }else {
            $state.go('manage.app.order',{appId: app.appId, userId: app.userId});
          }
          break;
        case '1':
          $state.go('manage.app.index',{appId: app.appId, userId: app.userId});
          break;
        case '2':
          $state.go('manage.test',{appId: app.appId});
          break;
        case '3':
          $state.go('manage.app.error',{appId: app.appId, userId: app.userId});
          break;
      }
    };
    $scope.updateApp = function(app) {
      var status = app.appStatus === '0'?'1':'0';
      var headerText = status === '1'?'停用':'启用';
      TipModal.showModal({}, {bodyText:'确认' + headerText + '？'}).then(function(result) {
        app.operaDisabled = true;
        restService.promiseRequest(restUrl.getUrl('apps') + '/:appId', 'POST', {appId: app.appId}, {
          userId: userId,
          appStatus: status
        }).then(function (res) {
          if (res) {
            getAllApps();
          }
          app.operaDisabled = false;
        }, function (error) {
          console.log(error);
          app.operaDisabled = false;
        });
      });
    };
    $scope.deleteApp = function(app) {
      TipModal.showModal({}, {}).then(function(result) {
        app.operaDisabled = true;
        restService.promiseRequest(restUrl.getUrl('apps')+'/:appId','DELETE',{appId:app.appId},{userId: userId}).then(function(res){
          if(res) {
            getAllApps();
          }
          app.operaDisabled = false;
        },function(error) {
          console.log(error);
          app.operaDisabled = false;
        });
      });
    };
    $scope.getData = function (pageNo) {
      if(pageNo) {
        if(parseInt(pageNo,10)>=1&&parseInt(pageNo,10)<=$scope.page.maxPages) {
          $scope.page.currentPage = pageNo;
          $scope.page.inputCurPage = pageNo;
          getAllApps();
        }
      }
    };
    $scope.getApps = function() {
      getAllApps();
    };
    /*function defined*/
    /**
     * [getAllApps 获取所有应用数据]
     * */
    function getAllApps() {
      $scope.submitting = true;
      restService.promiseRequest(restUrl.getUrl('apps'),'GET',{},{userId: userId,pageIndex:$scope.page.currentPage,pageNum:$scope.page.numPerPage,searchText:$scope.searchText}).then(function(res){
        if(res) {
          handleApps(res);
        }else {
          $scope.page.totalItems = 0;
        }
        $scope.submitting = false;
      },function(error) {
        console.log(error);
        $scope.submitting = false;
      });
    }
    /**
     * [handleApps 处理返回的应用数据]
     * */
    function handleApps(data) {
      $scope.apps = data.appList;
      $scope.page.totalItems = data.totalItems;
      $scope.page.maxPages = Math.ceil($scope.page.totalItems / $scope.page.numPerPage);
    }
  }]);
