/**
 * Created by Echo on 2017/3/1.
 */
'use strict';
angular.module('seApp')
  .directive('login',[ function () {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: 'views/portal/login.view.html',
      controller: ['$scope','$state', 'restService','restUrl','Handle','RsaPassword' ,function ($scope, $state, restService, restUrl, Handle, RsaPassword) {
        $scope.credentials = {
          userName: null,
          password: null
        };
        //默认类型选择为本系统
        $scope.type = '0';

        $scope.login = function () {
          console.log($scope.type);
          var promise;
          if($scope.type === '1') {
            promise = restService.promiseRequest(restUrl.getUrl('oaLogin'),'GET',{},{userName: $scope.credentials.userName,password: RsaPassword($scope.credentials.password)});
          }else {
            promise = restService.promiseRequest(restUrl.getUrl('login'),'GET',{},{userName: $scope.credentials.userName,password: md5($scope.credentials.password)});
          }
          $scope.submitting = true;
          promise.then(function(res) {
            if(res) {
              setUserCookie(res);
            }
          }, function(error) {
            console.log('error: ',error);
          }).finally(function() {
            $scope.submitting = false;
          });
        };

        function setUserCookie(res) {
          Handle.setCookie({userId: res.userId,userName: res.userName,userMail:res.userMail,role:res.role,userStatus:res.userStatus,oaUserName:res.oaUserName},1);
          $state.go('manage.apps');
        }

      }]
    };
  }]);
