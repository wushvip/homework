/**
 * Created by Echo on 2017/3/7.
 */
'use strict';
angular.module('app')
  .controller('uploadFileCtrl', [ '$scope','$uibModalInstance','$timeout','Upload','restService','restUrl','info','tableStruct', function ($scope, $uibModalInstance,$timeout,Upload,restService,restUrl,info,tableStruct) {
    var appUserId = info.appUserId;
    var type = info.type;
    var timer = null;
    var appId = info.appId;
    $scope.appId = appId;
    $scope.appUserId = appUserId;
    $scope.tableStruct = tableStruct;
    $scope.splitConfig = [',', '*', '|', '#', '$', '%', '&', '.', '/', '\\'];

    $scope.setLocalInit=function(item){
      if(item.file){
        item.file.errorMessage=null;
      }
      item.errorMessage=null;
      item.progress=0;
    };

    /**
     * [operateIndex 更新/删除/清空索引请求]
     * @param tableId {string} [表ID]
     * @param files {arrray} [文件集合]
     * @param item {object} [单条文件]
     * */
    function operateIndex(tableId,files,item){
      startProgress(item);
      var promise;
      if(type === 'update') {
        promise = restService.promiseRequest(restUrl.getUrl('apps')+'/:appId/index','POST',{appId:appId},{appId:appId,mutiValueSplit:item.mutiValueSplit,fileSplit:item.fileSplit,userId:appUserId});
      }
      if(type === 'delete') {
        promise = restService.promiseRequest(restUrl.getUrl('apps')+'/:appId/index', 'DELETE', {appId: appId}, {userId: appUserId,mutiValueSplit: item.mutiValueSplit,fileSplit: item.fileSplit});
      }
      $scope.submitting = true;
      promise.then(function(res) {
        if(res) {
          finishProgress(item);
        }else {
          stopProgress(item);
          item.errorMessage=res.message;
        }
        $scope.$emit('refreshDataNum',true);
      },function(){
        stopProgress(item);
        //item.errorMessage=$scope.messages.errorRetry;
      }).finally(function(){
        item.completed=true;
        $scope.submitting = false;
        fileIdx++;
        var len = files.length<5?files.length:5;
        if(fileIdx < len) {
          $scope.uploadFile(tableId,files);
        }
      });
    }
    var fileIdx;
    $scope.uploadFile = function(tableId,files){
      for (fileIdx = 0; fileIdx < files.length; fileIdx++) {
        if (files[fileIdx]&&files[fileIdx].completed === undefined&&checkFile(files[fileIdx])) {
          $scope.submitting = true;
          uploadSigleFile(tableId,files,files[fileIdx]);
          break;
        }else {
          continue;
        }
      }
    };

    function uploadSigleFile(tableId,files,file) {
      file['errorMessage'] = null;
      file['completed'] = false;
      Upload.upload({
        url: restUrl.getUrl('apps')+'/'+appId+'/data/local',
        data: {userId: appUserId, appId: appId, tableId: tableId, file:file}
      }).then(function(res) {
        if (res.data.status === "SUCCESS") {
          operateIndex(tableId,files,file);
        }else {
          file['progress'] = 0;
          file['errorMessage'] = res.data['message'];
          file['completed'] = false;
        }
      }, function(response) {
        file['progress'] = 0;
        file['errorMessage'] = response.status + ': ' + response.message;
        file['completed'] = false;
      }, function (evt) {
        file['progress'] = Math.min(50, parseInt(50.0 * evt.loaded / evt.total));
      }).finally(function(){
        $scope.submitting = false;
      });
    }

    $scope.removeFile = function(files,idx) {
      files.splice(idx,1);
    };

    $scope.cancel = function() {
      $uibModalInstance.close();
    };

    $scope.checkFilesIsAvailable = function (files) {
      return  checkFilesIsAvailable(files);

    };
    $scope.checkFileIsAvailable = function (file) {
      return checkFileIsAvailable(file);
    };
    $scope.checkFileName = function (file) {
      return checkFileName(file);
    };
    $scope.checkFileSize = function (file) {
      return checkFileSize(file);
    };
    $scope.checkFileSplit = function (file) {
      return checkFileSplit(file);
    };

    $scope.checkFile = function (file) {
      return checkFile(file);
    };
    $scope.checkFileNum = function(files) {
      if (files&&files.length>=5) {
        $scope.toaster.pop('warning','', $scope.messages.limitation(5),3000);
      }
    };
    $scope.limitFileNum = function(files) {
      if (files&&files.length>5) {
        files.length = 5;
        $scope.toaster.pop('warning','', $scope.messages.limitation(5),3000);
      }
      return false;
    };
    function checkFilesIsAvailable (files) {
      if (files&&files.length) {
        for (var i = 0; i < files.length; i++) {
          if(checkFileIsAvailable(files[i])){
            return true;
          }
        }
      }
      return false;
    }
    function checkFileIsAvailable (file) {
      return isFileCompletedUndefined(file)&&checkFile(file);
    }

    function isFileCompletedUndefined(file) {
      return file.completed===undefined?true:false;
    }
    /*function checkFilesName(files){
      if (files.length) {
        for (var i = 0; i < files.length; i++) {
          if(!checkFileName(files[i])){
            return false;
          }
        }
        return true;
      }
    }*/
    function checkFile (file) {
      if (checkFileName(file)&&checkFileSize(file)&&checkFileSplit(file)){
        return true;
      }else {
        return false;
      }
    }
    function checkFileName(file) {
      if(file&&file['name']){
        var d=file['name'].substring(file['name'].lastIndexOf('.') + 1);
        if(d==='csv'){
          return true;
        }
      }
      return false;
    }
    function checkFileSize(file) {
      if(file&&file['size']) {
        if(file['size'] > 200*1024*1024) {
          return false;
        }
        return true;
      }
    }

    //验证分隔符
    function checkFileSplit (file) {
      if (!file.fileSplit) {
        return false;
      }
      if (!file.mutiValueSplit) {
        return false;
      }
      if (file.fileSplit === file.mutiValueSplit) {
        return false;
      }
      return true;
    }
    function update(item){
      if (item.progress>=85) {
        return;
      }else{
        item.progress += 5;
        timer=$timeout(function(){update(item);}, 200);
      }
    }
    function startProgress(item){
      item.progress=item.progress|0;
      update(item);
    }
    function stopProgress(item){
      $timeout.cancel(timer);
      item.progress=0;
    }
    function finishProgress(item){
      $timeout.cancel(timer);
      item.progress=100;
    }

  }]);
