/**
 * Created by Echo on 2017/3/9.
 */
'use strict';
angular.module('app')
  .controller('errorLogCtrl', [ '$scope','$stateParams','restService','restUrl','Handle','$timeout', function ($scope,$stateParams,restService,restUrl,Handle, $timeout) {
    /*variable defined*/
    var appUserId = $stateParams.userId;
    var appId  = $stateParams.appId;
    var myDate = new Date();
    $scope.errorLogs = [];
    $scope.page = angular.copy($scope.pubObj.page);
    getLogs(0,0);
    /*$scope function defined*/
    $scope.getLogs = function() {
      var start = Handle.getNumFromDate($scope.startTime);
      var end = Handle.getNumFromDate($scope.endTime);
      getLogs(start,end);
    };
    /*function defined*/
    function getLogs(startTime,endTime) {
      $scope.errorLogs = [];  //清空上一次列表
      $scope.submitting = true;
      restService.promiseRequest(restUrl.getUrl('statistics')+'/errorLog','GET',{},{userId: appUserId,appId:appId,startDate:startTime,endDate:endTime,pageIndex:$scope.page.currentPage,pageNum:$scope.page.numPerPage}).then(function(res){
        if(res) {
          $scope.errorLogs = res.errorLogList;
          $scope.page.totalItems = res.totalItems;
          $scope.page.maxPages = Math.ceil($scope.page.totalItems / $scope.page.numPerPage);
        }
      },function(error) {

      }).finally(function() {
        $scope.submitting = false;
      });
    }

    /*time picker setting*/
    $scope.startTime = myDate;
    $scope.endTime = myDate;
    // 时间选择器配置
    $scope.startDateOptions = {
      formatYear: 'yy',
      minDate: null,
      maxDate: $scope.endTime,
      startingDay: 1
    };
    $scope.endDateOptions = {
      formatYear: 'yy',
      minDate: $scope.startTime,
      maxDate: new Date(),
      startingDay: 1
    };
    $scope.$watch('startTime', function(newValue,oldValue){
      $scope.endDateOptions.minDate = newValue;
    });
    $scope.$watch('endTime', function(newValue,oldValue){
      $scope.startDateOptions.maxDate = newValue;
    });
    $scope.startOpen = function() {
      $timeout(function() {
        $scope.startPopupOpened = true;
      });
    };
    $scope.endOpen = function() {
      $timeout(function() {
        $scope.endPopupOpened = true;
      });
    };
    $scope.startPopupOpened = false;
    $scope.endPopupOpened = false;
    $scope.getData = function (pageNo) {
      if(pageNo) {
        if(parseInt(pageNo,10)>=1&&parseInt(pageNo,10)<=$scope.page.maxPages) {
          $scope.page.currentPage = pageNo;
          $scope.page.inputCurPage = pageNo;
          $scope.getLogs();
        }
      }
    };
  }]);
