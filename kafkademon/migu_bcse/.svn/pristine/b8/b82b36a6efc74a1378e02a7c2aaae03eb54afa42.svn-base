'use strict';
/**
 * [用户列表管理Controller]
 */
angular.module('users')
.controller('usersCtrl', [ '$scope','$rootScope','$uibModal', 'restService','restUrl','TipModal', function ( $scope,$rootScope,$uibModal,restService,restUrl,TipModal ) {
    var userId = $rootScope.userId;
    var userStatus = $rootScope.userStatus;
    $scope.users = [];
    $scope.searchName = '';
    $scope.page = angular.copy($scope.pubObj.page);
    getAllUsers();
    /*$scope function defined */
    $scope.saveUser=function(info,callback){
      var promise,data = {userId:userId,userName:info.userName,userMail:info.userName,oaUserName:''};
      if(info.oaUserName) {
        data.oaUserName = info.oaUserName;
      }
      if(info&&info.userId) {
        promise = restService.promiseRequest(restUrl.getUrl('users')+'/:id','PUT',{id:info.userId},data);
      }else if(checkValidEmail(info.userName)&&!checkRepeatUserName(info.userName)) {
        data.userStatus = '0';
        promise = restService.promiseRequest(restUrl.getUrl('users'),'POST',{},data);
      }
      if(promise) {
        $scope.submitting=true;
        promise.then(function(res){
          if(res) {
            $scope.toaster.pop('success','成功',$scope.messages.public.success,3000);
            $scope.searchName = '';
            callback();
            getAllUsers();
          }
          $scope.submitting = false;
        },function(error) {
          console.log(error);
          $scope.submitting = false;
        });
      }
    };

    $scope.getUser = function() {
      getAllUsers();
    };
    $scope.deleteUser = function(user) {
      TipModal.showModal({}, {}).then(function(result) {
        user.operaDisabled = true;
        restService.promiseRequest(restUrl.getUrl('users')+'/:id','DELETE',{id: user.userId},{userId:userId}).then(function(res) {
          if(res) {
            getAllUsers();
          }
        }).finally(function() {
          user.operaDisabled = false;
        });
      });
    };
    $scope.resetPassword = function(user) {
      TipModal.showModal({}, {bodyText:'确定重置？'}).then(function(result) {
        user.operaDisabled = true;
        restService.promiseRequest(restUrl.getUrl('users') + '/:id/password', 'DELETE', {id: user.userId}, {userId: userId}).then(function (res) {
          if (res) {
            $scope.toaster.pop('success', '成功', $scope.messages.um.resetSuccess, 3000);
            getAllUsers();
          }
        }).finally(function () {
          user.operaDisabled = false;
        });
      });
    };
    $scope.operateUserStatus = function(user) {
      var userStatus = user.userStatus==='0'?'1':'0';
      var headerText = userStatus === '1'?'停用':'启用';
      TipModal.showModal({}, {bodyText:'确认' + headerText + '？'}).then(function(result) {
        user.operaDisabled = true;
        restService.promiseRequest(restUrl.getUrl('users') + '/:id', 'PUT', {id: user.userId}, {
          userId: userId,
          userStatus: userStatus
        }).then(function (res) {
          if (res) {
            getAllUsers();
          }
        }).finally(function () {
          user.operaDisabled = false;
        });
      });
    };
    $scope.editOaUser = function(user) {
      $uibModal.open({
        templateUrl: 'views/um/oa.modify.html',
        scope: $scope,
        backdrop: 'static',
        controller: [ '$scope','$uibModalInstance','info', function ($scope,$uibModalInstance,info) {
          $scope.isAdd = checkInfo(info);
          $scope.info = info||{};
          //$scope.title = $scope.isAdd?'添加用户':'编辑用户';
          $scope.cancel = function() {
            $uibModalInstance.dismiss('cancel');
          };
          function checkInfo(info) {
            return !info||!info.userId;
          }
        }],
        resolve: {
          info: function(){
            return angular.copy(user);
          }
        }
      });
    };
    $scope.getData = function (pageNo) {
      if(pageNo) {
        if(parseInt(pageNo,10)>=1&&parseInt(pageNo,10)<=$scope.page.maxPages) {
          $scope.page.currentPage = pageNo;
          $scope.page.inputCurPage = pageNo;
          getAllUsers();
        }
      }
    };

    /*function defined */
    function getAllUsers() {
      var data = {userId:userId,pageIndex:$scope.page.currentPage,pageNum:$scope.page.numPerPage,searchName:$scope.searchName};
      //获取数据函数
      $scope.submitting = true;
      restService.promiseRequest(restUrl.getUrl('users'),'GET',{},data).then(function(res) {
        handleUsersData(res);
        $scope.submitting = false;
      },function(error) {
        $scope.submitting = false;
      });
    }
    /**
     * [checkValidEmail 检查用户名是否符合邮箱格式]
     * @param  {[string]} email [邮箱名]
     */
    function checkValidEmail(email) {
      var reg=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
      if(!reg.test(email)){
        $scope.toaster.pop('error',null, $scope.messages.um.invalidEmail,3000);
        return false;
      }
      return true;
    }
    /**
     * [checkRepeatUserName 检查用户名是否已经存在]
     * @param  {[string]} userName [用户名]
     */
    function checkRepeatUserName(userName) {
      var idx;
      for (idx = 0; idx < $scope.users.length; idx++) {
        if($scope.users[idx].userName===userName){
          $scope.toaster.pop('error',null, $scope.messages.repetition,3000);
          return true;
        }
      }
      return false;
    }
    function handleUsersData (res) {
      if(res) {
        $scope.users = res.userList;
        $scope.page.totalItems = res.totalItems;
        $scope.page.maxPages = Math.ceil($scope.page.totalItems / $scope.page.numPerPage);
      }else {
        $scope.users = [];
        $scope.page.totalItems = 0;
      }
    }


}]);
