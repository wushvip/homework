/**
 * Created by Echo on 2017/3/4.
 */
'use strict';
angular.module('apps')
  .controller('confirmCtrl', ['$scope','$rootScope','$state','$stateParams','restService','restUrl', function ($scope,$rootScope, $state,$stateParams, restService, restUrl) {
    /* variables defined*/
    var userId = $rootScope.userId;
    var appId  = $scope.basicInfo.appId||$stateParams.appId;
    /*$scope.app = {
      name: '应用test',
      description: '这是第一个测试应用'
    };*/
    $scope.appList = [];
    $scope.tableStruct = [];

    //$scope.app = $scope.appList.length?$scope.appList[0]:null;
    getSheetData();
    /**
     * [previous 应用结构点击上一步返回应用结构触发]
     */
    $scope.previous=function(){
      $scope.nav.steps[0]['passed'] = true;
      $scope.nav.steps[2]['passed'] = false;
      $scope.nav.steps[2]['actived'] = true;
      $scope.nav.steps[4]['actived'] = false;
      $scope.nav.steps[6]['actived'] = false;
      $state.go('manage.create.struct',{appId:appId});
    };

    $scope.finish = function () {
      $scope.submitting = true;
      restService.request(restUrl.getUrl('apps')+'/:appId/last','POST',{appId: appId},{userId: userId},function(res){
        $scope.submitting = false;
        $state.go('manage.create.finish', {appId: appId});
      },function(error) {
        $scope.submitting = false;
      });
    };

    /*追踪路由状态变化，如果是创建结束界面存在后退，则界面跳转创建应用首页*/
    $scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
      var limitStates = ['manage.create.finish','manage.apps'];
      if (limitStates.indexOf(fromState.name)>-1) {
        $state.go('manage.apps');
      }
    });

    /**
     * [getTableStruct 获取结构数据]
     * */
    function getSheetData() {
      if($scope.structInfo.length) {
        $scope.tableStruct = angular.copy($scope.structInfo);
      }else {
        $scope.submitting = true;
        restService.request(restUrl.getUrl('apps')+'/:appId/structure','GET',{appId:appId},{userId: userId},function(res){
          $scope.tableStruct = res.appTableList;
          $scope.submitting = false;
        },function(error) {
          $scope.submitting = false;
        });
      }
    }

    /*控制步骤显示*/
    $scope.nav.steps[0]['passed'] = true;
    $scope.nav.steps[2]['passed'] = true;
    $scope.nav.steps[4]['passed'] = false;
    $scope.nav.steps[4]['actived'] = true;
    $scope.nav.steps[6]['actived'] = false;
  }]);
