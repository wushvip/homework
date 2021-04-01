/**
 * Created by Echo on 2017/3/8.
 */
'use strict';
angular.module('app')
  .controller('configSpreadCtrl', [ '$scope','$stateParams','restService','restUrl','Handle','TipModal', function ($scope,$stateParams,restService,restUrl,Handle,TipModal) {
    /*variable defined*/
    var appUserId = $stateParams.userId;
    var appId  = $stateParams.appId;
    $scope.spreads = [];
    $scope.selectedRules = [];
    $scope.isEditing = false;
    $scope.addRule = {};
    var newRule = {
      ruleName: null,
      includeKeywords: [],
      spreadIds: [],
      isAddRule: true,
      isEditRule: false
    };
    getSpreads();
    /*$scope function defined*/
    $scope.addSpread = function (rule) {
      $scope.isEditing = true;
      $scope.selectedRules = [];
      $scope.addRule = angular.copy(newRule);
      $scope.spreads.push(newRule);
    };
    $scope.editSpread = function (rule) {
      $scope.isEditing = true;
      angular.forEach($scope.spreads, function(ruleItem) {
        ruleItem.isAddRule = false;
        ruleItem.isEditRule = false;
      });
      rule.isEditRule = true;
      $scope.addRule = angular.copy(rule);
    };
    $scope.cancelSpread = function () {
      $scope.isEditing = false;
      $scope.addRule = {};
      getSpreads();
    };
    $scope.saveSpread = function (rule) {
      var includeKeywords = Handle.cleanArrTrim(rule.includeKeywords);
      var spreadIds = Handle.cleanArrTrim(rule.spreadIds);
      if(Handle.checkNotEmpty(includeKeywords,'包含关键字') && Handle.checkNotEmpty(spreadIds,'包含ID')) {
        saveSpread(rule.isAddRule,rule.ruleName,includeKeywords,spreadIds);
      }
    };
    $scope.deleteSpread = function (rule) {
      TipModal.showModal({}, {}).then(function(result) {
        $scope.isEditing = false;
        $scope.submitting = true;
        restService.promiseRequest(restUrl.getUrl('config')+'/spread/:ruleName','DELETE',{ruleName:rule.ruleName},{userId: appUserId,appId:appId}).then(function(res){
          if(res) {
            getSpreads();
          }
        },function(error) {

        }).finally(function() {
          $scope.submitting = false;
        });
      });
    };
    $scope.deleteSpreads = function () {
      if(Handle.checkNotEmpty($scope.selectedRules,'所选规则')) {
        TipModal.showModal({}, {}).then(function(result) {
          var ruleNames = Handle.getDeleteRuleName($scope.selectedRules);
          $scope.submitting = true;
          restService.promiseRequest(restUrl.getUrl('config') + '/spread/bash', 'POST', {}, {
            userId: appUserId,
            appId: appId,
            ruleNames: ruleNames
          }).then(function (res) {
            if (res) {
              getSpreads();
            }
          }, function (error) {

          }).finally(function () {
            $scope.submitting = false;
          });
        });
      }
    };
    $scope.getSelectedSpreads = function(rule) {
      $scope.selectedRules = Handle.getSelectedRules(rule,angular.copy($scope.selectedRules));
    };
    $scope.getAllSelectedSpreads = function() {
      if($scope.selectedRules.length === $scope.spreads.length) {
        $scope.selectedRules = [];
      }else {
        $scope.selectedRules = angular.copy($scope.spreads);
      }
    };
    $scope.checkSelectedSpread = function(rule) {
      return Handle.checkSelectedRule(rule, $scope.selectedRules);
    };
    /*function defined*/
    /**
     * [getSpreads] [获取规则列表]
     * */
    function getSpreads() {
      $scope.selectedRules = [];
      $scope.submitting = true;
      restService.promiseRequest(restUrl.getUrl('config')+'/spread','GET',{},{userId: appUserId,appId:appId}).then(function(res){
        if(res) {
          $scope.spreads = res.ruleList;
        }
      },function(error) {

      }).finally(function() {
        $scope.submitting = false;
      });
    }
    /**
     * saveSpread [保存规则]
     * @param isAddRule {boolean} [判断是否添加还是更新规则]
     * */
    function saveSpread(isAddRule,ruleName,includeKeywords,spreadIds) {
      //$scope.isEditing = false;
      /*[成功返回后重新获取所有列表数据getAllRules]*/
      var promise;
      if(isAddRule) {
        promise = restService.promiseRequest(restUrl.getUrl('config')+'/spread','POST',{},{userId: appUserId,appId:appId,ruleName:ruleName,includeKeywords:includeKeywords,spreadIds:spreadIds});
      }else {
        promise = restService.promiseRequest(restUrl.getUrl('config')+'/spread/:ruleName','PUT',{ruleName:ruleName},{userId: appUserId,appId:appId,includeKeywords:includeKeywords,spreadIds:spreadIds});
      }
      $scope.submitting = true;
      promise.then(function(res){
        if(res) {
          $scope.addRule = {};
          $scope.isEditing = false;
          getSpreads();
        }
      },function(error) {

      }).finally(function() {
        $scope.submitting = false;
      });
    }

  }]);
