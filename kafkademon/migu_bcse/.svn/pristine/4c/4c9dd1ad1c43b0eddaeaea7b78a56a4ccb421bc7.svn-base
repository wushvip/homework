/**
 * Created by Echo on 2017/3/9.
 */
'use strict';
angular.module('app')
  .controller('hotWordCtrl', [ '$scope','$stateParams','restService','restUrl','Handle','$timeout', function ($scope,$stateParams,restService,restUrl,Handle, $timeout) {
    /*variable defined*/
    var appUserId = $stateParams.userId;
    var appId  = $stateParams.appId;
    $scope.hotWords=[];
    $scope.page = angular.copy($scope.pubObj.page);
    $scope.type='1';
    /*$scope function defined*/
    $scope.getHotWords = function(type) {
      $scope.type = type;
      $scope.hotWords=[]; //清空上一次列表
      $scope.submitting = true;
      var startTime, endTime, promise;
      switch(type) {
        case '1':
          promise = restService.promiseRequest(restUrl.getUrl('statistics')+'/hotWord/yesterday','GET',{},{userId: appUserId,appId:appId,pageIndex:$scope.page.currentPage,pageNum:$scope.page.numPerPage});
          break;
        case '2':
          promise = restService.promiseRequest(restUrl.getUrl('statistics')+'/hotWord/week','GET',{},{userId: appUserId,appId:appId,pageIndex:$scope.page.currentPage,pageNum:$scope.page.numPerPage});
          break;
        case '3':
          promise = restService.promiseRequest(restUrl.getUrl('statistics')+'/hotWord/month','GET',{},{userId: appUserId,appId:appId,pageIndex:$scope.page.currentPage,pageNum:$scope.page.numPerPage});
          break;
        default:
          startTime = Handle.getNumFromDate($scope.startTime);
          endTime = Handle.getNumFromDate($scope.endTime);
          promise = restService.promiseRequest(restUrl.getUrl('statistics')+'/hotWord','GET',{},{userId: appUserId,appId:appId,startDate:startTime,endDate:endTime,pageIndex:$scope.page.currentPage,pageNum:$scope.page.numPerPage});
          break;
      }
      promise.then(function(res){
        if(res) {
          $scope.hotWords = res.hotWordList;
          $scope.page.totalItems = res.totalItems;
          $scope.page.maxPages = Math.ceil($scope.page.totalItems / $scope.page.numPerPage);
        }
      },function(error) {

      }).finally(function() {
        $scope.submitting = false;
      });
    };
    $scope.getHotWords($scope.type);
    /*function defined*/
    /*function getHotWords(startTime,endTime ) {
      $scope.hotWords=[]; //清空上一次列表
      $scope.submitting = true;
      promise.then(function(res){
        if(res) {
          $scope.hotWords = res.hotWordList;
          $scope.page.totalItems = res.totalItems;
          $scope.page.maxPages = Math.ceil($scope.page.totalItems / $scope.page.numPerPage);
        }
      },function(error) {

      }).finally(function() {
        $scope.submitting = false;
      });
    }*/

    /*time picker setting*/
    $scope.startTime = Handle.yesterday;
    $scope.endTime = Handle.yesterday;
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
      maxDate: Handle.today,
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
          $scope.getHotWords($scope.type);
        }
      }
    };
  }]);
