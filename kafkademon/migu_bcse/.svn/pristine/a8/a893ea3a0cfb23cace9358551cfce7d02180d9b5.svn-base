/**
 * Created by Echo on 2017/2/28.
 */
'use strict';
angular.module('app')
  .controller('appCtrl', [ '$scope','$rootScope', 'Menus','Handle','$uibModal','restService','restUrl','$stateParams','$state','appInfo', 'TipModal', function ($scope,$rootScope, Menus, Handle, $uibModal,restService,restUrl,$stateParams,$state,appInfo,TipModal) {
    /*variables defined*/
    var userId = $rootScope.userId;
    var appUserId = $stateParams.userId;
    var appId = $stateParams.appId;
    $scope.appInfo = {};
    if(appInfo) {
      $scope.appInfo = appInfo.info;
    }
    $scope.tableStruct = [];
    //获取整个菜单栏的信息
    $scope.settings = Menus.settings;
    /*追踪路由状态变化，并展开相应的目录*/
    $scope.$on('$stateChangeSuccess', function(event, toState) {
      Handle.autoOpenMenu(toState.name,$scope.settings);
    });
    getSheetData();
    $scope.$on('refreshDataNum',function(event,param){
      getAppBasicInfo();
    });
    /*$scope function defined*/
    /**点击可展开目录时，只展示当前目录*/
    $scope.openMenu = Handle.openMenu;
    //上传文件弹出框
    $scope.openModal = function (appId,appUserId,type) {
      $uibModal.open({
        templateUrl: 'views/templates/upload.file.html',
        size: 'lg',
        scope: $scope,
        controller: 'uploadFileCtrl',
        backdrop: 'static',
        resolve: {
          info: function(){
            return {appUserId: appUserId, appId: appId, type: type};
          },
          tableStruct: function() {
            return clearFiles($scope.tableStruct);
          }
        }
      });
    };
    $scope.statuChange = function(app) {
      var status = app.appStatus === '0'?'1':'0';
      restService.request(restUrl.getUrl('apps')+'/:appId','POST',{appId:app.appId},{userId: userId,appStatus:status},function(res){
        app.appStatus = status;
      },function(error) {

      });
    };
    $scope.deleteApp = function() {
      TipModal.showModal({}, {}).then(function(result) {
        $scope.submitting = true;
        restService.promiseRequest(restUrl.getUrl('apps')+'/:appId','DELETE',{appId:$scope.appInfo.appId},{userId: userId}).then(function(res){
          if(res) {
            $state.go('manage.apps');
          }
          $scope.submitting = false;
        },function(error) {
          console.log(error);
          $scope.submitting = false;
        });
      });
    };
    $scope.openUploadModal = function(type) {
      return $scope.openModal(appId,appUserId,type);
    };

    /**检查规则名是否已经存在*/
    $scope.checkRuleName=function(ruleItem, rules){
      var datas = angular.copy(rules);
      if(!ruleItem||!ruleItem['ruleName']){
        $scope.toaster.pop('warning',null,$scope.messages.public.empty2('规则名称'), 3000);
        return false;
      }
      /*for(var index=0;index<datas.length;index++){
        var data=datas[index];
        if(ruleItem['ruleName'].toLowerCase()==data['ruleName'].toLowerCase()){
          $scope.toaster.pop('warning',null,$scope.messages.rule.repeatRuleName, 3000);
          ruleItem['ruleName'] = '';
          return true;
        }
      }*/
      return true;
    };

    $scope.getTemplate = function(rule, newRule) {
      if (rule.isAddRule||rule.isEditRule) {
        return 'edit';
      }else {
        return 'display';
      }
    };
    /*function defined*/
    /**
     * [getAppBasicInfo 获取应用的基本信息]
     * */
    function getAppBasicInfo() {
      restService.request(restUrl.getUrl('apps')+'/:appId/basicInfo','GET',{appId:appId},{userId: appUserId},function(res){
        if(res) {
          $scope.appInfo = res.info;
          //getSheetData();
        }
      }, function(error) {

      });
    }
    /**
     * [getSheetData 获取应用结构]*/
    function getSheetData () {
      restService.request(restUrl.getUrl('apps')+'/:appId/structure','GET',{appId:appId},{userId: appUserId},function(res){
        $scope.tableStruct = res.appTableList;
        //getAppBasicInfo();
      },function(error) {
      });
    }
    function clearFiles(tableList) {
      angular.forEach(tableList,function(table) {
        table.files = [];
      });
      return tableList;
    }
  }]);
