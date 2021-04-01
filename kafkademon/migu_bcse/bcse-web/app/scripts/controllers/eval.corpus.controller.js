/**
 * Created by Echo on 2017/3/4.
 */
'use strict';
angular.module('eval')
  .controller('corpusCtrl', ['$scope','$rootScope', '$state','Upload','locals','restUrl','Handle', function ($scope, $rootScope, $state,Upload, locals,restUrl, Handle) {
    /*variables defined*/
    var userId = $rootScope.userId;
    var errorMsg = null;
    /*$scope function defined*/
    $scope.uploadFiles = function(file, $invalidFiles) {
      if(!file){
        return;
      }
      $scope.evalObj.file = file;
      if (!checkFile(file)) {
        return false;
      }else {
        //if(Handle.checkFileType(file.name, ['txt'])&&Handle.checkFileSize(file.size, 1000)) {
          $scope.uploading = true;
          Upload.upload({
            url: restUrl.getUrl('evaluation')+'/dic',
            data: {userId:userId,file:file}
          }).then(function(res) {
            if(res) {
              file.result = res.data;
            }
            if (res.data.status === "SUCCESS") {
              $scope.evalObj.dataId = res.data['result']?res.data['result']['dataId']:null;
              $scope.toaster.pop('success','成功', res.data.message,3000);
            }else {
              $scope.evalObj.dataId = null;
              $scope.toaster.pop('error','错误', res.data.message,3000);
            }
            $scope.uploading = false;
          }, function(response) {
            $scope.evalObj.dataId = null;
            $scope.toaster.pop('error','错误', response.data.message,3000);
            $scope.uploading = false;
          }, function (evt) {
            file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
          }).finally(function(){
            file.progress = 0;

          });
        //}
      }
    };

    $scope.previous = function() {
      $scope.navInit();
      $scope.nav.steps[0]['actived'] = true;
      $state.go('manage.eval');
    };

    $scope.next = function() {
      if (!checkFileIsExist()) {
        $scope.toaster.pop('warning','', errorMsg, 3000);
      }else {
        locals.set('evalCorpusId', $scope.evalObj.dataId);
        $state.go('manage.eval.result');
      }
    };

    /*function defined*/
    /**
     * [checkFileIsExist 检查文件是否存在]
     */
    function checkFileIsExist() {
      if (!$scope.evalObj.file) {
        errorMsg = $scope.messages.file.select;
        return false;
      }

      if ( !checkFile($scope.evalObj.file) || ($scope.evalObj.file['result'] && $scope.evalObj.file['result']['status'] !== "SUCCESS") ) {
        errorMsg = $scope.messages.file.redo;
        return false;
      }
      return true;
    }
    /**
     * [checkFile 检查选择应用是否为空以及是否存在]
     * @parama {[object]} file [文件对象]
     * @return boolean
     */
    function checkFile(file) {
      var prex = '.csv', len = file.name.length - prex.length;
      if (len>=0 && file.name.lastIndexOf('.csv') !== len) {
        file.errorMessage = $scope.messages.file.fileFormat('csv');
        return false;
      }
      if (file.size > 10*1024*1024) {
        file.errorMessage = $scope.messages.file.fileSizeExceed('10M');
        return false;
      }
      if (file.size <= 0) {
        file.errorMessage = $scope.messages.file.fileSizeUp('10M');
        return false;
      }
      return true;
    }
    /*steps control*/
    $scope.nav.steps[0]['passed'] = true;
    $scope.nav.steps[2]['passed'] = false;
    $scope.nav.steps[2]['actived'] = true;
    $scope.nav.steps[4]['actived'] = false;

  }]);
