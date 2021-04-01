/**
 * Created by Echo on 2017/3/4.
 */
'use strict';
angular.module('analysis')
  .controller('analysisCtrl', [ '$scope','$rootScope','restService','restUrl','apps', function ($scope,$rootScope, restService, restUrl,apps) {
    /*variable defined*/
    var userId = $rootScope.userId;
    $scope.apps = apps?apps.appList:[];
    $scope.users = getUserApp( $scope.apps);
    $scope.searchInfo = {
      userId: userId,
      timeAppList: $scope.apps.length?[$scope.apps[0]]:[],
      costAppList: $scope.apps.length?[$scope.apps[0]]:[],
      numAppList: $scope.apps.length?[$scope.apps[0]]:[]
    };

    /*$scope function defined*/
    /**
     * [checkApp 点击应用名称前复选框触发]
     * @param  {[string]} type [类型]
     * @param  {[object]} item [应用]
     * @response none
     */
    $scope.checkApp=function(type,item){
      var idx;
      switch(type)
      {
        case '1':
          idx=$scope.searchInfo.timeAppList.indexOf(item);
          if(idx>-1){
            $scope.searchInfo.timeAppList.splice(idx,1);
          }else{
            $scope.searchInfo.timeAppList.push(item);
          }
          break;
        case '2':
          idx=$scope.searchInfo.costAppList.indexOf(item);
          if(idx>-1){
            $scope.searchInfo.costAppList.splice(idx,1);
          }else{
            $scope.searchInfo.costAppList.push(item);
          }
          break;
        case '3':
          idx=$scope.searchInfo.numAppList.indexOf(item);
          if(idx>-1){
            $scope.searchInfo.numAppList.splice(idx,1);
          }else{
            $scope.searchInfo.numAppList.push(item);
          }
          break;
      }
    };
    $scope.checkAppSelected = function(item,flag) {
      var idx;
      switch(flag) {
        case '0':
          idx = $scope.searchInfo.timeAppList.indexOf(item);
          break;
        case '1':
          idx = $scope.searchInfo.costAppList.indexOf(item);
          break;
        case '2':
          idx = $scope.searchInfo.numAppList.indexOf(item);
          break;
      }
      return idx!==-1?true:false;
    };
    $scope.searchTimeSelectedCheck = function(item) {
      return checkboxIsDisabled(item, $scope.searchInfo.timeAppList);
    };

    $scope.searchCostSelectedCheck = function(item) {
      return checkboxIsDisabled(item, $scope.searchInfo.costAppList);
    };

    $scope.searchNumSelectedCheck = function(item) {
      return checkboxIsDisabled(item, $scope.searchInfo.numAppList);
    };

    /*function defined*/
    /**
    * [checkboxIsDisabled 检查应用选择是否达到上限]
     * @param item [{object}] [当前点击对象]
     * @param selectedArr [{array}] [已选择对象集合]
     * @response boolean
    * */
    function checkboxIsDisabled(item,selectedArr) {
      var len = 5,idx=selectedArr.indexOf(item);
      if (idx===-1&&selectedArr.length>=len) {
        return true;
      }
      return false;
    }
    /**
    * [getUserApp 重新整理apps为user:{appinfo}形式]
     * @param apps [{array]] [封装对象]
     * @response object
    * */
    function getUserApp(apps) {
      var obj={};
      angular.forEach(apps, function(app) {
        if(app.userName&&!obj[app.userName]) {
          obj[app.userName] = [];
          obj[app.userName].push(app);
        }else {
          obj[app.userName].push(app);
        }
      });
      return obj;
    }
  }]);
