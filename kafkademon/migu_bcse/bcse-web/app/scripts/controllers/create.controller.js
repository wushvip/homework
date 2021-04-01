/**
 * Created by Echo on 2017/3/2.
 */
/*
* 创建应用全局变量定义
* 基本信息变量定义
* 基本信息执行函数定义
* */
'use strict';
angular.module('apps')
  .controller('createCtrl', ['$scope','$rootScope', '$state','$uibModal','restService','restUrl', function ($scope,$rootScope, $state,$uibModal, restService, restUrl) {
    var userId = $rootScope.userId;
    $scope.nav = {};
    $scope.nav.steps=[{
      label:'填写基本信息',
      state: 'manage.create',
      actived:false,
      passed:false
    },
      {
        label:'',
        state: null,
        actived:false,
        passed:false
      },
      {
        label:'定义应用结构',
        state: 'manage.create.struct',
        actived:false,
        passed:false
      },
      {
        label:'',
        state: null,
        actived:false,
        passed:false
      },
      {
        label:'确认明细',
        state: 'manage.create.confirm',
        actived:false,
        passed:false
      },
      {
        label:'',
        state: null,
        actived:false,
        passed:false
      },
      {
        label:'完成创建',
        state: 'manage.create.finish',
        actived:false,
        passed:false
      }];
    $scope.basicInfo = {
      inCreating: false,
      appId: null,
      appName: null,
      appDescribe: ''
    };
    /*定义struct对象，存储选项值
     **fieldOptions 字段名称选项
     **typeOptions 类型选项
     **sourceOptions 源字段选项
     **boolOptions 布尔值选项
     **tempType 结构类型：自定义结构（'0'）；批量字段导入（'1'）
     */
    $scope.struct = {
      // fieldOptions: [],
      typeOptions: ['STRING','INT','FLOAT','DOUBLE','LONG','DATE','TEXT','ANSJ','QP_NoSplit','JP_NoSplit', 'ANSJ_JP', 'ANSJ_QP', 'ANSJ_PY','JP_Suggest','QP_Suggest'],
      sourceOptions: [],
      boolOptions: {1:'是', 0:'否'},
      type:'0',
      fieldString:'', //批量字段导入输入框值
      fieldArray:[]   //批量字段输入框值形成的数组
    };
    $scope.structInfo = [];

    /*steps用来控制上面进行到哪一步。atived表示显示蓝色，passed表示是否完成*/
    $scope.navInit = function() {
      angular.forEach($scope.nav.steps,function(step,key) {
        step.actived = false;
        step.passed = false;
      });
    };
    $scope.clickGoStatus = function(isPassed, status) {
      if(isPassed&&status) {
        if(status === 'manage.create') {
          $scope.navInit();
          $scope.nav.steps[0]['actived'] = true;
          $state.go(status);
        }else {
          $state.go(status,{appId:$scope.basicInfo.appId});
        }
      }
    };
    $scope.nav.steps[0]['actived'] = true;
    isExistUncompletedApp();

    /*$scope function defined*/
    $scope.saveBasicInfo = function() {
      $scope.submitting = true;
      $scope.basicInfo.inCreating = true;
      if($scope.basicInfo.appId) {
        restService.promiseRequest(restUrl.getUrl('apps')+'/:appId/basicInfo','PUT',{appId: $scope.basicInfo.appId},{userId: userId,appName:$scope.basicInfo.appName,appDescribe:$scope.basicInfo.appDescribe}).then(function(res){
          if(res) {
            $scope.submitting = false;
            $state.go('manage.create.struct',{appId: $scope.basicInfo.appId});
          }
          $scope.submitting = false;
        },function(error) {
          console.log(error);
          $scope.submitting = false;
        });
      }else {
        restService.promiseRequest(restUrl.getUrl('apps')+'/basicInfo','POST',{},{userId: userId,appName:$scope.basicInfo.appName,appDescribe:$scope.basicInfo.appDescribe}).then(function(res){
          if(res) {
            $scope.basicInfo.appId = res.appId;
            $state.go('manage.create.struct',{appId: $scope.basicInfo.appId});
          }
          $scope.submitting = false;
        },function(error) {
          console.log(error);
          $scope.submitting = false;
        });
      }
    };

    /*function defined*/
    function isExistUncompletedApp () {
      if(!$scope.basicInfo.inCreating) {
        restService.request(restUrl.getUrl('apps')+'/existFlag','GET',{},{userId: userId},function(res){
          if(res&&res.isExist === '1') {
            modalOpen(res.appInfoDetail);
          }
        },function(error) {

        });
      }
    }
    function modalOpen (data) {
      $uibModal.open({
        templateUrl: 'views/templates/alert.modal.html',
        scope: $scope,
        backdrop: "static",
        controller:['$scope', '$uibModalInstance',function($scope, $uibModalInstance) {
          $scope.text = {
            header: '提示',
            body: $scope.messages.create.uncompletedApp,
            confirm: '继续创建',
            cancel: '创建新应用'
          };
          $scope.basicInfo.appId = data.appId?data.appId:$scope.basicInfo.appId;
          //创建新应用，删除正在创建中的那个应用
          $scope.cancel = function() {
            /*delete app*/
            $scope.submitting = true;
            restService.promiseRequest(restUrl.getUrl('apps')+'/:appId','DELETE',{appId:$scope.basicInfo.appId},{userId: userId}).then(function(res){
              if(res) {
                $scope.basicInfo.appId = null;
                $scope.basicInfo.appName = null;
                $scope.basicInfo.appDescribe = null;
                $uibModalInstance.dismiss('cancel');
                $scope.nav.steps[0]['passed'] = false;
                $scope.nav.steps[0]['actived'] = true;
                $scope.nav.steps[2]['actived'] = false;
                $scope.nav.steps[4]['actived'] = false;
                $scope.nav.steps[6]['actived'] = false;
                $state.go('manage.create');
              }
              $scope.submitting = false;
            },function(error) {
              $scope.submitting = false;
            });
          };
          //继续创建，则页面中补全应用名称和描述
          $scope.confirm=function(){
            $scope.basicInfo.appName = data.appName?data.appName:$scope.basicInfo.appName;
            $scope.basicInfo.appDescribe = data.appDescribe?data.appDescribe:$scope.basicInfo.appDescribe;
            $uibModalInstance.dismiss('cancel');
          };
        }]});
    }
  }]);
