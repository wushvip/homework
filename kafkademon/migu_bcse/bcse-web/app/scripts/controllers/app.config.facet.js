/**
 * Created by Echo on 2017/3/8.
 */
'use strict';
angular.module('app')
  .controller('configFacetCtrl', [ '$scope','$stateParams', '$uibModal','restService','restUrl','Handle','TipModal', function ($scope,$stateParams, $uibModal,restService, restUrl,Handle,TipModal) {
    /*variable defined*/
    var appUserId = $stateParams.userId;
    var appId  = $stateParams.appId;
    var modalOptions = {};
    $scope.facets = [];
    $scope.facetFields = [];
    $scope.selectedRules = [];
    var newRule = {ruleName: null,includeFields:[],isAddRule: true};
    $scope.selectSettings = {enableSearch: true,scrollable:'400px'};
    getFacetFields();
    /*$scope function defined*/
    $scope.addFacet = function() {
      openModel(newRule);
    };
    $scope.editFacet = function(rule) {
      openModel(rule);
    };
    $scope.saveFacet = function(rule, callback) {
      $scope.submitting = true;
      var promise;
      if(!rule.isAddRule) {
        promise = restService.promiseRequest(restUrl.getUrl('config')+'/facet/:ruleName','PUT',{ruleName:rule.ruleName},{userId: appUserId,appId:appId,includeFields:Handle.getIncludeFields(rule.includeFields)});
      }else {
        promise = restService.promiseRequest(restUrl.getUrl('config')+'/facet','POST',{},{userId: appUserId,appId:appId,ruleName:rule.ruleName,includeFields:Handle.getIncludeFields(rule.includeFields)});
      }
      promise.then(function(res){
        if(res) {
          callback();
          getFacets();
        }
      },function(error) {

      }).finally(function() {
        $scope.submitting = false;
      });
    };
    $scope.deleteFacet = function(rule) {
      TipModal.showModal({}, modalOptions).then(function(result) {
        $scope.submitting = true;
        restService.promiseRequest(restUrl.getUrl('config')+'/facet/:ruleName','DELETE',{ruleName:rule.ruleName},{userId: appUserId,appId:appId}).then(function(res){
          if(res) {
            getFacets();
          }
        },function(error) {

        }).finally(function() {
          $scope.submitting = false;
        });
      });
    };
    $scope.deleteFacets = function() {
      if(Handle.checkNotEmpty($scope.selectedRules,'删除规则')) {
        TipModal.showModal({}, modalOptions).then(function(result) {
          var ruleNames = Handle.getDeleteRuleName($scope.selectedRules);
          $scope.submitting = true;
          restService.promiseRequest(restUrl.getUrl('config') + '/facet/bash', 'POST', {}, {
            userId: appUserId,
            appId: appId,
            ruleNames: ruleNames
          }).then(function (res) {
            if (res) {
              getFacets();
            }
          }, function (error) {

          }).finally(function () {
            $scope.submitting = false;
          });
        });
      }
    };
    $scope.getSelectedFacets = function(rule) {
      $scope.selectedRules = Handle.getSelectedRules(rule,angular.copy($scope.selectedRules));
    };
    $scope.getAllSelectedFacets = function() {
      if($scope.selectedRules.length === $scope.facets.length) {
        $scope.selectedRules = [];
      }else {
        $scope.selectedRules = angular.copy($scope.facets);
      }
    };
    $scope.checkSelectedFacet = function(rule) {
      return Handle.checkSelectedRule(rule, $scope.selectedRules);
    };
    /*function defined*/
    /**
     * [getFacets 获取分组统计规则列表*/
    function getFacets () {
      $scope.selectedRules = [];
      $scope.submitting = true;
      restService.promiseRequest(restUrl.getUrl('config')+'/facet','GET',{},{userId: appUserId,appId:appId}).then(function(res){
        if(res) {
          $scope.facets = res.ruleList;
        }
      },function(error) {

      }).finally(function() {
        $scope.submitting = false;
      });
    }
    /**
     * [getSearchFields] [获取可搜索字段]
     * */
    function getFacetFields () {
      restService.request(restUrl.getUrl('config')+'/fields','GET',{},{userId: appUserId,appId:appId},function(res){
        if(res) {
          getFacets();
          angular.forEach(res.facetFields, function(field) {
            $scope.facetFields.push({id:field,label:field});
          })
        }
      }, function(error) {

      });
    }
    function openModel(rule) {
      $uibModal.open({
        templateUrl: 'views/app/config/facet/edit.facet.html',
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
            var tempRule = angular.copy(rule);
            var fields = [];
            if(rule.includeFields.length) {
              angular.forEach(tempRule.includeFields, function(field) {
                fields.push({id:field});
              });
            }
            tempRule.includeFields = fields;
            return tempRule;
          },
          rules: function(){
            return $scope.facets;
          }
        }
      });
    }
  }]);
