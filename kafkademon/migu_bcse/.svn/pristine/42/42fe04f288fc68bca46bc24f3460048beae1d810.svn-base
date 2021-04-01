/**
 * Created by Echo on 2017/3/8.
 */
'use strict';
angular.module('app')
  .controller('configSuggestCtrl', [ '$scope','$stateParams', 'Handle','restService','restUrl','TipModal', function ($scope,$stateParams, Handle, restService,restUrl,TipModal) {
    /*variables defined*/
    var appUserId = $stateParams.userId;
    var appId  = $stateParams.appId;
    $scope.suggestions = [];
    $scope.selectedRules = [];
    $scope.fields = [];
    $scope.selectSettings = {enableSearch: true,scrollable:'400px'};
    $scope.isEditing = false;
    $scope.addRule = {};
    var newRule = {
      ruleName: null,
      includeFields: [],
      showField: null,
      isAddRule: true,
      isEditRule: false
    };
    getSearchFields();

    /*$scope function defined*/
    $scope.addSuggestion = function() {
      $scope.isEditing = true;
      $scope.selectedRules = [];
      $scope.addRule = angular.copy(newRule);
      $scope.suggestions.push(newRule);
    };
    $scope.editSuggestion = function(rule) {
      $scope.isEditing = true;
      angular.forEach($scope.suggestions, function(ruleItem) {
        ruleItem.isAddRule = false;
        ruleItem.isEditRule = false;
      });
      rule.isEditRule = true;
      $scope.addRule = angular.copy(rule);
      $scope.addRule.includeFields = Handle.arr2IdObj(rule.includeFields);
    };
    $scope.cancelSuggestion = function() {
      $scope.isEditing = false;
      $scope.addRule = {};
      getSuggestions();
    };
    $scope.saveSuggestion = function(rule) {

      /*[成功返回后重新获取所有列表数据getAllRules]*/
      var promise;
      if(rule.isAddRule) {
        promise = restService.promiseRequest(restUrl.getUrl('config')+'/suggestion','POST',{},{userId: appUserId,appId:appId,ruleName:rule.ruleName,includeFields:Handle.getIncludeFields(rule.includeFields),showField:rule.showField});
      }else {
        promise = restService.promiseRequest(restUrl.getUrl('config')+'/suggestion/:ruleName','PUT',{ruleName:rule.ruleName},{userId: appUserId,appId:appId,includeFields:Handle.getIncludeFields(rule.includeFields),showField:rule.showField});
      }
      $scope.submitting = true;
      promise.then(function(res){
        if(res) {
          $scope.addRule = {};
          $scope.isEditing = false;
          getSuggestions();
        }
      },function(error) {

      }).finally(function() {
        $scope.submitting = false;
      });
    };
    $scope.deleteSuggestion = function(rule) {
      TipModal.showModal({}, {}).then(function(result) {
        $scope.isEditing = false;
        $scope.submitting = true;
        restService.promiseRequest(restUrl.getUrl('config')+'/suggestion/:ruleName','DELETE',{ruleName:rule.ruleName},{userId: appUserId,appId:appId}).then(function(res){
          if(res) {
            getSuggestions();
          }
        },function(error) {

        }).finally(function() {
          $scope.submitting = false;
        });
      });
    };
    $scope.deleteSuggestions = function() {
      if(Handle.checkNotEmpty($scope.selectedRules,'删除规则')) {
        TipModal.showModal({}, {}).then(function(result) {
          var ruleNames = Handle.getDeleteRuleName($scope.selectedRules);
          $scope.submitting = true;
          restService.promiseRequest(restUrl.getUrl('config')+'/suggestion/bash','POST',{},{userId: appUserId,appId:appId,ruleNames:ruleNames}).then(function(res){
            if(res) {
              getSuggestions();
            }
          },function(error) {

          }).finally(function() {
            $scope.submitting = false;
          });
        });
      }
    };
    /**
     * [getSelectedSuggestions 添加未有的信息到数组中，如已有则删除]
     * @param item {object or string} [要存储的对象]
     * */
    $scope.getSelectedSuggestions = function(rule) {
      $scope.selectedRules = Handle.getSelectedRules(rule,angular.copy($scope.selectedRules));
    };
    $scope.getAllSelectedSuggestions = function() {
      if($scope.selectedRules.length === $scope.suggestions.length) {
        $scope.selectedRules = [];
      }else {
        $scope.selectedRules = angular.copy($scope.suggestions);
      }
    };
    $scope.checkSelectedSuggestion = function(rule) {
      return Handle.checkSelectedRule(rule, $scope.selectedRules);
    };
    $scope.executeSuggest = function(val) {
      //var searchObj = Handle.limitSearchText(val, 76);
      //$scope.inpuLimitMsg = searchObj['extraText'] !== null?'<strong>"'+searchObj['extraText']+'"</strong>'+'及其后面的字词均被忽略，因为bcse的查询限制在38个汉字以内。':null;
      return restService.promiseRequest(restUrl.getUrl('suggestion'),'POST',{},{userId: appUserId,appId:appId,searchQuery:val,sortConfig:$scope.suggestRule.ruleName}).then(function(res) {
        if(res&&res.resultList&&res.resultList.length) {
          var suggestions=res.resultList,suggestionRes=[];
          angular.forEach(suggestions, function(suggestionsItem) {
            angular.forEach(suggestionsItem,function(suggestionItem) {
              suggestionRes.push(suggestionItem);
            })
          });
          return suggestionRes.map(function(suggestion){
            return angular.isArray(suggestion)?(suggestion.length?suggestion[0]:''):suggestion;
          });
        }
      });
    };
    //typeahead处理选中结果
    $scope.intelFormatter = function(keyword) {
      if (keyword) {
        return keyword.replace(/<em>|<\/em>/g,'');
      }
    };
    /*function defined*/
    /**
    * [getSuggestions] [获取规则列表]
    * */
    function getSuggestions () {
      /*[handleSuggestionResponse回调函数处理数据]*/
      $scope.selectedRules = [];
      $scope.submitting = true;
      restService.promiseRequest(restUrl.getUrl('config')+'/suggestion','GET',{},{userId: appUserId,appId:appId}).then(function(res){
        if(res) {
          $scope.suggestions = res.ruleList;
          $scope.suggestRule = res.ruleList.length?res.ruleList[0]:null;
        }
      },function(error) {

      }).finally(function() {
        $scope.submitting = false;
      });
    }
    /**
     * [getSearchFields] [获取可搜索字段]
     * */
    function getSearchFields () {
      restService.request(restUrl.getUrl('config')+'/fields','GET',{},{userId: appUserId,appId:appId},function(res){
        if(res) {
          getSuggestions();
          $scope.showFields = res.showFields;
          newRule.showField = res.showFields.length?res.showFields[0]:null;
          angular.forEach(res.suggestFields, function(field,key) {
            $scope.fields.push({id:field,label:field});
          })
        }
      }, function(error) {

      });
    }

  }]);
