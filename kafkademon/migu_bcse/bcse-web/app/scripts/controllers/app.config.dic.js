/**
 * Created by Echo on 2017/3/7.
 */
'use strict';
angular.module('app')
  .controller('configDicCtrl', [ '$scope','$stateParams','Upload','restUrl','Handle', function ($scope,$stateParams,Upload,restUrl,Handle) {
    /*variable defined*/
    var appUserId = $stateParams.userId;
    var appId  = $stateParams.appId;
    /*$scope function defined*/
    $scope.stopFileChange = function(file) {
      if(file) {
        uploadFile(file,'stop');
      }
    };
    $scope.synonymSubmit = function(file) {
      if(file) {
        uploadFile(file,'synonym');
      }
    };
    $scope.extentionSubmit = function(file) {
      if(file) {
        uploadFile(file,'extention');
      }
    };
    /*function defined*/
    function uploadFile(file,mode) {
      //如果文件还没有选中，即file==null，则返回
      if(!file){ return false;}
      //如果文件类型为txt并且大小不超过10k，则执行请求
      switch(mode) {
        case 'stop': $scope.stopFile = file;
              break;
        case 'synonym': $scope.synFile = file;
          break;
        case 'extention': $scope.extFile = file;
          break;
      }
      if(Handle.checkFileType(file.name, ['txt'])&&Handle.checkFileSize(file.size, 10)) {
        file.uploading = true;
        Upload.upload({
          url: restUrl.getUrl('config')+'/dic/'+mode,
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
            $scope.toaster.pop('error',null, res.data.message,3000);
          }
        }, function(response) {
          file.result = null;
          file.progress = 0;
          file.uploaded = false;
          $scope.toaster.pop('info',null, response.status + ': ' + response.message,3000);
        }, function (evt) {
          file.progress = Math.min(90, parseInt(90.0 * evt.loaded / evt.total));
        }).finally(function(){
          file.uploading = false;
        });
      }
    }
  }]);
