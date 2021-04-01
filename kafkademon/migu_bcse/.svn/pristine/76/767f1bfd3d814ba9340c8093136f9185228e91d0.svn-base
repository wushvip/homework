/**
 * Created by Echo on 2017/3/6.
 */
'use strict';
angular.module('app')
  .controller('appIndexCtrl', [ '$scope','$timeout','$uibModal','$stateParams','restService','restUrl','TipModal', function ($scope,$timeout, $uibModal,$stateParams,restService,restUrl,TipModal) {
    /*variables defined*/
    var appUserId = $stateParams.userId;
    var appId = $stateParams.appId;
    /*scope function defined*/
    $scope.updateData = function() {
      $scope.openModal(appId,appUserId,'update');
    };
    $scope.deleteData = function() {
      $scope.openModal(appId,appUserId,'delete');
    };
    $scope.clearData = function() {
      TipModal.showModal({}, {bodyText:'确认清空索引？'}).then(function(result) {
        $scope.submitting = true;
        restService.promiseRequest(restUrl.getUrl('apps')+ '/:appId/index', 'DELETE', {appId: appId}, {userId: appUserId,type: 'all'}).then(function(res) {
          $scope.$emit('refreshDataNum',true);
        }).finally(function() {
          $scope.submitting = false;
        })
      });
    };
    /*历史记录弹出框*/
    $scope.openHistory = function() {
      $uibModal.open({
        templateUrl: 'views/app/index/history.record.html',
        size: 'lg',
        scope:$scope,
        controller: ['$uibModalInstance','$timeout', function($uibModalInstance, $timeout) {
          $scope.records = {
            type: '1',
            startTime: new Date(),
            endTime: new Date()
          };
          // 时间选择器配置
          $scope.startDateOptions = {
            formatYear: 'yy',
            minDate: null,
            maxDate: $scope.records.endTime,
            startingDay: 1
          };
          $scope.endDateOptions = {
            formatYear: 'yy',
            minDate: $scope.records.startTime,
            maxDate: new Date(),
            startingDay: 1
          };
          $scope.$watch('records.startTime', function(newValue,oldValue){
            $scope.endDateOptions.minDate = newValue;
          });
          $scope.$watch('records.endTime', function(newValue,oldValue){
            $scope.startDateOptions.maxDate = newValue;
          });
          $scope.startOpen = function() {
            $timeout(function() {
              $scope.records.startPopupOpened = true;
            });
          };
          $scope.endOpen = function() {
            $timeout(function() {
              $scope.records.endPopupOpened = true;
            });
          };
          $scope.records.startPopupOpened = false;
          $scope.records.endPopupOpened = false;

          $scope.page = angular.copy($scope.pubObj.page);
          //取消模态框
          $scope.cancel = function() {
            $uibModalInstance.dismiss('cancel');
          };
          $scope.getHistory = function(type) {
            $scope.records.type = type;
            $scope.submitting = true;
            restService.promiseRequest( restUrl.getUrl('apps')+'/:appId/index/operations', 'GET', {appId: appId}, {userId: appUserId,operaType:type,startDate:$scope.records.startTime,endDate:$scope.records.endTime,pageIndex:$scope.page.currentPage,pageNum:$scope.page.numPerPage}).then(function(res) {
              if(res) {
                $scope.results = res.historyList;
                $scope.page.totalItems = res.totalItems;
                $scope.page.maxPages = Math.ceil($scope.page.totalItems / $scope.page.numPerPage);
              }
            }).finally(function() {
              $scope.submitting = false;
            })
          };
          $scope.getHistory($scope.records.type);
          $scope.getData = function (pageNo) {
            if(pageNo) {
              if(parseInt(pageNo,10)>=1&&parseInt(pageNo,10)<=$scope.page.maxPages) {
                $scope.page.currentPage = pageNo;
                $scope.page.inputCurPage = pageNo;
                $scope.getHistory($scope.records.type);
              }
            }
          };
        }]
      })
    };


    /*history record*/

    /*varibles defined*/




  }]);
