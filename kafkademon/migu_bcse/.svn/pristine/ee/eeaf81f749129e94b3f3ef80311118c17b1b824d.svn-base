/**
 * Created by Echo on 2017/2/28.
 */
'use strict';
angular.module('portal')
  .controller('portalCtrl', ['$scope', 'Messages', 'toaster', function ($scope, Messages, toaster) {
    /*carousesl*/
    $scope.myInterval = 5000;
    $scope.nowrapSlides = false;
    $scope.active = 0;
    var slides = $scope.slides = [{
      id: 0,
      image: 'images/portal/precise.png',
      text: '不同业务、不同场景搜索结果排序准确率高'
    },{
      id: 1,
      image: 'images/portal/security.png',
      text: '系统访问引入加密、认证等技术提升安全性'
    },{
      id: 2,
      image: 'images/portal/smart.png',
      text: '不同业务、不同场景搜索结果排序准确率高'
    }];
    var currIndex = 0;
    $scope.messages = Messages;
    $scope.toaster = toaster;

  }]);
