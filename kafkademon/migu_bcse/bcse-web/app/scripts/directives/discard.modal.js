/**
 * Created by Echo on 2017/2/28.
 */
'use strict';
angular.module('seApp')
  .directive('discardModal', ['$rootScope', '$uibModalStack',
    function($rootScope, $uibModalStack) {
      return {
        restrict: 'A',
        link: function() {
          /**
           * If you are using ui-router, use $stateChangeStart method otherwise use $locationChangeStart
           * StateChangeStart will trigger as soon as the user clicks browser back button or keyboard backspace and modal will be removed from modal stack
           */

          $rootScope.$on('$locationChangeStart', function () {
            var top = $uibModalStack.getTop();
            if (top) {
              $uibModalStack.dismiss(top.key);
            }
          });
        }
      };
    }
  ]);
