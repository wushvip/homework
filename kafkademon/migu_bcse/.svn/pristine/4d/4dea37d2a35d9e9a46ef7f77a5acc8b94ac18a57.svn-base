/**
 * Created by Echo on 2017/3/26.
 */
'use strict';
angular.module('seApp')
  .directive('passwordVerify',[ function () {
    return {
      require: 'ngModel',
      restrict: 'A',
      link: function(scope, elem, attrs, ctrl) {

        //This part is supposed to check the strength
        ctrl.$parsers.unshift(function(viewValue) {
          var pwdValidLength, pwdHasLetter, pwdHasNumber;

          pwdValidLength = (viewValue && viewValue.length >= 8 && viewValue.length <= 16 ? true : false);
          pwdHasLetter = (viewValue && /[A-z]/.test(viewValue)) ? true : false;
          pwdHasNumber = (viewValue && /\d/.test(viewValue)) ? true : false;

          if( pwdValidLength && pwdHasLetter && pwdHasNumber ) {
            ctrl.$setValidity('verify', true);
          } else {
            ctrl.$setValidity('verify', false);
          }
          return viewValue;
        });
      }
    };
  }]);
