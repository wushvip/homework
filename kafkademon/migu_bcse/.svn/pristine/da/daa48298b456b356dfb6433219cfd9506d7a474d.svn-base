/**
 * Created by Echo on 2017/3/3.
 */
'use strict';
angular.module('apps')
.controller('structCtrl', ['$scope','$rootScope','$stateParams','Handle','Limitation','$state','restService','restUrl', function ($scope,$rootScope,$stateParams, Handle, Limitation, $state,restService,restUrl) {
    /* variables defined*/
    var userId = $rootScope.userId;
    var appId  = $scope.basicInfo.appId||$stateParams.appId;
    var templateUrl = ['views/create/struct.custom.html','views/create/struct.import.html'];
    var columnNameLimit=Limitation;
    //表数据
    $scope.tableList=[];
    /* function executed */
    tempInit();
    getSheetData();
    /* $scope function defined*/
    $scope.setAppStruct=function(template){
      // 如果选择的模板与当前一致，则直接return
      if (template === $scope.struct['type']) {
        return;
      }
      if ($scope.tableList.length>0) {
        $scope.tableList = [];
        changeTemplate(template);
      }else {
        changeTemplate(template);
      }
    };

    /*批量字段导入*/
    $scope.generateTable = function() {
      var fieldObject, regexp = /^[a-zA-Z][a-zA-Z0-9_]*$/;
      //控制字段信息的变量初始化
      $scope.tableList = [{tableName:'main',isMainTable:'1',fields:[],sourceOptions:[]}];
      //处理输入框中的字符串（以逗号为分隔符）为数组并进行去重复
      $scope.struct['fieldArray'] = fieldArrClean($scope.struct['fieldString'].split(","));
      angular.forEach($scope.struct['fieldArray'],function(field) {
        $scope.tableList[0].sourceOptions.push({field:field});
        //重新赋值控制字段信息
        fieldObject = {
          fieldName: field,
          isPk: '0',
          fieldType: 'STRING',
          isSearch: '1',
          isShow: '1',
          isMutivalue: '0',
          dynamicField: '0',
          srcField: []
        };
        $scope.tableList[0].fields.push(fieldObject);
      });
    };
    //清除输入框中字段以及数据表字段
    $scope.clearFieldTable = function() {
      $scope.struct['fieldString'] = '';
      $scope.struct['fieldArray'] = [];
      $scope.tableList = [];
    };

    /**
     * [previous 应用结构点击上一步返回基本信息触发]
     */
    $scope.previous=function(){
      $scope.navInit();
      $scope.nav.steps[0]['actived'] = true;
      $state.go('manage.create');
    };

    /**
     * [next 创建应用点击下一步触发]
     */
    $scope.saveStruct=function(){
      var errorMsg = checkData();
      if(errorMsg !== "") {
        $scope.toaster.pop('warning','', errorMsg, 3000);
      }else {
        var appTableList = tableList2Param($scope.tableList);
        $scope.submitting = true;
        restService.promiseRequest(restUrl.getUrl('apps')+'/:appId/structure','POST',{appId: appId},{userId: userId,appTableList: appTableList}).then(function(res){
          if(res) {
            $scope.structInfo = appTableList;
            $state.go('manage.create.confirm', {appId: appId});
          }
          $scope.submitting = false;
        },function(error) {
          console.log(error);
          $scope.submitting = false;
        });
      }
    };
    /**
     * [getSheetData 获取应用结构]*/
    function getSheetData () {
      $scope.submitting = true;
      restService.promiseRequest(restUrl.getUrl('apps')+'/:appId/structure','GET',{appId: appId},{userId: userId}).then(function(res){
        if(res) {
          $scope.tableList = param2TableList(res.appTableList);
        }
        $scope.submitting = false;
      },function(error) {
        console.log(error);
        $scope.submitting = false;
      });
    }
    /**
     * [param2TableList 处理获取的后台数据为可展示列表]
     * */
    function param2TableList(param){
      var tableList = angular.copy(param);
      angular.forEach(tableList, function(item) {
        item.sourceOptions = [];
        angular.forEach(item.fields,function(field){
          if (field['srcField']) {
            field['srcField'] = field['srcField'].split(',');
          }else {
            field['srcField'] = [];
          }
          if (field['dynamicField'] === '0') {
            item['sourceOptions'].push({field:angular.copy(field['fieldName'])});
          }else {
            field['fieldName'] = field['fieldName'].replace('*','');
            item['sourceOptions'].push({field:''});
          }
        });
      });
      return tableList;
    }
    /**
     * [tableList2Param 处理tableList数据为保存参数]
     * */
    function tableList2Param(tableList){
      var param = angular.copy(tableList);
      angular.forEach(param, function(item) {
        delete item.sourceOptions;
        angular.forEach(item.fields,function(field){
          if (field['dynamicField'] === '1') {
            field['fieldName'] = '*' + field['fieldName'];
          }
          field['srcField'] = field['srcField'].join(',');
        });
      });
      return param;
    }
    /**清除str*/
    function strClean(str,len,unfitStrArray,disabledStrArray) {
      var regexp = /^[a-zA-Z][a-zA-Z0-9_]*$/;
      //去除字符前后空格
      str = str.replace(/(^\s*)|(\s*$)/g,"");
      //截取30个有效字符
      str = str.substring(0, len);
      //去除字符前后空格
      str = str.replace(/(^\s*)|(\s*$)/g,"");
      if(!regexp.test(str)) {
        unfitStrArray.push(str);
        str = '';
      }
      if(columnNameLimit.indexOf(str.toUpperCase())!==-1){
        disabledStrArray.push(str);
        str = '';
      }
      return str;
    };
    function fieldArrClean(arr) {
      //unfitStrArray不符合字段名，disabledStrArray保留字段名
      var newArr=[],uniqueArr=[],unfitStrArray=[],disabledStrArray=[],modalOptions={};
      angular.forEach(arr,function(field) {
        if(field) {
          field = strClean(field, 30,unfitStrArray,disabledStrArray);
          newArr.push(field);
        }
      });
      uniqueArr = Handle.removeRepetition(Handle.removeEmpty(newArr));
      if(arr.length>uniqueArr.length) {
        $scope.toaster.pop('warning','', $scope.messages.create.repeat,3000);
      }
      return uniqueArr;
    };    /**
     * [checkData 检查对象tableList是否符合要求]
     * 检查是否存在表
     * 检查表名是否为空
     * 如果不存在主键则给于提示信息
     */
    var checkData = function() {
      var data = $scope.tableList,errorMsg = "",dataFields,existPk = false;

      if (!data.length) {
        errorMsg = $scope.messages.create.addTable;
        return errorMsg;
      }

      for (var i = 0; i < data.length; i++) {
        if(!data[i].tableName) {
          errorMsg = $scope.messages.create.tableEmpty;
          return errorMsg;
        }
        dataFields = data[i].fields;
        if (dataFields.length === 0) {
          errorMsg = $scope.messages.create.undefinedField;
          return errorMsg;
        }
        for (var j = 0; j < dataFields.length; j++) {
          if(!dataFields[j].fieldName) {
            errorMsg = $scope.messages.create.unnamedField;
            return errorMsg;
          }
          if (dataFields[j].isPk === "1") {
            existPk = true;
          }
        }
      }

      if(!existPk){
        errorMsg = $scope.messages.create.noKey;
        return errorMsg;
      }
      return errorMsg;
    };
    /**
     * [templateInit 页面初始化展示]
     */
    function tempInit(){
      switch($scope.struct.type) {
        case '1': $scope.templateUrl = templateUrl[1];
          break;
        default: $scope.templateUrl = templateUrl[0];
          break;
      }
    }
    /**
     * [设置应用结构的模板]
     * @param {[string]} template [表示模板类型]
     * 自定义结构'0' 批量字段导入'1'
     */
    function changeTemplate (template) {
      if(template === '0'){
        $scope.struct.type='0';
        $scope.templateUrl=templateUrl[0];
        //从批量字段界面切换至自定义结构界面，初始化批量字段界面变量
        $scope.struct.fieldString = '';
        $scope.struct.fieldArray = [];

      }else{
        $scope.struct.type='1';
        $scope.templateUrl=templateUrl[1];
      }
    };
    /*控制步骤显示*/
    $scope.nav.steps[0]['passed'] = true;
    $scope.nav.steps[2]['passed'] = false;
    $scope.nav.steps[2]['actived'] = true;
    $scope.nav.steps[4]['actived'] = false;
    $scope.nav.steps[6]['actived'] = false;

  }]);
