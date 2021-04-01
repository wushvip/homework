/**
 * Created by Echo on 2017/3/7.
 */
angular.module('app')
  .controller('indexSampleCtrl', ['$scope', '$stateParams','restService','restUrl', function($scope, $stateParams, restService, restUrl ) {
    var appId = $stateParams.appId;
    var appUserId = $stateParams.userId;
    $scope.samples = [];
    getSample();
    //获取样例
    function getSample() {
      $scope.submitting = true;
      restService.promiseRequest( restUrl.getUrl('apps')+'/:appId/sample', 'GET', {appId: appId}, {userId: appUserId}).then(function(res) {
        if(res) {
          $scope.samples = res;
          //$scope.toaster.pop('success',null, $scope.messages.public.success,3000);
        }else {
          //$scope.toaster.pop('error',null, $scope.messages.public.fail,3000);
        }
        $scope.submitting = false;
      }, function(error) {
        console.log(error);
        $scope.submitting = false;
      });
    }
    //使用样例
    $scope.useSample = function() {
      $scope.submitting = true;
      restService.promiseRequest( restUrl.getUrl('apps')+'/:appId/sample', 'POST', {appId: appId}, {userId: appUserId,appId: appId}).then(function(res) {
        if(res) {
          $scope.toaster.pop('success','成功', $scope.messages.public.success,3000);
        }
        $scope.submitting = false;
      }, function(error) {
        console.log(error);
        $scope.submitting = false;
      });
    };
  }]);
