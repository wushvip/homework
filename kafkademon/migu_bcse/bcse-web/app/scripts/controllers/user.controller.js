'use strict';
/**
 * [用户个人信息]
 */
angular.module('user')
.controller('userCtrl', ['$scope','$rootScope','$uibModal', function($scope,$rootScope,$uibModal){
    var userId = $rootScope.userId;
    /**
    * define with object so that it can be used in pwd-modify.view.html
    * @name @id coms from existed place
    * @oldPwd @newPwd initialized will be2 given in pwd-modify.view.html
    */
    $scope.user = {
      oldPwd: null,
      newPwd: null,
      matchPwd: null
    };

    //modify password
    $scope.openModal=function(){
        $uibModal.open({
            templateUrl: 'views/um/pwdModify.view.html',
            scope: $scope,
            backdrop: 'static',
            controller: [ '$uibModalInstance','restService','restUrl', function ($uibModalInstance,restService,restUrl) {
              $scope.passwordChecked = false;
              $scope.errorShow = false;
              $scope.cancel = function() {
                $uibModalInstance.dismiss('cancel');
                $scope.user.oldPwd = null;
                $scope.user.newPwd = null;
                $scope.user.matchPwd = null;
              };
              $scope.modifyPwd = function(callback) {
                $scope.submitting = true;
                restService.promiseRequest(restUrl.getUrl('users')+'/:id/password','PUT',{id:userId},{userId: userId,password: md5($scope.user.oldPwd),updatePassword:md5($scope.user.newPwd)}).then(function(res) {
                  if(res) {
                    $scope.toaster.pop('success','成功',$scope.messages.um.pwdSuccess,3000);
                    callback();
                  }
                  $scope.submitting = false;
                }, function(error) {
                  console.log(error);
                  $scope.submitting = false;
                }).finally(function() {

                });
              }
            }]
        });
    };

}]);
