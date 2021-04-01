/**
 * Created by Echo on 2017/3/7.
 */
'use strict';
angular.module('app')
  .controller('configOrderCtrl', [ '$scope','$uibModal','$stateParams','restService','restUrl','Handle','TipModal', function ($scope, $uibModal,$stateParams, restService, restUrl,Handle,TipModal) {
    /*variables defined*/
    var appUserId = $stateParams.userId;
    var appId  = $stateParams.appId;
    $scope.roughs = [];
    $scope.fields = [];
    $scope.selectedRules = [];
    var newRule = {ruleName: null,fields:[],isAddRule: true};
    getRoughFields();
    /*$scope function defined*/
    $scope.addRough = function() {
      openModel(newRule);
    };
    $scope.editRough = function(rule) {
      openModel(rule);
    };
    $scope.saveRough = function(rule, callback) {
      if(Handle.checkNotEmpty(rule.fields,'表达式配置')) {
        $scope.submitting = true;
        var promise;
        if(!rule.isAddRule) {
          promise = restService.promiseRequest(restUrl.getUrl('config')+'/rough/:ruleName','PUT',{ruleName:rule.ruleName},{userId: appUserId,appId:appId,fields:rule.fields});
        }else {
          promise = restService.promiseRequest(restUrl.getUrl('config')+'/rough','POST',{},{userId: appUserId,appId:appId,ruleName:rule.ruleName,fields:rule.fields});
        }
        promise.then(function(res){
          if(res) {
            callback();
            getRoughs();
          }
        },function(error) {

        }).finally(function(){
          $scope.submitting = false;
        });
      }
    };
    $scope.deleteRoughs = function() {
      if(Handle.checkNotEmpty($scope.selectedRules,'删除规则')) {
        TipModal.showModal({}, {}).then(function(result) {
          var ruleNames = Handle.getDeleteRuleName($scope.selectedRules);
          $scope.submitting = true;
          restService.promiseRequest(restUrl.getUrl('config') + '/rough/bash', 'POST', {}, {
            userId: appUserId,
            appId: appId,
            ruleNames: ruleNames
          }).then(function (res) {
            if (res) {
              getRoughs();
            }
          }, function (error) {

          }).finally(function () {
            $scope.submitting = false;
          });
        });
      }
    };
    /**
    * [deleteRough] ({string}) [删除单个应用]
     * */
    $scope.deleteRough = function(rule) {
      TipModal.showModal({}, {}).then(function(result) {
        $scope.submitting = true;
        restService.promiseRequest(restUrl.getUrl('config')+'/rough/:ruleName','DELETE',{ruleName:rule.ruleName},{userId: appUserId,appId:appId}).then(function(res){
          if(res) {
            getRoughs();
          }
        },function(error) {

        }).finally(function() {
          $scope.submitting = false;
        });
      });
    };
    $scope.getSelectedRoughs = function(rule) {
      $scope.selectedRules = Handle.getSelectedRules(rule,angular.copy($scope.selectedRules));
    };
    $scope.getAllSelectedRoughs = function() {
      if($scope.selectedRules.length === $scope.roughs.length) {
        $scope.selectedRules = [];
      }else {
        $scope.selectedRules = angular.copy($scope.roughs);
      }
    };
    $scope.checkSelectedRough = function(rule) {
      return Handle.checkSelectedRule(rule, $scope.selectedRules);
    };
    /*function defined*/
    /**
    * [getRoughs] [获取规则列表]
    * */
    function getRoughs() {
      $scope.selectedRules = [];
      $scope.submitting = true;
      restService.promiseRequest(restUrl.getUrl('config')+'/rough','GET',{},{userId: appUserId,appId:appId}).then(function(res){
        if(res) {
          $scope.roughs = res.ruleList;
        }
      },function(error) {

      }).finally(function() {
        $scope.submitting = false;
      });
    }
    /**
    * [openModel] ({object}) [传入待编辑的规则]
    * */
    function openModel(rule) {
      $uibModal.open({
        templateUrl: 'views/app/config/order/edit.rough.html',
        size: 'lg',
        scope: $scope,
        backdrop: 'static',
        controller: ['$uibModalInstance','rule','rules','$filter', function($uibModalInstance,rule,rules,$filter) {
          $scope.rule = rule;
          $scope.rules = rules;
          //取消模态框
          $scope.cancel = function() {
            $uibModalInstance.dismiss('cancel');
          };
          $scope.toggleItem = function(rule,field) {
            var item = $filter('filter')(rule.fields, {fieldName:field},true);
            if(item.length) {
              var idx = rule.fields.indexOf(item[0]);
              rule.fields.splice(idx,1);
            }else {
              rule.fields.push({fieldName: field, fieldWeight:1});
            }
          };
          $scope.checkSelected = function(rule,field) {
            var item = $filter('filter')(rule.fields, {fieldName:field},true);
            return item.length;
          };
        }],
        resolve: {
          rule: function(){
            var tempRule = angular.copy(rule);
            var fields = [];
            if(rule.fields.length) {
              angular.forEach(tempRule.fields, function(field) {
                fields.push(field);
              });
            }
            tempRule.fields = fields;
            return tempRule;
          },
          rules: function(){
            return $scope.roughs;
          }
        }
      });
    }
    function getRoughFields() {
      restService.request(restUrl.getUrl('config')+'/fields','GET',{},{userId: appUserId,appId:appId},function(res){
        if(res) {
          getRoughs();
          $scope.fields = res.roughFields;
        }
      }, function(error) {

      });
    }

  }]);
