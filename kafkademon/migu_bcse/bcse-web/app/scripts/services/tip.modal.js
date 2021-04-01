/**
 * Created by Echo on 2017/2/28.
 */
'use strict';
angular.module('seApp')
  .service('TipModal', ['$uibModal',
    function ($uibModal) {

      var modalDefaults = {
        backdrop: true,
        keyboard: true,
        modalFade: true,
        animation: true,
        templateUrl: 'views/templates/alert.info.html'
      };

      var modalOptions = {
        closeButtonText: '取消',
        actionButtonText: '确定',
        headerText: '提示',
        bodyText: '确认删除？',
        type:'2',
        confirm:true
      };

      this.showModal = function (customModalDefaults, customModalOptions) {
        if (!customModalDefaults) { customModalDefaults = {}; }
        customModalDefaults.backdrop = 'static';
        return this.show(customModalDefaults, customModalOptions);
      };

      this.show = function (customModalDefaults, customModalOptions) {
        //Create temp objects to work with since we're in a singleton service
        var tempModalDefaults = {};
        var tempModalOptions = {};

        //Map angular-ui modal custom defaults to modal defaults defined in service
        angular.extend(tempModalDefaults, modalDefaults, customModalDefaults);

        //Map modal.html $scope custom properties to defaults defined in service
        angular.extend(tempModalOptions, modalOptions, customModalOptions);

        if (!tempModalDefaults.controller) {
          tempModalDefaults.controller = ['$scope', '$uibModalInstance','$timeout', function ($scope, $uibModalInstance,$timeout) {
            $scope.modalOptions = tempModalOptions;
            $scope.modalOptions.ok = function (result) {
              $uibModalInstance.close(result);
            };
            $scope.modalOptions.close = function () {
              $uibModalInstance.dismiss('cancel');
            };
            //设置自动关闭时间
            if($scope.modalOptions.delay) {
              $timeout( $scope.modalOptions.close,$scope.modalOptions.delay);
            }
          }];
        }

        return $uibModal.open(tempModalDefaults).result;
      };

    }
  ]);
