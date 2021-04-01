/**
 * Created by Echo on 2017/3/9.
 */
'use strict';
angular.module('app')
  .controller('configRecoveryCtrl', [ '$scope','$stateParams','restService','restUrl','Upload','Handle', function ($scope,$stateParams,restService,restUrl,Upload,Handle) {
    /*variable defined*/
    var appUserId = $stateParams.userId;
    var appId  = $stateParams.appId;
    $scope.isLoadDic = true;     //默认加载词典
    $scope.recoveryText = null;
    /*$scope function defined*/
    $scope.commonFileChange = function(file) {
      if(file) {
        uploadFile(file,"0");
      }
    };
    $scope.standardFileChange = function(file) {
      if(file) {
        uploadFile(file,"1");
      }
    };
    $scope.getRecovery = function() {
      $scope.submitting = true;
      var loadDic = $scope.isLoadDic?'1':'0';
      restService.promiseRequest(restUrl.getUrl('recovery'),'GET',{},{userId: appUserId,appId:appId,searchQuery:$scope.recoveryText,isLoadDic:loadDic}).then(function(res){
        if(res) {
          $scope.correctResult = res.recovery;
        }else {
          $scope.correctResult = null;
        }
      },function(error) {

      }).finally(function() {
        $scope.submitting = false;
      });
    };
    /*function defined*/
    function uploadFile(file,mode) {
      //如果文件还没有选中，即file==null，则返回
      if(!file){ return false;}
      //如果文件类型为txt并且大小不超过1000k，则执行请求
      switch(mode) {
        case '0': $scope.commonFile = file;
          break;
        case '1': $scope.standardFile = file;
          break;
      }
      if(Handle.checkFileType(file.name, ['txt'])) {
        file.uploading = true;
        Upload.upload({
          url: restUrl.getUrl('recovery')+'/dic',
          data: {userId: appUserId, appId: appId, file:file,mode:mode}
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
            $scope.toaster.pop('error',null, res.data.message,3000);
          }
        }, function(response) {
          file.result = null;
          file.progress = 0;
          file.uploaded = false;
          $scope.toaster.pop('error',null, response.status + ': ' + response.message,3000);
        }, function (evt) {
          file.progress = Math.min(90, parseInt(90.0 * evt.loaded / evt.total));
        }).finally(function(){
          file.uploading = false;
        });
      }
    }

  }]);
