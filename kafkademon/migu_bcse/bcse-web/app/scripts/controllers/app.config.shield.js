/**
 * Created by Echo on 2017/3/8.
 */
'use strict';
angular.module('app')
  .controller('configShieldCtrl', [ '$scope','$stateParams','$uibModal','restService','restUrl', 'Handle','TipModal', function ($scope,$stateParams, $uibModal, restService, restUrl, Handle, TipModal) {
    /*variable defined*/
    var appUserId = $stateParams.userId;
    var appId  = $stateParams.appId;
    $scope.shields = [];
    $scope.selectedRules = [];
    var newRule = {ruleName: null,includeKeywords:[],isAddRule: true};
    getShields();
    /*$scope function defined*/
    $scope.addShield = function() {
      openModel(newRule);
    };
    $scope.editShield = function(rule) {
      openModel(rule);
    };
    $scope.saveShield = function(rule, callback) {
      $scope.submitting = true;
      if(!rule.isAddRule) {
        restService.promiseRequest(restUrl.getUrl('config')+'/shield/:ruleName','PUT',{ruleName:rule.ruleName},{userId: appUserId,appId:appId,includeKeywords:rule.includeKeywords}).then(function(res){
          if(res) {
            callback();
            getShields();
          }
        },function(error) {

        }).finally(function() {
          $scope.submitting = false;
        });
      }else {
        restService.promiseRequest(restUrl.getUrl('config')+'/shield','POST',{},{userId: appUserId,appId:appId,ruleName:rule.ruleName,includeKeywords:rule.includeKeywords}).then(function(res){
          if(res) {
            callback();
            getShields();
          }
        },function(error) {

        }).finally(function(){
          $scope.submitting = false;
        });
      }
    };
    $scope.deleteShield = function(rule) {
      TipModal.showModal({}, {}).then(function(result) {
        $scope.submitting = true;
        restService.promiseRequest(restUrl.getUrl('config')+'/shield/:ruleName','DELETE',{ruleName:rule.ruleName},{userId: appUserId,appId:appId}).then(function(res){
          if(res) {
            getShields();
          }
        },function(error) {

        }).finally(function() {
          $scope.submitting = false;
        });
      });
    };
    $scope.deleteShields = function() {
      if(Handle.checkNotEmpty($scope.selectedRules,'删除规则')) {
        TipModal.showModal({}, {}).then(function(result) {
          var ruleNames = Handle.getDeleteRuleName($scope.selectedRules);
          $scope.submitting = true;
          restService.promiseRequest(restUrl.getUrl('config') + '/shield/bash', 'POST', {}, {
            userId: appUserId,
            appId: appId,
            ruleNames: ruleNames
          }).then(function (res) {
            if (res) {
              getShields();
            }
          }, function (error) {

          }).finally(function () {
            $scope.submitting = false;
          });
        });
      }
    };
    $scope.getSelectedShields = function(rule) {
      $scope.selectedRules = Handle.getSelectedRules(rule,angular.copy($scope.selectedRules));
    };
    $scope.getAllSelectedShields = function() {
      if($scope.selectedRules.length === $scope.shields.length) {
        $scope.selectedRules = [];
      }else {
        $scope.selectedRules = angular.copy($scope.shields);
      }
    };
    $scope.checkSelectedShield = function(rule) {
      return Handle.checkSelectedRule(rule, $scope.selectedRules);
    };
    /*function defined*/
    function getShields () {
      $scope.selectedRules = [];
      $scope.submitting = true;
      restService.promiseRequest(restUrl.getUrl('config')+'/shield','GET',{},{userId: appUserId,appId:appId}).then(function(res){
        if(res) {
          $scope.shields = res.ruleList;
        }
      },function(error) {

      }).finally(function() {
        $scope.submitting = false;
      });
    }
    function openModel(rule) {
      $uibModal.open({
        templateUrl: 'views/app/config/shield/edit.shield.html',
        size: 'lg',
        scope: $scope,
        backdrop: 'static',
        controller: ['$uibModalInstance','rule','rules', function($uibModalInstance, rule, rules) {
          $scope.rule = rule;
          $scope.rules = rules;
          //取消模态框
          $scope.cancel = function() {
            $uibModalInstance.dismiss('cancel');
          };
        }],
        resolve: {
          rule: function(){
            return angular.copy(rule);
          },
          rules: function(){
            return $scope.shields;
          }
        }
      });
    }
  }]);
