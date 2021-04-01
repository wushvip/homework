/**
 * Created by Echo on 2017/3/9.
 */
'use strict';
angular.module('app')
  .controller('configAnalyzerCtrl', [ '$scope','$stateParams','restService','restUrl','Upload','Handle', function ($scope,$stateParams,restService,restUrl, Upload,Handle) {
    /*variable defined*/
    var appUserId = $stateParams.userId;
    var appId  = $stateParams.appId;
    $scope.segFields = ['ANSJ', 'QP_NoSplit', 'JP_NoSplit', 'ANSJ_JP', 'ANSJ_QP', 'ANSJ_PY'];    //控制所有字段
    $scope.segField = null;     //控制选中字段
    $scope.segText = null;      //控制分析分本
    /*$scope function defined*/
    $scope.uploadFile = function(file) {
      //如果文件还没有选中，即file==null，则返回
      if(!file){ return false;}
      $scope.file = file;
      //如果文件类型为txt并且大小不超过10k，则执行请求
      if(Handle.checkFileType(file.name, ['txt'])&&Handle.checkFileSize(file.size, 10)) {
        file.uploading = true;
        Upload.upload({
          url: restUrl.getUrl('config')+'/dic/ambiguity',
          data: {userId: appUserId, appId: appId, file:file}
        }).then(function(res) {
          if(res) {
            file.result = res.data;
          }
          if (res.data.status === "SUCCESS") {
            file.uploaded = true;
            file.progress = 100;
            $scope.toaster.pop('success',null, res.data.message,3000);
          }else {
            file.uploaded = false;
            file.progress = 0;
            file.fileErrorMsg = res.data.message;
            $scope.toaster.pop('error',null, res.data.message,3000);
          }
        }, function(response) {
          file.result = null;
          file.progress = 0;
          file.uploaded = false;
          file.fileErrorMsg = response.status + ': ' + response.message;
          $scope.toaster.pop('info',null, response.status + ': ' + response.message,3000);
        }, function (evt) {
          file.progress = Math.min(90, parseInt(90.0 * evt.loaded / evt.total));
        }).finally(function(){
          file.uploading = false;
        });
      }
    };
    $scope.getAnalyzer = function() {
      if($scope.segField) {
        restService.promiseRequest(restUrl.getUrl('analyzer'),'GET',{},{userId: appUserId,appId:appId,searchQuery:$scope.segText,fieldSearch:$scope.segField}).then(function(res){
          if(res) {
            $scope.analysisResult = res.analysis;
          }else {
            $scope.analysisResult = null;
          }
        },function(error) {

        }).finally(function() {
          $scope.submitting = false;
        });
      }
    };
    /*function defined*/


  }]);
