/**
 * Created by Echo on 2017/3/9.
 */
'use strict';
angular.module('app')
  .controller('appAnalysisCtrl', [ '$scope','$stateParams', function ($scope,$stateParams) {
    /*variable defined*/
    var appUserId = $stateParams.userId;
    var appId  = $stateParams.appId;
    $scope.searchInfo = {
      userId: appUserId,
      timeAppList: [{appId:appId,userId:appUserId,appName: $scope.appInfo.appName,userName: $scope.appInfo.userName}],
      costAppList: [{appId:appId,userId:appUserId,appName: $scope.appInfo.appName,userName: $scope.appInfo.userName}],
      numAppList: [{appId:appId,userId:appUserId,appName: $scope.appInfo.appName,userName: $scope.appInfo.userName}]
    };

    /*$scope function defined*/
    $scope.getTimeAppList = function() {
      return $scope.searchInfo.timeAppList;
    };

    /*function defined*/

  }]);
